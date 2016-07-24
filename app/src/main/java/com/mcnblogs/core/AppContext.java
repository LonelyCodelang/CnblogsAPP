package com.mcnblogs.core;

import android.app.Application;

/**
 * 作者：hl
 * 时间:2016/7/22 19:25
 * 说明:
 */
public class AppContext extends Application  {
    // singleton
    private static AppContext AppContext = null;

    @Override
    public void onCreate() {
        super.onCreate();
        AppContext = this;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public static AppContext getInstance() {
        return AppContext;
    }
}
