package com.manta.snslogindemo

import android.app.Application
import com.manta.snslogin.SnsLogin

class SnsLoginApp : Application() {

    override fun onCreate() {
        super.onCreate()
        SnsLogin
            .withGoogle(this)
            .withTwitter(this)
            .withKakao(this, BuildConfig.KAKAO_APP_KEY)
            .withNaver(this, clientId = "", clientSecret = "", clientName = "")


    }
}