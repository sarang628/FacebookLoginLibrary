package com.example.facebookloginlibrary

import com.example.loginlibrary.FacebookLoginProvider
import com.example.loginlibrary.FacebookLoginProviderImpl
import dagger.Module
import dagger.Provides

@Module
class FaceBookLoginModule {
    @Provides
    fun provideFacebooLoginProvider(): FacebookLoginProvider {
        return FacebookLoginProviderImpl()
    }
}