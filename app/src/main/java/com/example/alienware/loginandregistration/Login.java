package com.example.alienware.loginandregistration;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Alienware on 20-01-2017.
 */

public class Login extends Fragment implements View.OnTouchListener{

    EditText password, user_name;
    Button login, sign_up;
    TheSessionKeeper theSessionKeeper;
    LoginSuccess loginSuccess;
    SignUp signUp;
    ChangeFrag tc;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        tc = (ChangeFrag)getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        loginSuccess = new LoginSuccess();
        signUp = new SignUp();
        theSessionKeeper = new TheSessionKeeper(getContext());
        View view = inflater.inflate(R.layout.login, container, false);
        password = (EditText)view.findViewById(R.id.password);
        user_name = (EditText)view.findViewById(R.id.user_name);

        login = (Button) view.findViewById(R.id.login);
        sign_up = (Button) view.findViewById(R.id.sign_up);
        login.setOnTouchListener(this);
        sign_up.setOnTouchListener(this);
        return view;

    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                return true;
            case MotionEvent.ACTION_UP:
                switch (view.getId()){
                    case R.id.login:
                        if(HouseKeeping.areFieldsEmpty(user_name, password)) {
                            Toast.makeText(getContext(), "Fill the fields you Muppet!", Toast.LENGTH_SHORT).show();
                            return true;
                        }
                        else {
                            try {
                                AsyncConnect asyncConnect = new AsyncConnect(getContext(),getString(R.string.link_login),jsonToPass());
                                asyncConnect.execute();
                                //correct
                                theSessionKeeper.setIsLoggedIn(user_name.getText().toString(), true);
                                tc.bringChange(loginSuccess);
                            }//frag for successful login
                            catch(Exception e){System.out.print(e);}
                        }
                        return true;

                    case R.id.sign_up:
                        tc.bringChange(signUp);
                        //frag for sign up
                        return true;
                }
        }
        return true;
    }



    boolean checkCredentials(){

        JSONObject jsonObject = new HouseKeeping().createJson("user_name",user_name.getText().toString(),"password",password.getText().toString());
        AsyncConnect asyncConnect = new AsyncConnect(getContext(),getString(R.string.link_login),jsonObject);
        asyncConnect.execute();

        //pass to async
        //check for boolean reply
        //profit
        return false;
    }

    JSONObject jsonToPass(){
        return new  HouseKeeping().createJson("name",user_name.getText().toString(),"password",password.getText().toString());
    }

}
