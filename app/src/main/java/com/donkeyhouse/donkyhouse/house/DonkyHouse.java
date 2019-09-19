package com.donkeyhouse.donkyhouse.house;
/*
* 驴舍的信息显示报警和控制
* */
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.donkeyhouse.donkyhouse.R;
import com.donkeyhouse.donkyhouse.adapter.HouseAdapter;
import com.donkeyhouse.donkyhouse.bean.House;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DonkyHouse extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerView;
    private List<House> housesList = new ArrayList<>();
    private ImageView house_light;
    private ImageView house_tem;
    private ImageView house_shidu;
    private ImageView house_nongdu;
    private TextView light;
    private TextView tem;
    private TextView shidu;
    private TextView nongdu;
    @SuppressLint("HandlerLeak")
    private Handler handler= new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    String str1 = msg.getData().getString("data1");
                    try {
                        JSONArray result = new JSONArray(str1);
                        showResponse(result);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donky_house);
        initData();
        initHouse();
        recyclerView = findViewById(R.id.house_recycle);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        HouseAdapter adapter = new HouseAdapter(housesList);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        house_light = findViewById(R.id.house_ligth);
        house_tem = findViewById(R.id.house_tem);
        house_shidu = findViewById(R.id.house_shidu);
        house_nongdu = findViewById(R.id.house_nongdu);
        light = findViewById(R.id.text_light);
        tem = findViewById(R.id.text_tem);
        shidu = findViewById(R.id.text_shidu);
        nongdu = findViewById(R.id.text_nongdu);
        house_light.setOnClickListener(this);
        house_tem.setOnClickListener(this);
        house_shidu.setOnClickListener(this);
        house_nongdu.setOnClickListener(this);
    }

    private void initHouse() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client =new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.get().url("http://192.168.0.100:8080/api/listHomes").build();
                try {
                    Response response = client.newCall(request).execute();
                    Log.e("22222222222222222222", "run: ");
                    String responseData = response.body().string();
                    Log.e("11111111111111111111", "run: "+responseData);
                    parseJSONWithJsonObject(responseData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void parseJSONWithJsonObject(String JsonData) {
        try {
            JSONObject obj = new JSONObject(JsonData);
            final JSONArray data1 = obj.getJSONArray("data");
            Message message = new Message();
            message.what = 1;
            Bundle bundle = new Bundle();
            bundle.putString("data1", String.valueOf(data1));
            message.setData(bundle);
            handler.sendMessage(message);
        } catch (JSONException e) {
            Log.e("有异常", "异常是"+e.getMessage());
            e.printStackTrace();
        }

    }
    private void showResponse(final JSONArray data) {
        JSONObject row = null;
        for (int i = 0; i < data.length(); i++) {
            try {
                row = data.getJSONObject(i);
                House house = new House(row.getString("homeId"),row.getString("useraccount"),row.getString("temperature"),row.getString("humidity"),row.getString("illumination"));
                housesList.add(house);
                Log.e("输出每个名字:", "parseJSONWithJsonObject: "+row.get("homeId"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @SuppressLint("ResourceType")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.house_ligth:
                house_light.setImageDrawable(getResources().getDrawable(R.mipmap.icon_house_clickcange));
                house_tem.setImageDrawable(getResources().getDrawable(R.mipmap.icon_house_top));
                house_shidu.setImageDrawable(getResources().getDrawable(R.mipmap.icon_house_top));
                house_nongdu.setImageDrawable(getResources().getDrawable(R.mipmap.icon_house_top));
                light.setTextColor(Color.WHITE);
                tem.setTextColor(getResources().getColor(R.color.brown));
                shidu.setTextColor(getResources().getColor(R.color.brown));
                nongdu.setTextColor(getResources().getColor(R.color.brown));
                lishtControl();
                break;
            case R.id.house_tem:
                house_light.setImageDrawable(getResources().getDrawable(R.mipmap.icon_house_top));
                house_tem.setImageDrawable(getResources().getDrawable(R.mipmap.icon_house_clickcange));
                house_shidu.setImageDrawable(getResources().getDrawable(R.mipmap.icon_house_top));
                house_nongdu.setImageDrawable(getResources().getDrawable(R.mipmap.icon_house_top));
                light.setTextColor(getResources().getColor(R.color.brown));
                tem.setTextColor(Color.WHITE);
                shidu.setTextColor(getResources().getColor(R.color.brown));
                nongdu.setTextColor(getResources().getColor(R.color.brown));
                temControl();
                break;
            case R.id.house_shidu:
                house_light.setImageDrawable(getResources().getDrawable(R.mipmap.icon_house_top));
                house_tem.setImageDrawable(getResources().getDrawable(R.mipmap.icon_house_top));
                house_shidu.setImageDrawable(getResources().getDrawable(R.mipmap.icon_house_clickcange));
                house_nongdu.setImageDrawable(getResources().getDrawable(R.mipmap.icon_house_top));
                light.setTextColor(getResources().getColor(R.color.brown));
                tem.setTextColor(getResources().getColor(R.color.brown));
                shidu.setTextColor(Color.WHITE);
                nongdu.setTextColor(getResources().getColor(R.color.brown));
                shiduControl();
                break;
            case R.id.house_nongdu:
                house_light.setImageDrawable(getResources().getDrawable(R.mipmap.icon_house_top));
                house_tem.setImageDrawable(getResources().getDrawable(R.mipmap.icon_house_top));
                house_shidu.setImageDrawable(getResources().getDrawable(R.mipmap.icon_house_top));
                house_nongdu.setImageDrawable(getResources().getDrawable(R.mipmap.icon_house_clickcange));
                light.setTextColor(getResources().getColor(R.color.brown));
                tem.setTextColor(getResources().getColor(R.color.brown));
                shidu.setTextColor(getResources().getColor(R.color.brown));
                nongdu.setTextColor(Color.WHITE);
                nongduControl();
                break;
        }
    }

    private void nongduControl() {
    }

    private void shiduControl() {
    }

    private void temControl() {
    }

    private void lishtControl() {
    }
}
