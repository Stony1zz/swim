package com.donkeyhouse.donkyhouse.production;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.donkeyhouse.donkyhouse.R;
import com.donkeyhouse.donkyhouse.adapter.HistoryAdapter;
import com.donkeyhouse.donkyhouse.bean.RHistory;
import com.donkeyhouse.donkyhouse.bean.UserHistory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RFIDHistory extends AppCompatActivity {
    RecyclerView recyclerView;
    private List<UserHistory> historyList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rfidhistory);
        initHistory();
//        RHistory rHistory = new RHistory("123","erw","htrhr","ty5","rtut","453");
//        Log.d("hitory", String.valueOf(rHistory));
//        historyList.add(rHistory);
        recyclerView = findViewById(R.id.history_rec);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        HistoryAdapter adapter = new HistoryAdapter(historyList);
        recyclerView.setAdapter(adapter);
    }

    private void initHistory() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client =new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.get().url("http://192.168.0.101:8080/history/listDonkeyHistory?limit=20").build();
                try {
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
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
            JSONArray data1 = obj.getJSONArray("data");
            showResponse(data1);
        } catch (JSONException e) {
            Log.e("有异常", "异常是"+e.getMessage());
            e.printStackTrace();
        }

    }
    private void showResponse(final JSONArray data) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                JSONObject row = null;
                for (int i = 0; i < data.length(); i++) {
                    try {
                        row = data.getJSONObject(i);
                        UserHistory rHistory = new UserHistory(Long.parseLong(row.getString("userDataId")),Long.parseLong(row.getString("userId")),row.getString("sensorId"),row.getString("waterPressure"),row.getString("RFIDInfo"),row.getString("time"));
                        Log.d("hitory" +
                                "", String.valueOf(rHistory));
                        historyList.add(rHistory);
                        Log.e("输出每个名字:", "parseJSONWithJsonObject: "+row.get("donkeyId"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
