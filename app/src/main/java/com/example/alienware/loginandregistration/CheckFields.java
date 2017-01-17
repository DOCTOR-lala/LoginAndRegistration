package com.example.alienware.loginandregistration;


import android.widget.EditText;

class CheckFields {
    static boolean areFieldsEmpty(EditText... myStrings){
        for(EditText s: myStrings)
            if(s.getText().toString().equals(""))
                return true;
        return false;
    }
}

