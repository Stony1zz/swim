package com.donkeyhouse.donkyhouse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.donkeyhouse.donkyhouse.bean.Lifeguard;
import com.donkeyhouse.donkyhouse.bean.User;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import okhttp3.MediaType;

public class Regist extends AppCompatActivity implements View.OnClickListener {

    private EditText regist_account;
    private EditText regist_password;
    private EditText regist_name;
    private EditText regist_sex;
    private EditText regist_depp;
    private Button regist_btn;

    private String account;
    private String passWord;
    private String name;
    private String sex;
    private String poolid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        initData();
    }

    private void initData() {
        regist_account = findViewById(R.id.regist_account);
        regist_password = findViewById(R.id.regist_password);
        regist_name=findViewById(R.id.regist_name);
        regist_depp=findViewById(R.id.regist_deep);
        regist_sex=findViewById(R.id.regist_sex);
        regist_btn = findViewById(R.id.regist_btn);

        regist_account.setOnClickListener(this);
        regist_password.setOnClickListener(this);
        regist_btn.setOnClickListener(this);
        regist_sex.setOnClickListener(this);
        regist_depp.setOnClickListener(this);
        regist_name.setOnClickListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.regist_btn:
                logon();
                Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,LogIn.class);
                startActivity(intent);
                break;
        }
    }
    public void logon(){
        String url = HttpAdress.LOG_ON;
        account = regist_account.getText().toString().trim();
        passWord = regist_password.getText().toString().trim();
        sex=regist_sex.getText().toString().trim();
        poolid=regist_depp.getText().toString().trim();
        name=regist_name.getText().toString().trim();
        OkHttpUtils
                .postString()
                .url(url)
                .content(new Gson().toJson(new User(name,account,"sdsdsd", passWord,sex,poolid)))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtil.showToast(Regist.this, "用户名不存在");
                    }

                    @Override
                    public void onResponse(String response, int id) {

                        ToastUtil.showToast(Regist.this, response.toString().trim());
                        Intent intent = new Intent(Regist.this,LogIn.class);
                        startActivity(intent);
                        finish();
                    }
                });
    }
}
