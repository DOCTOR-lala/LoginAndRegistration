package com.example.alienware.loginandregistration;

import android.content.Intent;
import android.provider.Settings;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import org.json.JSONObject;


public class    MainActivity extends AppCompatActivity implements ChangeFrag{


    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;
    Login login;
    LoginSuccess loginSuccess;
    TheSessionKeeper theSessionKeeper;
    CoordinatorLayout coordinatorLayout;
    static FrameLayout frameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coordinatorLayout = (CoordinatorLayout)findViewById(R.id.mainCoordinator);
        frameLayout = (FrameLayout)findViewById(R.id.frame);
        fragmentManager = getSupportFragmentManager();
        login = new Login();
        loginSuccess = new LoginSuccess();
        theSessionKeeper = TheSessionKeeper.getInstance(getApplicationContext());
        if (theSessionKeeper.isLoggedIn()) {
            //frag for login_success
            bringChange(loginSuccess);
        }
        else {
            //frag for login
            bringChange(login);
        }
    }



    @Override
    public void bringChange(Fragment fragment) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(frameLayout.getId(), fragment);
        fragmentTransaction.commit();
    }
}


