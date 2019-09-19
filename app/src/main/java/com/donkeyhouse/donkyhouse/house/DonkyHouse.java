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

public class DonkyHouse extends AppCompatActivity  {
    RecyclerView recyclerView;
    private static List<UserHistory> housesList = new ArrayList<UserHistory>();
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
        initHouse();
        recyclerView = findViewById(R.id.house_recycle);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        HouseAdapter adapter = new HouseAdapter(housesList);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }


    private void initHouse() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client =new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.get().url("http://192.168.0.102:8080/rfid/listUserHis/").build();
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
                UserHistory house = new UserHistory(Long.parseLong(row.getString("userDataId")),Long.parseLong(row.getString("userId")),row.getString("sensorId"),row.getString("waterPressure"),row.getString("rfidinfo"),row.getString("time"));
                housesList.add(house);
                Log.e("输出每个名字:", "parseJSONWithJsonObject: "+row.get("userDataId"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }







}
