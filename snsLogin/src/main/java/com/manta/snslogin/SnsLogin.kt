package com.manta.snslogin

import android.app.Activity
import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.manta.snslogin.login.google.GoogleLoginLauncher
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient
import com.manta.snslogin.login.kakao.KakaoLogin
import com.manta.snslogin.login.naver.NaverLogin
import com.manta.snslogin.login.naver.NaverLoginResult
import com.manta.snslogin.login.twitter.TwitterLogin
import com.navercorp.nid.NaverIdLoginSDK

object SnsLogin {

    fun withFirebaseAuth(context: Context) = apply {
        FirebaseApp.initializeApp(context)
    }

    fun withKakaoLogin(context: Context, appKey: String) = apply {
        KakaoSdk.init(context, appKey)
    }

    fun withNaverLogin(
        context: Context,
        clientId: String,
        clientSecret: String,
        clientName: String
    ) {
        NaverIdLoginSDK.initialize(
            context,
            clientId,
            clientSecret,
            clientName
        )
    }

    fun googleLogin(defaultWebClientId: String): GoogleLoginLauncher.Builder {
        return GoogleLoginLauncher.Builder(defaultWebClientId)
    }

    fun googleLogout(activity: Activity) {
        Firebase.auth.signOut()
        val opt = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
        val client = GoogleSignIn.getClient(activity, opt)
        client.signOut()
        client.revokeAccess()
    }


    fun kakaoLogin(
        context: Context,
        onSuccess: (token: String) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        KakaoLogin.login(context, onSuccess, onFailure)
    }

    fun kakaoLogout(onSuccess: () -> Unit, onFailure: (Throwable) -> Unit) {
        KakaoLogin.logOut(onSuccess, onFailure)
    }

    fun naverLogin(
        context: Context,
        onSuccess: (NaverLoginResult) -> Unit,
        onFailure: (String) -> Unit
    ) {
        NaverLogin.login(context, onSuccess, onFailure)
    }


    fun naverLogout() {
        NaverLogin.logout()
    }

    fun getKeyHash(context: Context) = Utility.getKeyHash(context)

    fun twitterLogin(
        activity: Activity,
        onSuccess: (accessToken: String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        TwitterLogin.login(activity, onSuccess, onFailure)
    }

    fun twitterLogout(){
        TwitterLogin.logOut()
    }

}