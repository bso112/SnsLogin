package com.manta.snslogin

import android.content.Context
import com.manta.snslogin.login.google.GoogleLoginLauncher
import com.google.firebase.FirebaseApp
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient
import com.manta.snslogin.login.kakao.KakaoLogin

object SnsLogin {

    fun withGoogleLogin(context: Context) = apply {
        FirebaseApp.initializeApp(context)
    }

    fun withKakaoLogin(context: Context, appKey: String) = apply {
        KakaoSdk.init(context, appKey)
    }

    fun googleLogin(defaultWebClientId: String): GoogleLoginLauncher.Builder {
        return GoogleLoginLauncher.Builder(defaultWebClientId)
    }

    fun getKeyHash(context: Context) = Utility.getKeyHash(context)


    fun kakaoLogin(
        context: Context,
        onSuccess: (token: String) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        KakaoLogin.login(context, onSuccess, onFailure)
    }


}