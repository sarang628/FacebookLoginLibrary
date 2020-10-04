package com.example.loginlibrary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.Arrays;

public class FacebookLoginProviderFakeImpl implements FacebookLoginProvider {

    private static final String TAG = "FacebookLoginProviderFakeImpl";
    private static FacebookLoginProviderFakeImpl facebookLoginProvider;
    private final CallbackManager callbackManager;
    private OnResultLoginListener onResultLoginListener;

    protected static FacebookLoginProviderFakeImpl getInstance() {
        if (facebookLoginProvider == null)
            synchronized (FacebookLoginProviderFakeImpl.class) {
                if (facebookLoginProvider == null)
                    facebookLoginProvider = new FacebookLoginProviderFakeImpl();
            }
        return facebookLoginProvider;
    }

    private FacebookLoginProviderFakeImpl() {
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {
                Log.e(TAG, "onCancel");
            }

            @Override
            public void onError(FacebookException error) {
            }
        });
    }

    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
    }

    @Override
    public void requestLogin(Activity activity) {

    }

    @Override
    public void requestLogin(Activity activity, OnResultLoginListener onResultLoginListener) {
        Log.d(TAG, "requestLogin");
    }

    @Override
    public boolean isLoggedIn() {
        return true;
    }

    @Override
    public void setOnLoginResultListener(OnResultLoginListener onResultLoginListener) {

    }

    @Override
    public void logout(OnResultLogoutListener onResultLogoutListener) {
        new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken,
                                                       AccessToken currentAccessToken) {
                if (currentAccessToken == null) {
                    //write your code here what to do when user logout
                    if (onResultLogoutListener != null) {
                        onResultLogoutListener.onResult(0);
                    }
                }
            }
        };

        LoginManager.getInstance().logOut();
    }

    @Override
    public String getToken() {
        return !isLoggedIn() ? "null" : AccessToken.getCurrentAccessToken().getToken();
    }

    @Override
    public void showStatusDialog(Context context) {
        FacebookLoginInfoDialog.Companion.show(context);
    }

}
