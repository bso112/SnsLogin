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
import com.manta.snslogin.login.google.GoogleAuthUtil
import com.manta.snslogin.login.kakao.KakaoAccessToken
import com.manta.snslogin.login.kakao.KakaoLogin
import com.manta.snslogin.login.naver.NaverLogin
import com.manta.snslogin.login.naver.NaverLoginResult
import com.manta.snslogin.login.twitter.TwitterLogin
import com.navercorp.nid.NaverIdLoginSDK


class NotInitializedError(authProvider: String) : Throwable("$authProvider SDK not been initialized!")

object SnsLogin {

    internal enum class AuthProvider {
        Google {
            override fun logout(activity: Activity) {
                googleLogout(activity)
            }
        }, Twitter {
            override fun logout(activity: Activity) {
                twitterLogout()
            }
        }, Kakao {
            override fun logout(activity: Activity) {
                kakaoLogout()
            }
        }, Naver {
            override fun logout(activity: Activity) {
                naverLogout()
            }
        };

        abstract fun logout(activity: Activity)
    }

    private val authProviders = mutableListOf<AuthProvider>()

    fun withFirebaseAuth(context: Context) = apply {
        FirebaseApp.initializeApp(context)
        authProviders.add(AuthProvider.Google)
        authProviders.add(AuthProvider.Twitter)
    }

    fun withKakaoLogin(context: Context, appKey: String) = apply {
        KakaoSdk.init(context, appKey)
        authProviders.add(AuthProvider.Kakao)
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
        authProviders.add(AuthProvider.Naver)
    }

    fun googleLogin(defaultWebClientId: String): GoogleLoginLauncher.Builder {
        checkSdkInitialized(AuthProvider.Google)
        return GoogleLoginLauncher.Builder(defaultWebClientId)
    }

    fun googleLogout(activity: Activity) {
        checkSdkInitialized(AuthProvider.Google)
        GoogleAuthUtil.signOut(activity)
    }

    fun logoutAll(activity: Activity){
        authProviders.forEach { it.logout(activity) }
    }

    private fun checkSdkInitialized(authProvider: AuthProvider){
        if(!authProviders.contains(authProvider)){
            throw NotInitializedError(authProvider.name)
        }
    }


    fun kakaoLogin(
        context: Context,
        onSuccess: (token: KakaoAccessToken) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        checkSdkInitialized(AuthProvider.Kakao)
        KakaoLogin.login(context, onSuccess, onFailure)
    }

    fun kakaoLogout(onSuccess: () -> Unit = {}, onFailure: (Throwable) -> Unit = {}) {
        checkSdkInitialized(AuthProvider.Kakao)
        KakaoLogin.logOut(onSuccess, onFailure)
    }

    fun naverLogin(
        context: Context,
        onSuccess: (NaverLoginResult) -> Unit,
        onFailure: (String) -> Unit
    ) {
        checkSdkInitialized(AuthProvider.Naver)
        NaverLogin.login(context, onSuccess, onFailure)
    }


    fun naverLogout() {
        checkSdkInitialized(AuthProvider.Naver)
        NaverLogin.logout()
    }

    fun twitterLogin(
        activity: Activity,
        onSuccess: (firebaseUserData: FirebaseUserData) -> Unit,
        onFailure: (String) -> Unit
    ) {
        checkSdkInitialized(AuthProvider.Twitter)
        TwitterLogin.login(activity, onSuccess, onFailure)
    }

    fun twitterLogout() {
        checkSdkInitialized(AuthProvider.Twitter)
        TwitterLogin.logOut()
    }

}