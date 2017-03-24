package com.andy.timeofisrael.utils;

import android.util.Log;

/**
 * Created by lancelot on 2016/11/14.
 */

public class LogUtils {
    private static final String TAG = "timesofisrael";

    public static void log(String message) {
        Log.d(TAG, message);
    }

    public static void e(Exception e) {
        Log.e(TAG, e.getMessage());
    }

    public static void e(Throwable throwable) {
        Log.e(TAG, throwable.getMessage());
    }
}
