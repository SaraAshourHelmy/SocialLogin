package com.sara.FacebookLogin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.sara.sociallogin.R;

public class FacebookActivity extends AppCompatActivity {

    TextView tv_info;
    LoginButton loginButton;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_facebook);


        tv_info = (TextView) findViewById(R.id.tv_user_facebook);
        loginButton = (LoginButton) findViewById(R.id.login_button);
        SetupTools();

    }

    private void SetupTools() {

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                Log.e("login", "success");
                Profile profile = Profile.getCurrentProfile();
                tv_info.setText("hi " + profile.getFirstName() + " " +
                        profile.getLastName());
            }

            @Override
            public void onCancel() {

                Log.e("login", "cancel");
            }

            @Override
            public void onError(FacebookException error) {

                Log.e("login", "error");
            }
        });
    }


}
