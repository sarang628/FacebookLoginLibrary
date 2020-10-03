package com.example.facebookloginlibrary

import com.example.loginlibrary.FaceBookLoginModule
import dagger.Component

@Component(modules = [FaceBookLoginModule::class])
interface FacebookComponent {
    fun inject(mainActivity: MainActivity)
}