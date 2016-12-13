package com.example.user.myregister.utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by user on 12/11/2016.
 */

public class Pref {
    public static final String KEY_NAME="name";
    Context context;
    SharedPreferences sharedPreferences;
    public Pref(Context context){

        this.context=context;
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);

    }
    public String getPreferences(String key){
        return sharedPreferences.getString(key,"");

    }
    public boolean setPreferences(String key,String value){
        return sharedPreferences.edit().putString(key, value).commit();
    }
}
