package com.example.alienware.loginandregistration;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.HandlerThread;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.logging.Handler;

/**
 * Created by Alienware on 17-01-2017.
 */

public class LoginSuccess extends Fragment {

    TextView name,userName,email,dateOfCreation,uid;
    TheSessionKeeper theSessionKeeper;
    Button logout;
    ChangeFrag tc;
    CoordinatorLayout coordinatorLayout;
    Login login;
    JSONObject jsonObject;
    String errorMessage = "beeepboooppp! errrr!!!! Looks like you're trapped here for Ever :{) ";
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        tc = (ChangeFrag)getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        login = new Login();
        View view = inflater.inflate(R.layout.login_success,container,false);
        coordinatorLayout = (CoordinatorLayout)view.findViewById(R.id.coordinator_loginsuccess);
        theSessionKeeper = TheSessionKeeper.getInstance(getContext());
        uid = (TextView)view.findViewById(R.id.uid);
        name = (TextView)view.findViewById(R.id.nameOfUser);
        userName = (TextView)view.findViewById(R.id.userName);
        email = (TextView)view.findViewById(R.id.eMail);
        dateOfCreation = (TextView)view.findViewById(R.id.dateOfCreation);

        uid.setText(theSessionKeeper.get("uid"));
        //userName.setText(theSessionKeeper.get("user_name"));
        userName.setText(theSessionKeeper.get("user_name"));
        name.setText(theSessionKeeper.get("name"));
        email.setText(theSessionKeeper.get("email"));
        dateOfCreation.setText(theSessionKeeper.get("dateOfCreation"));


        logout = (Button)view.findViewById(R.id.logout);
        logout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        //frag for login
                        try {
                            jsonObject = new JSONObject();
                            jsonObject.put("uid", theSessionKeeper.get("uid"));
                        }catch (JSONException e){e.printStackTrace();}

                        new AsyncConnect(getContext(),getString(R.string.link_logOut),jsonObject, new AsyncConnect.AsyncRevert() {
                            @Override
                            public void getJsonResponse(JSONObject jsonObject) {
                                try {
                                    if (jsonObject.getBoolean("error")) {
                                        Snackbar.make(coordinatorLayout, errorMessage, Snackbar.LENGTH_SHORT).show();
                                        //Toast.makeText(getContext(),errorMessage,Toast.LENGTH_SHORT).show();
                                    }else {
                                        theSessionKeeper.logOut();
                                        tc.bringChange(login);
                                    }
                                }catch (JSONException e){e.printStackTrace();}

                            }
                        }).execute();

                        return true;
                }
                return true;
            }
        });
        return view;
    }

}
