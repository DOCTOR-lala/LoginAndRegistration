package com.example.alienware.loginandregistration;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Alienware on 17-01-2017.
 */

public class LoginSuccess extends AppCompatActivity {

    TextView textView;

    LoginSuccess(String name){
        textView.setText(name);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_success);
        textView = (TextView)findViewById(R.id.nameOfUser);

    }
}
