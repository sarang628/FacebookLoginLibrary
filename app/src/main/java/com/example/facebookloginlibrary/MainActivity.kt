package com.example.facebookloginlibrary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.facebookloginlibrary.databinding.ActivityMainBinding
import com.example.loginlibrary.FacebookLoginProvider
import com.example.loginlibrary.FacebookLoginProviderImpl
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var facebookLoginProvider: FacebookLoginProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        val activityMainActivity = ActivityMainBinding.inflate(LayoutInflater.from(this))
        super.onCreate(savedInstanceState)
        //Inject
        (application as FaceBookApplication).facebookComponent.inject(this)
        setContentView(activityMainActivity.root)

        facebookLoginProvider.setOnLoginResultListener {
            facebookLoginProvider.showStatusDialog(this)
            activityMainActivity.tvLogin.text =
                    "isLogin = ${facebookLoginProvider.isLoggedIn}\n" +
                            "token = ${facebookLoginProvider.token}"
        }


        activityMainActivity.tvLogin.text = "" +
                "isLogin = ${facebookLoginProvider.isLoggedIn}\n" +
                "token = ${facebookLoginProvider.token}"



        activityMainActivity.btnLogin.setOnClickListener {
            facebookLoginProvider.requestLogin(this, null)
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