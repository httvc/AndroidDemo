package com.httvc.androiddemo.ui;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

import com.httvc.androiddemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NotificationActivity extends AppCompatActivity {
    public static final int TYPE_Normal = 1;
    @BindView(R.id.btn1)
    Button btn1;
    private NotificationManager manger;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.bind(this);
        manger = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    @OnClick({R.id.btn1,R.id.btn2,R.id.btn3,
            R.id.btn4,R.id.btn5,R.id.btn6,R.id.btn7,R.id.btn8})
    void setOnClick(View view){
        switch (view.getId()){
            case R.id.btn1:
               NotificationCompat.Builder builder= new NotificationCompat.Builder(this);
                builder.setPriority(NotificationCompat.PRIORITY_MAX);//设置优先级
                //Ticker是状态栏显示的提示
                builder.setTicker("更新app");
                //第一行内容 通常作为通知栏标题
                builder.setContentText("更新");
                //第二行内容 通常是通知正文
                builder.setContentText("内容");
                //第三行内容 通常是内容摘要什么的 在低版本机器上不一定显示
                builder.setSubText("这里显示的是通知第三行内容！");
                builder.setAutoCancel(true);// 将AutoCancel设为true后，当你点击通知栏的notification后，它会自动被取消消失
                builder.setNumber(2);// 这里用来显示右下角的数字
                builder.setSmallIcon(R.mipmap.ic_launcher);
                builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
                Intent intent=new Intent(this,UpdateVersionActivity.class);
                PendingIntent pIntent = PendingIntent.getActivity(this, 1, intent, 0);
                builder.setContentIntent(pIntent);
                builder.setDefaults(NotificationCompat.DEFAULT_ALL);//铃声、闪光、震动均系统默认
                Notification notification = builder.build();
                manger.notify(TYPE_Normal,notification);
                break;
        }
    }

}
