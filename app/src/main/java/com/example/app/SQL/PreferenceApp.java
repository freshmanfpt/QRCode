package com.example.app.SQL;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.app.MainActivity;
import com.example.app.MainApplication;

public class PreferenceApp {

    private static final String NAME = "preferenceApp";
    private static final boolean isShowWeb = false;
    private static PreferenceApp mInstance;

    private final SharedPreferences preferences;

    private PreferenceApp(Context context){
        this.preferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    public static PreferenceApp getInstance(){
        if (mInstance == null){
            mInstance = new PreferenceApp(MainApplication.getInstance().getContext());
        }
        return mInstance;
    }

    public boolean getBoolean(String key, boolean def){
        return preferences.getBoolean(key, def);
    }

    public void putBoolean(String key, boolean value){
        SharedPreferences.Editor editor = editor();
        editor.putBoolean(key, value);
        System.out.println("tuan: valueSHOW_WEB = "+getBoolean(SHOW_WEB, false)+"\ntuan: valueLUU_QUET = "+getBoolean(TU_LUU_LICH_SU_QUET,false));
    }

    private SharedPreferences.Editor editor(){
        return this.preferences.edit();
    }

    public static final String SHOW_WEB = "showWeb";
    public static final String TU_LUU_LICH_SU_QUET = "tuLuuLsQuet";

}
