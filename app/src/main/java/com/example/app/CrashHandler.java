package com.example.app;

import android.content.Context;

/**
 * Created by wpc on 7/2/14.
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private Thread.UncaughtExceptionHandler mDefaultHandler;
    private Context mContext;
    private static CrashHandler single;
    public static CrashHandler getInstance(){
        if(single==null){
            synchronized (CrashHandler.class){
                if(single==null){
                    single = new CrashHandler();
                }
            }
        }
        return single;
    }

    private CrashHandler(){}

    public void init(Context context){
        mContext = context;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        mDefaultHandler.uncaughtException(thread,ex);
    }
}
