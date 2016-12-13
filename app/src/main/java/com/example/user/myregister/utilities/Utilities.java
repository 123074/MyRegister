package com.example.user.myregister.utilities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.user.myregister.BuildConfig;

/**
 * Created by user on 12/11/2016.
 */

public class Utilities {
    public static void log(String string){

        if(BuildConfig.DEBUG){
            Log.i("Utilities",string);
        }
    }
    public static boolean isNetworkAvailable(Context context){
        ConnectivityManager cm=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info=cm.getActiveNetworkInfo();
        if(info!=null){
            return true;

        }
        else {
            return false;
        }

    }
}
