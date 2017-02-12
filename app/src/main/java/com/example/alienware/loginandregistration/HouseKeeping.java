package com.example.alienware.loginandregistration;

import android.widget.EditText;

import org.json.JSONObject;

/**
 * Created by Alienware on 05-02-2017.
 */

public class HouseKeeping {

//for creating Json from Strings
    JSONObject createJson(String... strings){
        JSONObject jsonObject = new JSONObject();
        int i =0;
        try {
            while (i<strings.length){
                jsonObject.put(strings[i],strings[i+1]);
                i+=2;
            }
            System.out.print("JSON->> "+jsonObject);
            return jsonObject;
        }catch (Exception e){e.printStackTrace();}
        return null;
    }


//to check if login and sign up fields are empty
    static boolean areFieldsEmpty(EditText... myStrings){
        for(EditText s: myStrings)
            if(s.getText().toString().equals(""))
                return true;
        return false;
    }

    String createHash(String secret){
        return
    }


 //to encode and decode password

}
