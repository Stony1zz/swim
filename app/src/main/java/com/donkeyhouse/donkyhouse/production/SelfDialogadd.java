package com.donkeyhouse.donkyhouse.production;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.donkeyhouse.donkyhouse.R;

public class SelfDialogadd extends Dialog {
    private Button yesadd;//确定按钮
    private Button noadd;//取消按钮
    private EditText zhongliangadd;//消息标题文本
    private EditText weizhiadd;//消息提示文本
    private String titleStradd;//从外界设置的title文本
    private String messageStradd;//从外界设置的消息文本
    //确定文本和取消文本的显示内容
    private String yesStradd, noStradd;
    private onNoOnclickListener noOnclickListener;//取消按钮被点击了的监听器
    private onYesOnclickListener yesOnclickListener;//确定按钮被点击了的监听器
    /*设置取消按钮的显示内容和监听*/
    public void setNoOnclickListener(String str, onNoOnclickListener onNoOnclickListener) {
        if (str != null) {
            noStradd = str;
        }
        this.noOnclickListener = onNoOnclickListener;
    }
    /*设置确定按钮的显示内容和监听*/
    public void setYesOnclickListener(String str, onYesOnclickListener onYesOnclickListener) {
        if (str != null) {
            yesStradd = str;
        }
        this.yesOnclickListener = onYesOnclickListener;
    }
    public SelfDialogadd(Context context) {
        super(context, R.style.MyDialog);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialogviewadd);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(true);
        //初始化界面控件
        initView();
        //初始化界面数据
        initData();
        //初始化界面控件的事件
        initEvent();

    }
    /*初始化界面的确定和取消监听器*/
    private void initEvent() {
        //设置确定按钮被点击后，向外界提供监听
        yesadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yesOnclickListener != null) {
                    yesOnclickListener.onYesClick();
                }
            }
        });
        //设置取消按钮被点击后，向外界提供监听
        noadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noOnclickListener != null) {
                    noOnclickListener.onNoClick();
                }
            }
        });
    }
    /*初始化界面控件的显示数据*/
    private void initData() {
        //如果用户自定了title和message
        if (titleStradd != null) {
            zhongliangadd.setText(titleStradd);
        }
        if (messageStradd != null) {
            weizhiadd.setText(messageStradd);
        }
        //如果设置按钮的文字
        if (yesStradd != null) {
            yesadd.setText(yesStradd);
        }
        if (noStradd != null) {
            noadd.setText(noStradd);
        }
    }
    /*初始化界面控件*/
    private void initView() {
        yesadd = (Button) findViewById(R.id.yesadd);
        noadd = (Button) findViewById(R.id.noadd);
        zhongliangadd =  findViewById(R.id.zhongliangadd);
        weizhiadd =  findViewById(R.id.weizhiadd);
    }
    /*从外界Activity为Dialog设置标题*/
    public void setTitle(String title) {
        titleStradd = title;
    }
    /*从外界Activity为Dialog设置dialog的message*/
    public void setMessage(String message) {
        messageStradd = message;
    }
    /*设置确定按钮和取消被点击的接口*/
    public interface onYesOnclickListener {
        public void onYesClick();
    }
    public interface onNoOnclickListener {
        public void onNoClick();
    }
}
