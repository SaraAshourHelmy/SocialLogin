package com.sara.sociallogin;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.sara.FacebookLogin.FacebookLogin;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        FacebookLogin login = (FacebookLogin) getFragmentManager().findFragmentByTag("facebook");
        login.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        SetupTools();

    }

    private void SetupTools() {
        FragmentManager fragmentManager = getFragmentManager();
        FacebookLogin login = new FacebookLogin();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.lnr_facebook, login, "facebook");
        transaction.commit();

    }


}
