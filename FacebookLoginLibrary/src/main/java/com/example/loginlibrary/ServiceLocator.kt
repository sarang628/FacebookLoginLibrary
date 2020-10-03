package com.example.loginlibrary

import android.util.Log

class ServiceLocator {
    companion object {
        fun get(): FacebookLoginProvider {
            if (BuildConfig.BUILD_TYPE.equals("debug")) {
                Log.d("sarang", "FacebookLoginProviderImpl")
                return FacebookLoginProviderImpl.getInstance()
            } else {
                Log.d("sarang", "FacebookLoginProviderImpl")
                return FacebookLoginProviderImpl.getInstance()
            }
        }
    }
}