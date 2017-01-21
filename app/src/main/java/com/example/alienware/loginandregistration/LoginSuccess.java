package com.example.alienware.loginandregistration;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Alienware on 17-01-2017.
 */

public class LoginSuccess extends Fragment {

    TextView textView;
    TheSessionKeeper theSessionKeeper;
    Button logout;
    ChangeFrag tc;
    Login login;


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
        theSessionKeeper = new TheSessionKeeper(getContext());
        textView = (TextView)view.findViewById(R.id.nameOfUser);
        textView.setText(theSessionKeeper.whoIsLoggedIn());
        logout = (Button)view.findViewById(R.id.logout);
        logout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        //frag for login
                        theSessionKeeper.setIsLoggedIn(textView.getText().toString(),false);
                        tc.bringChange(login);

                        return true;
                }
                return true;
            }
        });
        return view;
    }


}
