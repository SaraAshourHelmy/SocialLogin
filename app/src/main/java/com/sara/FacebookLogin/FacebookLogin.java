package com.sara.FacebookLogin;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.sara.sociallogin.R;

public class FacebookLogin extends Fragment {

    LoginButton btn_login;
    CallbackManager callbackManager;
    TextView tv_info;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // initialize facebook sdk

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        FacebookSdk.sdkInitialize(getActivity());

        // initialize callback
        callbackManager = CallbackManager.Factory.create();
        View view = inflater.inflate(R.layout.fragment_facebook, container, false);
        btn_login = (LoginButton) view.findViewById(R.id.login_button);
        tv_info = (TextView) view.findViewById(R.id.tv_user_facebook);
        SetupFacebookLogin();

        return view;
    }

    private void SetupFacebookLogin() {

        // btn_login.setReadPermissions("email");
        //  btn_login.setFragment(this);
        //btn_login.setFragment;

        // Callback registration

        btn_login.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {


                Profile profile = Profile.getCurrentProfile();
                tv_info.setText(profile.getFirstName() + " - " + profile.getLastName());
                Log.e("login", "success " + profile.getLastName());
            }

            @Override
            public void onCancel() {

                Toast.makeText(getActivity(), "Cancel", Toast.LENGTH_SHORT).show();
                Log.e("login", "cancel");
            }

            @Override
            public void onError(FacebookException error) {

                Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                Log.e("login", "error");
            }
        });
    }
}
