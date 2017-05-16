package com.httvc.androiddemo.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Android Studio
 * Project：ShouJinLiCai
 * Author：httvc
 * Date：2016/7/27.
 */
public class SharedPreferenceUtils {

    public static String CONFIG = "share_data";
    private static SharedPreferences sharedPreferences;


    public static void saveStringDate(Context context, String key, String value) {

        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        }
        sharedPreferences.edit().putString(key, value).commit();
    }

    public static String getStringDate(Context context, String key, String defaultValue) {

        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        }

        return sharedPreferences.getString(key, defaultValue);

    }

    public static void saveBooleanDate(Context context, String key, boolean value) {

        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        }

        sharedPreferences.edit().putBoolean(key, value).commit();

    }

    public static boolean getBooleanDate(Context context, String key, boolean defaultValue) {

        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        }

        return sharedPreferences.getBoolean(key, defaultValue);

    }

    public static void saveIntDate(Context context, String key, int value) {

        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        }

        sharedPreferences.edit().putInt(key, value).commit();

    }

    public static int getIntDate(Context context, String key, int defaultValue) {

        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        }

        return sharedPreferences.getInt(key, defaultValue);

    }

    public static void saveLongDate(Context context, String key, long value) {

        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        }

        sharedPreferences.edit().putLong(key, value).commit();

    }

    public static long getLongDate(Context context, String key, long defaultValue) {

        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        }

        return sharedPreferences.getLong(CONFIG, defaultValue);

    }

    public static void saveFloatDate(Context context, String key, float value) {

        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        }

        sharedPreferences.edit().putFloat(key, value).commit();

    }

    public static float getFloatDate(Context context, String key, float defaultValue) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        }
        return sharedPreferences.getFloat(key, defaultValue);
    }

}
