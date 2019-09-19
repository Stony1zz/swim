package com.donkeyhouse.donkyhouse.house;
/*
 * 驴舍温度、湿度、气体浓度数据可视化（折线图）
 * */

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Line;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Anchor;
import com.anychart.enums.MarkerType;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.Stroke;
import com.donkeyhouse.donkyhouse.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DataVisual extends AppCompatActivity {


    private List<DataEntry> seriesData = new ArrayList<>();
    private Cartesian cartesian;
    private Handler handler;
    private AnyChartView anyChartView;


    @SuppressLint("HandlerLeak")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_visual);
        showLineChart();
        handler = new Handler() {
            /**
             * Subclasses must implement this to receive messages.
             *
             * @param msg
             */
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        showData(seriesData);
                        break;
                }
            }
        };
    }

    private void showLineChart() {
        anyChartView = findViewById(R.id.any_chart_view);
        anyChartView.setProgressBar(findViewById(R.id.progress_bar));
        cartesian = AnyChart.line();
        cartesian.animation(true);
        cartesian.padding(10d, 20d, 5d, 20d);
        cartesian.crosshair().enabled(true);
        cartesian.crosshair()
                .yLabel(true)
                // TODO ystroke
                .yStroke((Stroke) null, null, null, (String) null, (String) null);

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesian.title("驴舍温度、湿度、光照条件数据可视化");
        cartesian.yAxis(0).title("温湿度、光照等数值");
        cartesian.xAxis(0).labels().padding(5d, 5d, 5d, 5d);
        initServe();
    }

    private void initServe() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request1 = builder.get().url("http://192.168.0.100:8080/history/listTemperatureHistory?limit=20").build();
                Request request2 = builder.get().url("http://192.168.0.100:8080/history/listHumidityHistory?limit=20").build();
                Request request3 = builder.get().url("http://192.168.0.100:8080/history/listIlluminationHistory?limit=20").build();
                try {
                    Response response1 = client.newCall(request1).execute();
                    Response response2 = client.newCall(request2).execute();
                    Response response3 = client.newCall(request3).execute();
                    String responseData1 = response1.body().string();
                    String responseData2 = response2.body().string();
                    String responseData3 = response3.body().string();
                    parseJSONWithJsonObject(responseData1, responseData2, responseData3);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseJSONWithJsonObject(String JsonData1, String JsonData2, String JsonData3) {
        try {
            JSONObject obj1 = new JSONObject(JsonData1);
            JSONObject obj2 = new JSONObject(JsonData2);
            JSONObject obj3 = new JSONObject(JsonData3);
            JSONArray data1 = obj1.getJSONArray("data");
            JSONArray data2 = obj2.getJSONArray("data");
            JSONArray data3 = obj3.getJSONArray("data");
            showResponse(data1, data2, data3);
        } catch (JSONException e) {
            Log.e("有异常", "异常是" + e.getMessage());
            e.printStackTrace();
        }

    }

    private void showResponse(final JSONArray data1, final JSONArray data2, final JSONArray data3) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                JSONObject row1 = null;
                JSONObject row2 = null;
                JSONObject row3 = null;
                for (int i = 18; i >= 0; i--) {
                    try {
                        row1 = data1.getJSONObject(i);
                        row2 = data2.getJSONObject(i);
                        row3 = data3.getJSONObject(i);
                        seriesData.add(new CustomDataEntry(row1.getString("hometimeId"), Float.parseFloat(row1.getString("temperature")), Float.parseFloat(row2.getString("humidity")), Float.parseFloat(row3.getString("illumination"))));
                        Log.e("输出:", "parseJSONWithJsonObject: " + row1.get("temperature"));
                        handler.sendEmptyMessage(1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void showData(List<DataEntry> seriesData) {
        Log.i("大小:", "parseJSONWithJsonObject: " + seriesData.size());
        Log.i("大小:", "parseJSONWithJsonObject: " + seriesData);
        Set set = Set.instantiate();
        set.data(seriesData);
        Mapping series1Mapping = set.mapAs("{ x: 'x', value: 'value' }");
        Mapping series2Mapping = set.mapAs("{ x: 'x', value: 'value2' }");
        Mapping series3Mapping = set.mapAs("{ x: 'x', value: 'value3' }");

        Line series1 = cartesian.line(series1Mapping);
        series1.name("温度");
        series1.hovered().markers().enabled(true);
        series1.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series1.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        Line series2 = cartesian.line(series2Mapping);
        series2.name("湿度");
        series2.hovered().markers().enabled(true);
        series2.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series2.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        Line series3 = cartesian.line(series3Mapping);
        series3.name("光照");
        series3.hovered().markers().enabled(true);
        series3.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series3.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        cartesian.legend().enabled(true);
        cartesian.legend().fontSize(13d);
        cartesian.legend().padding(0d, 0d, 10d, 0d);

        anyChartView.setChart(cartesian);
    }

    private class CustomDataEntry extends ValueDataEntry {

        private String x;
        private Number v1;
        private Number v2;
        private Number v3;

        CustomDataEntry(String x, Number value, Number value2, Number value3) {
            super(x, value);
            this.x = x;
            this.v1 = value;
            this.v2 = value2;
            this.v3 = value3;

            setValue("value2", value2);
            setValue("value3", value3);

        }

        @Override
        public String toString() {
            return "{" + x + "," + v1 + "," + v2 + "," + v3 + "}";
        }
    }
}
