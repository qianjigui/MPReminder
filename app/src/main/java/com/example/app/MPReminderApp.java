package com.example.app;

import android.app.Application;

/**
 * Created by wpc on 7/2/14.
 */
public class MPReminderApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler handler = CrashHandler.getInstance();
        handler.init(getApplicationContext());
    }
}
