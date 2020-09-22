package com.example.facebookloginlibrary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.facebookloginlibrary.databinding.ActivityMainBinding
import com.example.loginlibrary.FacebookLoginProvider
import com.example.loginlibrary.FacebookLoginProviderImpl

class MainActivity : AppCompatActivity() {
    val facebookLoginProvider = FacebookLoginProviderImpl()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainActivity = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(activityMainActivity.root)
        activityMainActivity.tvLogin.text = "" +
                "isLogin = ${facebookLoginProvider.isLoggedIn}\n" +
                "token = ${facebookLoginProvider.token}"

        activityMainActivity.btnLogin.setOnClickListener {
            facebookLoginProvider.requestLogin(this) {
                facebookLoginProvider.showStatusDialog(this)
                activityMainActivity.tvLogin.text =
                    "isLogin = ${facebookLoginProvider.isLoggedIn}\n" +
                            "token = ${facebookLoginProvider.token}"
            }
        }

        activityMainActivity.btnLogout.setOnClickListener {
            facebookLoginProvider.logout {
                facebookLoginProvider.showStatusDialog(this)
                activityMainActivity.tvLogin.text =
                    "isLogin = ${facebookLoginProvider.isLoggedIn}\n" +
                            "token = ${facebookLoginProvider.token}"
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        facebookLoginProvider.onActivityResult(requestCode, resultCode, data)
    }
}