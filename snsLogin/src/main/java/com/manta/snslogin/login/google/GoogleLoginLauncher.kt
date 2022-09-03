package com.manta.snslogin.login.google

import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity


class GoogleLoginLauncher(
    private val defaultWebClientId: String,
    private val launcher: ActivityResultLauncher<String>
) {

    fun launch() {
        launcher.launch(defaultWebClientId)
    }

    class Builder(private val defaultWebClientId: String) {
        private var onSuccessCallback: (GoogleUser) -> Unit = {}
        private var onFailureCallback: (String) -> Unit = {}

        fun onSuccess(callback: (GoogleUser) -> Unit) = apply {
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
                    FirebaseAuthUtil.googleSignIn(activity, account, onSuccess = { googleUser ->
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