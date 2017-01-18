package com.example.alienware.loginandregistration;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by Alienware on 17-01-2017.
 */

public class TheSessionKeeper {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;
    private static final String name = "Hello World!";
    private static String isLoggedIn = "LoggedIn";
    private static String who = "Who";
    private static final int mode =0;

    public TheSessionKeeper(Context c){
        this.context = c;
        sharedPreferences = context.getSharedPreferences(name,mode);
        editor = sharedPreferences.edit();
    }


    public void setIsLoggedIn(String name,boolean loggedIn){
        editor.putBoolean(isLoggedIn,loggedIn);
        editor.putString(who,name);
        editor.commit();
    }

    public boolean isLoggedIn(){
        return sharedPreferences.getBoolean(isLoggedIn, false);
    }

    public String whoIsLoggedIn(){
        return sharedPreferences.getString(who,null);
    }

}


