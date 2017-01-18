package com.example.alienware.loginandregistration;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Alienware on 17-01-2017.
 */

public class LoginSuccess extends AppCompatActivity {

    TextView textView;
    TheSessionKeeper theSessionKeeper;
    Button logout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_success);
        theSessionKeeper = new TheSessionKeeper(getApplicationContext());
        textView = (TextView)findViewById(R.id.nameOfUser);
        textView.setText(theSessionKeeper.whoIsLoggedIn());
        logout = (Button)findViewById(R.id.logout);
        logout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                            theSessionKeeper.setIsLoggedIn(textView.getText().toString(),false);
                            startActivity(new Intent(getApplication(), MainActivity.class));
                            finish();
                        return true;
                }
                return true;
            }
        });
    }
}
