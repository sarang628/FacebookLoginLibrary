package com.example.facebookloginlibrary

import android.app.Application

class FaceBookApplication : Application() {
    val facebookComponent = DaggerFacebookComponent.builder().build()
}