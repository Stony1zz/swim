package com.donkeyhouse.donkyhouse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.donkeyhouse.donkyhouse.house.DonkyHouse;
import com.donkeyhouse.donkyhouse.production.Production_Manage;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout production_ll;
    private LinearLayout donkyhouse_ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this,"欢迎",Toast.LENGTH_SHORT).show();
        initData();
    }

    private void initData() {
        production_ll = findViewById(R.id.production_LL);
        donkyhouse_ll =findViewById(R.id.donkyhouse_LL);
        production_ll.setOnClickListener(this);
        donkyhouse_ll.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.production_LL:
                Intent intent = new Intent(MainActivity.this,Production_Manage.class);
                startActivity(intent);
                break;
            case R.id.donkyhouse_LL:
                Intent intent1 = new Intent(MainActivity.this,DonkyHouse.class);
                startActivity(intent1);
                break;
        }
    }
}
