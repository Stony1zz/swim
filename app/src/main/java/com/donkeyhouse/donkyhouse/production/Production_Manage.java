package com.donkeyhouse.donkyhouse.production;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.donkeyhouse.donkyhouse.MainActivity;
import com.donkeyhouse.donkyhouse.R;
import com.donkeyhouse.donkyhouse.adapter.ProductionAdapter;
import com.donkeyhouse.donkyhouse.bean.Production;
import com.donkeyhouse.donkyhouse.bean.SwimmingPool;

import java.util.ArrayList;
import java.util.List;

public class Production_Manage extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerView;
    private List<SwimmingPool>SwimmingPoolList=new ArrayList<>();
    private TextView  search_ll;
    private ImageView search_history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_production__manage);
        initSwim();
        search_history = findViewById(R.id.search_history);
        search_history.setOnClickListener(this);
        search_ll = findViewById(R.id.search_LL);
        search_ll.setOnClickListener(this);
        recyclerView = findViewById(R.id.species_Rec);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ProductionAdapter adapter = new ProductionAdapter(SwimmingPoolList);
        recyclerView.setAdapter(adapter);

       // FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.float_bar1);
    }

    private void initSwim() {
        SwimmingPool SwimmingPool1 = new SwimmingPool("中北游泳馆","中北大学","服务良好");
        SwimmingPoolList.add(SwimmingPool1);
        SwimmingPool SwimmingPool2 = new SwimmingPool("尖草游泳馆","尖草坪","整体良好");
        SwimmingPoolList.add(SwimmingPool2);
        SwimmingPool SwimmingPool3 = new SwimmingPool("太原游泳馆","太原","整体健康");
        SwimmingPoolList.add(SwimmingPool3);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search_LL:
                Intent intent = new Intent(Production_Manage.this,Search.class);
                startActivity(intent);
                break;
            case R.id.search_history:
                Intent intent1 = new Intent(Production_Manage.this,RFIDHistory.class);
                startActivity(intent1);
                break;
        }
    }
}
