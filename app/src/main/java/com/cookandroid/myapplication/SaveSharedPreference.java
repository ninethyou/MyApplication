package com.cookandroid.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SaveSharedPreference {
    private static final String pre_userID = "userID";
    private static final String pre_userPW = "userPW";

    static SharedPreferences getSharedPreferences(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setUserIDPW(Context context, String userID, String userPW){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(pre_userID, userID);
        editor.putString(pre_userPW, userPW);
        editor.commit();
    }
    public static String getUserID(Context context){
        return getSharedPreferences(context).getString(pre_userID,"");
    }
    public static String getUserPW(Context context){
        return getSharedPreferences(context).getString(pre_userPW,"");
    }

    public static void clearUserID(Context context){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.clear();
        editor.commit();
    }

}
