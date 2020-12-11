package com.example.app;

import android.app.Application;
import android.content.Context;

public class MainApplication extends Application {
    private static MainApplication mInstance;
    private Context mContext;

    public Context getContext(){
        return mContext;
    }

    public static MainApplication getInstance(){
        if(mInstance == null){
            throw new IllegalStateException("nullPointer Exeption");
        }
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mContext = getApplicationContext();
    }
}
