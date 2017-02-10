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
    private static final String file = "Hello World!";
    private static String isLoggedIn = "LoggedIn";
    private static String who = "Who";
    private static final int mode =0;
    private static final String[] fields = {"uid","name","user_name","email","dateOfCreation"};

    public TheSessionKeeper(Context c){
        this.context = c;
        sharedPreferences = context.getSharedPreferences(file,mode);
        editor = sharedPreferences.edit();
    }


    public void setIsLoggedIn(boolean loggedIn,String... info){
        editor.putBoolean(isLoggedIn,loggedIn);
        for (int i =0 ;i < info.length;i++){
            editor.putString(fields[i],info[i]);
            editor.commit();
        }

    }

    public void logOut(){
        editor.putBoolean(isLoggedIn,false);
        editor.commit();
    }

    public boolean isLoggedIn(){
        return sharedPreferences.getBoolean(isLoggedIn, false);
    }

    public String get(String toGet){
        return sharedPreferences.getString(toGet,null);
    }

    /*public void bomb(){
        for (String holder:fields){
            editor.putString(holder,"");
        }

    }*/

}


