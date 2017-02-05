package com.example.alienware.loginandregistration;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

/**
 * Created by Alienware on 17-01-2017.
 */

public class SignUp extends Fragment {

    TextView not_equal;
    EditText name, user_name, password, confirm_password,email;
    Button register;
    Login login;
    ChangeFrag tc;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        tc = (ChangeFrag) getActivity();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.signup,container,false);
        name = (EditText) view.findViewById(R.id.name);
        user_name = (EditText) view.findViewById(R.id.user_name);
        email = (EditText)view.findViewById(R.id.email);
        password = (EditText) view.findViewById(R.id.password);
        confirm_password = (EditText) view.findViewById(R.id.confirm_password);
        not_equal = (TextView)view.findViewById(R.id.passwords_not_equal);
        login = new Login();

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


        register = (Button) view.findViewById(R.id.register);
        register.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){

                    case MotionEvent.ACTION_DOWN: return true;

                    case MotionEvent.ACTION_UP:

                        //since we only have one button don't be concerned with nested switch inside action up
                        if (HouseKeeping.areFieldsEmpty(name,user_name,email,password,confirm_password))
                            Toast.makeText(getContext(), "You're a special kind of idiot", Toast.LENGTH_SHORT).show();
                        else{
                                //frag for login
                            if(not_equal.getVisibility()== EditText.INVISIBLE) {
                                jsonToPass();
                                tc.bringChange(login);
                            }
                        }
                }
                return true;
            }
        });

        return view;
    }


    JSONObject jsonToPass(){
        return new  HouseKeeping().createJson("name",name.getText().toString(),"user_name",user_name.getText().toString(),"email",email.getText().toString(),"password",password.getText().toString());
    }
}


