package com.manta.snslogin.login.google

import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import com.manta.snslogin.FirebaseUserData


class GoogleLoginLauncher(
    private val defaultWebClientId: String,
    private val launcher: ActivityResultLauncher<String>
) {

    fun launch() {
        launcher.launch(defaultWebClientId)
    }

    class Builder(private val defaultWebClientId: String) {
        private var onSuccessCallback: (FirebaseUserData) -> Unit = {}
        private var onFailureCallback: (String) -> Unit = {}

        fun onSuccess(callback: (FirebaseUserData) -> Unit) = apply {
            onSuccessCallback = callback
        }

        fun onFailure(callback: (String) -> Unit) = apply {
            onFailureCallback = callback
        }


        fun build(activity: AppCompatActivity): GoogleLoginLauncher {
            activity.registerForActivityResult(GoogleSignInContract()) { account ->
                if (account == null) {
                    onFailureCallback("Failed to login with google")
                } else {
                    GoogleAuthUtil.googleSignIn(activity, account, onSuccess = { googleUser ->
                        onSuccessCallback(googleUser)
                    }, onFailure = { msg ->
                        onFailureCallback(msg)
                    })
                }
            }.also {
                return GoogleLoginLauncher(defaultWebClientId, it)
            }
        }
    }
}