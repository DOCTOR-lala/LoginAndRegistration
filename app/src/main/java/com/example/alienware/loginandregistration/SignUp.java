package com.example.alienware.loginandregistration;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Alienware on 17-01-2017.
 */

public class SignUp extends AppCompatActivity {

    TextView not_equal;
    EditText name, user_name, password, confirm_password;
    Button register;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        not_equal = (TextView)findViewById(R.id.passwords_not_equal);

        name = (EditText) findViewById(R.id.name);
        user_name = (EditText) findViewById(R.id.user_name);
        password = (EditText) findViewById(R.id.password);
        confirm_password = (EditText) findViewById(R.id.confirm_password);

        confirm_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!(password.getText().toString().equals(confirm_password.getText().toString())))
                    not_equal.setVisibility(EditText.VISIBLE);
                else
                    not_equal.setVisibility(EditText.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        register = (Button) findViewById(R.id.register);
        register.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){

                    case MotionEvent.ACTION_DOWN: return true;

                    case MotionEvent.ACTION_UP:

                        //since we only have one button don't be concerned with nested switch inside action up
                        if (CheckFields.areFieldsEmpty(name, user_name, password, confirm_password))
                            Toast.makeText(getApplicationContext(), "You're a special kind of idiot", Toast.LENGTH_SHORT).show();
                        else{
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }
                        }
                return true;
            }
        });
    }
}

