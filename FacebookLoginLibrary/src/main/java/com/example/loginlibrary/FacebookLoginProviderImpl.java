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

public class FacebookLoginProviderImpl implements FacebookLoginProvider {

    private static final String TAG = "FacebookLoginProvider";
    private static FacebookLoginProviderImpl facebookLoginProvider;
    private final CallbackManager callbackManager;
    private OnResultLoginListener onResultLoginListener;

    public static FacebookLoginProviderImpl getInstance() {
        if (facebookLoginProvider == null)
            synchronized (FacebookLoginProviderImpl.class) {
                if (facebookLoginProvider == null)
                    facebookLoginProvider = new FacebookLoginProviderImpl();
            }
        return facebookLoginProvider;
    }

    public FacebookLoginProviderImpl() {
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.i(TAG, "onSuccess: ");
                Log.i(TAG, loginResult.getAccessToken().toString());
                Log.i(TAG, loginResult.getRecentlyDeniedPermissions().toString());
                Log.i(TAG, loginResult.getRecentlyGrantedPermissions().toString());
                if (onResultLoginListener != null) {
                    onResultLoginListener.onResult(loginResult.getAccessToken().getToken());
                }
            }

            @Override
            public void onCancel() {
                Log.e(TAG, "onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.e(TAG, "onError" + error.toString());
                LoginManager.getInstance().logOut();
            }
        });
    }

    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        if (callbackManager != null) {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        } else {
            Log.e(TAG, "callbackManager is null");
        }
    }

    @Override
    public void requestLogin(Activity activity, OnResultLoginListener onResultLoginListener) {
        this.onResultLoginListener = onResultLoginListener;
        LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("public_profile", "email"));
    }

    @Override
    public boolean isLoggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
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
