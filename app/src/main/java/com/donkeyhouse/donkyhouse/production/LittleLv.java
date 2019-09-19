package com.donkeyhouse.donkyhouse.production;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.donkeyhouse.donkyhouse.HttpAdress;
import com.donkeyhouse.donkyhouse.R;
import com.donkeyhouse.donkyhouse.ToastUtil;
import com.donkeyhouse.donkyhouse.adapter.LittleAdapter;
import com.donkeyhouse.donkyhouse.bean.Little;
import com.donkeyhouse.donkyhouse.bean.Natatorium;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LittleLv extends AppCompatActivity {
    RecyclerView recyclerView;
    private static List<Natatorium> littleList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_little_lv);
        initLittle();
        Log.e("11111111111111111111", "run: ");
        recyclerView = findViewById(R.id.little_rec);
        //recyclerView = findViewById(R.id.species_Rec);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        LittleAdapter adapter = new LittleAdapter(littleList);
        Log.e("适配器","sssssssssssssssssssssssss"+littleList.size());
        recyclerView.setAdapter(adapter);
        Log.e("适配器","sssssssssssssssssssssssss");
    }

    private void initLittle() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client =new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.get().url(HttpAdress.SWIMPOOL).build();
                try {
                    Response response = client.newCall(request).execute();
                    Log.e("22222222222222222222", "run:");
                    String responseData = response.body().string();
                    Log.e("11111111111111111111", "run:"+responseData);
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
                Calendar c = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String formate = simpleDateFormat.format(c.getTime());
                for (int i = 0; i < data.length(); i++) {
                    try {
                        row = data.getJSONObject(i);
                            Natatorium little = new Natatorium(Long.parseLong(row.getString("natatoriumId")),formate,row.getString("name"),Float.parseFloat(row.getString("waterLevel")),row.getString("swimmingPoolId"),row.getString("detail"));
                            littleList.add(little);
                            Log.e("每个尺子:",littleList.get(i).toString());
                            Log.e("输出每个名字:"," "+i+"  "+row.get("natatoriumId"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
