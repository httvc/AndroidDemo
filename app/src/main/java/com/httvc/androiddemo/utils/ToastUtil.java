package com.httvc.androiddemo.utils;

import android.content.Context;
import android.widget.Toast;


/**
 * Created by xieshengqi on 17/2/21.
 */

public class ToastUtil {
    public static void show(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void showDeBugMessage(String err) {
    }

    public static void err(Context context, int code, String msg) {
        Toast.makeText(context, code + ":" + msg, Toast.LENGTH_SHORT).show();
    }

//    public static Observable<String> showSnackbar(View view, String string, String action) {
//        return Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> e) throws Exception {
//                Snackbar.make(view, string, Snackbar.LENGTH_INDEFINITE)
//                        .setAction(action, view1 -> {
//                            e.onNext(action);
//                            e.onComplete();
//                        }).show();
//            }
//        });
}