package com.httvc.androiddemo.api;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ParseException;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.httvc.androiddemo.utils.UnicodeUtils;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;

import javax.net.ssl.SSLHandshakeException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by Android Studio
 * Project：AndroidDemo
 * Author：httvc
 * Email：jfjie2013@163.com
 * Date：2017/5/24.
 */

public abstract class HttpCallBack<T> implements Callback<HttpResult<T>> {

    private Context mContext;
    private ProgressDialog mProgressDialog;

    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;
    private static Gson gson = new Gson();
    /**
     * @param mContext
     * @param showProgress 是否显示加载框
     * @param msg          加载框的提示语
     */
    public HttpCallBack(Context mContext, boolean showProgress, String msg) {
        this.mContext = mContext;
        if (showProgress) {
            //show your progress bar
            showDialog(msg);
        }
    }

    public abstract void onSuccess(T t);

    /**
     * @param code
     * @param message
     */

    public abstract void onFailure(int code, String message);

    @Override
    public void onResponse(Call<HttpResult<T>> call, Response<HttpResult<T>> response) {
        dismissDialog();
        String errorMessage = "";
        if (response.isSuccessful()) { //code 200
            String responseCode = response.body().getStatus();
            int code = Integer.parseInt(responseCode);
            if ("0".equals(responseCode)) {
                onSuccess(response.body().getData());
            } else if ("101".equals(responseCode)) {
                errorMessage = "APPKEY为空或不存在";
                onFailure(code, errorMessage);
            } else if ("102".equals(responseCode)) {
                errorMessage = "APPKEY已过期";
                onFailure(code, errorMessage);
            } else if ("103".equals(responseCode)) {
                errorMessage = "APPKEY无请求此数据权限";
                onFailure(code, errorMessage);
            } else if ("104".equals(responseCode)) {
                errorMessage = "请求超过次数限制";
                onFailure(code, errorMessage);
            } else if ("105".equals(responseCode)) {
                errorMessage = "IP被禁止";
                onFailure(code, errorMessage);
            } else if ("106".equals(responseCode)) {
                errorMessage = "IP请求超过限制";
                onFailure(code, errorMessage);
            } else if ("107".equals(responseCode)) {
                errorMessage = "接口维护中";
                onFailure(code, errorMessage);
            } else if ("108".equals(responseCode)) {
                errorMessage = "接口已停用";
                onFailure(code, errorMessage);
            }else {
                onFailure(code, errorMessage);
            }

        } else {
            //================ 1.handle http default error 4xx,5xx=================
            int code = response.raw().code();
            String message = response.raw().message();   //code 和 message 都是http Raw 数据，你抓包就能看见的
            Log.e("http-error", "code:" + code + "   message:" + message);
            //我们的项目返回404 的时候有可能是翻页到没有数据了,这一点很恶心
            if (code != 404) {
                onFailure(code, message);
                return;
            }

            //================ 2.把项目业务方面定义的错误提取处理处理，和业务逻辑，api 有关=================
            String errorBodyStr = "";
            try {   //我们的项目需要的UniCode转码，不是必须要的！
                errorBodyStr = UnicodeUtils.convertUnicode(response.errorBody().string());
            } catch (IOException ioe) {
                Log.e("errorBodyStr ioe:", ioe.toString());
            }
            try {
                HttpResult errorResponse = gson.fromJson(errorBodyStr, HttpResult.class);
                if (null != errorResponse) {
                    onFailure(Integer.parseInt(errorResponse.getStatus()), errorResponse.getMsg());
                    //这里的code 如果定义和public void onFailure(Call<T> call, Throwable t) { 一样，要记得分开处理
                } else {
                    onFailure(-1, "ErrorResponse is null ");  //!!!!!!
                }
            } catch (Exception jsonException) {
                onFailure(-1, "http请求错误Json 信息异常"); //
                jsonException.printStackTrace();
            }
        }//response is not Successful dispose over !
    }

    @Override
    public void onFailure(Call<HttpResult<T>> call, Throwable e) {
        dismissDialog();
        String temp = e.getMessage().toString();
        String errorMessage = "";
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            switch (httpException.code()) {
                case UNAUTHORIZED:
                case FORBIDDEN:
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
                    errorMessage = "网络错误";
                    break;
            }

        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            errorMessage = "解析错误";
        } else if (e instanceof ConnectException) {
            errorMessage = "网络连接失败，请检测网络";
        } else if (e instanceof SSLHandshakeException) {
            errorMessage = "证书验证失败";
        } else if (e instanceof ConnectTimeoutException) {
            errorMessage = "连接超时";
        } else if (e instanceof SocketTimeoutException) {
            errorMessage = "连接超时";
        }else if (e instanceof RuntimeException){
            errorMessage = "运行时错误";
        }else if (e instanceof UnknownHostException){
            errorMessage = "无法解析主机，请检查网络连接";
        }else if (e instanceof UnknownServiceException){
            errorMessage = "未知的服务器错误";
        }else {
            errorMessage = "获取数据失败" + temp;
        }
        onFailure(-1, errorMessage);
    }

    public void showDialog(String message) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(mContext);
        }
        mProgressDialog.setMessage(message);
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    public void dismissDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
