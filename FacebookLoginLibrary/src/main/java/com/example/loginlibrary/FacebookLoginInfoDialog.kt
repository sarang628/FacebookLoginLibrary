package com.example.loginlibrary

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import com.example.loginlibrary.databinding.LayoutLoginInfoBinding

private class FacebookLoginInfoDialog(context: Context) : Dialog(context) {
    companion object {
        fun show(context: Context) {
            FacebookLoginInfoDialog(context).show()
        }
    }

    init {
        val viewBinding = LayoutLoginInfoBinding.inflate(LayoutInflater.from(context))
        setContentView(viewBinding.root)

        val fbLoginProvider = FacebookLoginProviderImpl.getInstance()

        viewBinding.textView.text = "is login = ${fbLoginProvider.isLoggedIn}"

        viewBinding.token.text = "token = ${fbLoginProvider.token}"
    }


}