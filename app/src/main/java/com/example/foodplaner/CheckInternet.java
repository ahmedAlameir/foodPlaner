package com.example.foodplaner;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

import com.example.foodplaner.MyPlan.View.MyPlanFragment;

public class CheckInternet {
    public static Boolean getConnectivity(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork=cm.getActiveNetworkInfo();
        if(activeNetwork!=null){
            return true;
        }
        else {
            return false;
        }
    }
}
