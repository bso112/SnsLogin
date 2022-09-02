package com.manta.snslogindemo

import android.app.Application
import com.manta.snslogin.SnsLogin

class SnsLoginApp : Application() {

    override fun onCreate() {
        super.onCreate()
        SnsLogin
            .withGoogleLogin(this)
            .withKakaoLogin(this, BuildConfig.KAKAO_APP_KEY)

    }
}