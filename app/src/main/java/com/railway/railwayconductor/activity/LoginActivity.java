package com.railway.railwayconductor.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import com.railway.railwayconductor.R;
import com.railway.railwayconductor.activity.listener.LoginActivityLoginClick;
//import com.railway.railwayconductor.activity.listeners.LoginActivityLoginClick;
//import com.railway.railwayconductor.activity.listeners.LoginActivityRegisterClick;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends Activity {
    private Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //add handler for login
        this.login    = (Button)findViewById(R.id.login_btn_sign_in);
        login.setOnClickListener(new LoginActivityLoginClick());
    }

    public void disableButtons(){
        this.login.setEnabled(false);
    }

    public void enableButtons(){
        this.login.setEnabled(true);
    }

}

