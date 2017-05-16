package com.httvc.androiddemo.receiver;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.httvc.androiddemo.utils.SharedPreferenceUtils;

public class DownLoadCompleteReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)){
            long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1L);
            long downloadApkId= SharedPreferenceUtils.getLongDate(context,"DOWNLOAD_ID",-1L);
            // if (downloadApkId == id) {
            Intent intentInstall = new Intent();
            intentInstall.setAction(Intent.ACTION_VIEW);
            DownloadManager downManager = (DownloadManager)context.getSystemService(Context.DOWNLOAD_SERVICE);
            Uri downloadFileUri = downManager.getUriForDownloadedFile(id);
            if (downloadFileUri != null) {
               // collapseStatusBar(context);
                intentInstall.setDataAndType(downloadFileUri,"application/vnd.android.package-archive");
                intentInstall.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intentInstall);
            }else {
                Toast.makeText(context,"weizhaodo",Toast.LENGTH_SHORT).show();
            }
            //   }

        }else if(intent.getAction().equals(DownloadManager.ACTION_NOTIFICATION_CLICKED)){
            //点击通知栏执行的
        }
    }

    /**
     * 收起通知栏
     * @param context
     */
    public static void collapseStatusBar(Context context) {
      /*  try{
            Object statusBarManager = context.getApplicationContext().getSystemService("statusbar");
            Method collapse;
            if (Build.VERSION.SDK_INT <= 16){
                collapse = statusBarManager.getClass().getMethod("collapse");
            }else{
                collapse = statusBarManager.getClass().getMethod("collapsePanels");
            }
            collapse.invoke(statusBarManager);
        }catch (Exception localException){
            localException.printStackTrace();
        }*/
    }
}
