package com.httvc.androiddemo.ui;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.httvc.androiddemo.R;
import com.httvc.androiddemo.utils.SDCardUtils;
import com.httvc.androiddemo.utils.SharedPreferenceUtils;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateVersionActivity extends AppCompatActivity {

    @BindView(R.id.update_version_btn)
    Button updateVersionBtn;
    private DownloadManager downManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_version);
        ButterKnife.bind(this);
        downManager = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
    }

    @OnClick({R.id.update_version_btn})
    void setOnClick(View view){
        switch (view.getId()){
            case R.id.update_version_btn:
                DownloadManager.Request request =
                        new DownloadManager.Request(Uri.parse("http://www.jzzcgc.com/appdebug/zhichuanggongcheng.apk"));
                //设置在什么网络情况下进行下载
                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
                //设置通知栏标题
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
                request.setTitle("下载");
                request.setDescription("智创工程");
                request.setAllowedOverRoaming(false);
                File file = new File(SDCardUtils.getRootDirectory()+"/httvc");

                request.setDestinationInExternalPublicDir(file.getName(), "zhichuanggongcheng.apk");
                //设置文件存放目录
               // request.setDestinationInExternalFilesDir(this, Environment.DIRECTORY_DOWNLOADS, "mydown");

                //将下载请求添加至downManager，注意enqueue方法的编号为当前
               Long id= downManager.enqueue(request);
                SharedPreferenceUtils.saveLongDate(this,"DOWNLOAD_ID",id);
                break;
        }
    }


    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        // Intent intent = new Intent(Intent.ACTION_VIEW);
        //   intent.setDataAndType(Uri.fromFile(new File(mUrl)),
        //           "application/vnd.android.package-archive");
    }



}
