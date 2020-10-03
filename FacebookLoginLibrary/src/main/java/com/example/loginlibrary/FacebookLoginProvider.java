package com.example.loginlibrary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public interface FacebookLoginProvider {

    /**
     * 로그인 요청
     * @param activity
     * @param onResultLoginListener setOnLoginResultListener를 통해 추가했을경우 null 처리해도 됩니다.
     */
    void requestLogin(Activity activity, OnResultLoginListener onResultLoginListener);

    /**
     * 현재 로그인 상태
     */
    boolean isLoggedIn();

    void setOnLoginResultListener(OnResultLoginListener onResultLoginListener);

    /**
     * 로그아웃 요청
     *
     * @param onResultLogoutListener
     */
    void logout(OnResultLogoutListener onResultLogoutListener);

    /**
     * 토큰요청
     *
     * @return 토큰
     */
    String getToken();

    /**
     * 현재 상태 보여주는 다이얼로그
     *
     * @param context
     */
    void showStatusDialog(Context context);

    void onActivityResult(final int requestCode, final int resultCode, final Intent data);

    /**
     * 로그인 결과 리스너
     */
    interface OnResultLoginListener { void onResult(String accessToken);}

    /**
     * 로그아웃 결과 리스너
     */
    interface OnResultLogoutListener { void onResult(int result);}
}
