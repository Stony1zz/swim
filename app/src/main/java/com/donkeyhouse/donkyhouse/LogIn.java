package com.donkeyhouse.donkyhouse;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.donkeyhouse.donkyhouse.bean.User;
import com.donkeyhouse.donkyhouse.bean.login;
import com.donkeyhouse.donkyhouse.bean.login1;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import okhttp3.MediaType;

public class LogIn extends AppCompatActivity implements View.OnClickListener {

    private EditText login_account;
    private EditText login_password;
    private Button login_btn;
    private TextView goto_regist;
    private TextView goto_regist1;
    private String userName;
    private String passWord;
    private CheckBox jiushengyuan;
    public static boolean jiazhi=false;
    private SharedPreferences.Editor editor= Myapplication.editor;
    public String url = HttpAdress.LOG_IN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        initData();
    }

    private void initData() {
        login_account = findViewById(R.id.login_account);
        login_password = findViewById(R.id.login_password);
        login_btn = findViewById(R.id.login_btn);
        goto_regist = findViewById(R.id.goto_regist);
        goto_regist1=findViewById(R.id.goto_regist1);
        jiushengyuan=findViewById(R.id.jiushengyuan);
        jiushengyuan.setOnClickListener(this);
        login_account.setOnClickListener(this);
        login_password.setOnClickListener(this);
        login_btn.setOnClickListener(this);
        goto_regist.setOnClickListener(this);
        goto_regist1.setOnClickListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_btn:
                Toast.makeText(this,"正在请求服务器验证，请稍后...",Toast.LENGTH_LONG).show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                            login();
                            //Intent intent1 = new Intent(LogIn.this,MainActivity.class);
                            //startActivity(intent1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
            case R.id.goto_regist:
                Intent intent = new Intent(this,Regist.class);
                startActivity(intent);
                break;
            case R.id.goto_regist1:
                Intent intent1 = new Intent(this,Regist1.class);
                startActivity(intent1);
                break;
            case R.id.jiushengyuan:
                    jiazhi=true;
                    url=HttpAdress.LOG_IN_life;
        }
    }
    public void login(){

    userName = login_account.getText().toString().trim();
    passWord = login_password.getText().toString().trim();
            if (jiazhi==false) {
                OkHttpUtils
                        .postString()
                        .url(url)
                        .content(new Gson().toJson(new login(userName, passWord)))
                        .mediaType(MediaType.parse("application/json; charset=utf-8"))
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                ToastUtil.showToast(LogIn.this, "用户名不存在");
                            }

                            @Override
                            public void onResponse(String response, int id) {

                                ToastUtil.showToast(LogIn.this, response.toString().trim());
                                    editor.putString("userId", userName);
                                    editor.putString("passWord", passWord);
                                    editor.putString("userName", userName);
                                    editor.commit();
                                    startActivity(new Intent(LogIn.this, MainActivity.class));
                                    finish();
                            }
                        });

            }else {
                OkHttpUtils
                        .postString()
                        .url(url)
                        .content(new Gson().toJson(new login1(userName, passWord)))
                        .mediaType(MediaType.parse("application/json; charset=utf-8"))
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                ToastUtil.showToast(LogIn.this, "用户名不存在");
                            }

                            @Override
                            public void onResponse(String response, int id) {

                                ToastUtil.showToast(LogIn.this, response.toString().trim());
                                editor.putString("userId", userName);
                                editor.putString("passWord", passWord);
                                editor.putString("userName", userName);
                                editor.commit();
                                startActivity(new Intent(LogIn.this, MainActivity.class));
                                finish();
                            }
                        });

            }
    }
}
