package com.example.alienware.loginandregistration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnTouchListener{

    EditText password, user_name;
    Button login, sign_up;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        password = (EditText)findViewById(R.id.password);
        user_name = (EditText)findViewById(R.id.user_name);

        login = (Button)findViewById(R.id.login);
        sign_up = (Button)findViewById(R.id.sign_up);
        login.setOnTouchListener(this);
        sign_up.setOnTouchListener(this);


    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()){

            case MotionEvent.ACTION_DOWN: return true;

            case MotionEvent.ACTION_UP:
                switch (view.getId()){
                    case R.id.login:
                        if(CheckFields.areFieldsEmpty(user_name, password))
                            Toast.makeText(getApplicationContext(), "Can't Login without information \n *judging intensifies*", Toast.LENGTH_SHORT).show();
                        return true;

                    case R.id.sign_up:
                        startActivity ( new Intent(MainActivity.this,SignUp.class));
                        return true;
                }
        }

        return true;
    }

}
