package com.donkeyhouse.donkyhouse.production;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.donkeyhouse.donkyhouse.R;
import com.donkeyhouse.donkyhouse.adapter.SearchAdapter;
import com.donkeyhouse.donkyhouse.bean.SearchItem;

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

public class Search extends AppCompatActivity implements View.OnClickListener {
    private EditText realsearch_ed;
    private ImageView production_search_iv;
    RecyclerView recyclerView;
    private List<SearchItem> searchItemList = new ArrayList<>();
    private String getEt;
    private int i=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        realsearch_ed = findViewById(R.id.realsearch_ed);
        production_search_iv = findViewById(R.id.production_search);
        production_search_iv.setOnClickListener(this);
        recyclerView = findViewById(R.id.produce_search_Rec);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        SearchAdapter adapter = new SearchAdapter(searchItemList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.production_search:
                if (1==i){
                    GoToSearch();
                    i++;
                }else{
                    i=1;
                    Intent intent = new Intent(this,Search.class);
                    startActivity(intent);
                }
                break;
        }
    }

    private void GoToSearch() {
//        getEt = realsearch_ed.getText().toString().trim();
        //访问服务器获取需要的数据，拿下来，保存在字符串中。
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client =new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.get().url("http://192.168.0.100:8080/api/listDonkeys").build();
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
            JSONArray data1 = obj.getJSONArray("data");
            Log.e("11111111111111111111", "run: "+data1);
            String s = obj.getString("size");
            ShowSearch(data1,s);
        } catch (JSONException e) {
            Log.e("有异常", "异常是"+e.getMessage());
            e.printStackTrace();
        }

    }


    private void ShowSearch(final JSONArray data, final String s) {
        //将拿下来的字符串显示在item。
        //包括recyclview的设置
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                JSONObject row = null;
                Calendar c = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String formate = simpleDateFormat.format(c.getTime());
                for (int i = 0; i < Integer.parseInt(s); i++) {
                    try {
                        row = data.getJSONObject(i);
                        Log.e("输出每个名字1111:", "parseJSONWithJsonObject: " + row.get("donkeyId"));
                        if(row.getString("homeId").equals(realsearch_ed.getText().toString().trim())){
                            SearchItem searchItem = new SearchItem(row.getString("donkeyId"),row.getString("RFIDInfo"),row.getString("homeId"),formate,row.getString("gender"),row.getString("size"));
                            Log.e("输出每个名字2222:", "parseJSONWithJsonObject: " + row.get("donkeyId"));
                            searchItemList.add(searchItem);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
