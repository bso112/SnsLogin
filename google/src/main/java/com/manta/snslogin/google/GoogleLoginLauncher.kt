package com.manta.snslogin.google

import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher


/**
 * use R.string.default_web_client_id for [androidx.activity.result.ActivityResultLauncher.launch] input
 */
fun ComponentActivity.registerForGoogleLoginResult(
    onSuccess: (FirebaseUserData) -> Unit,
    onFailure: (Throwable) -> Unit,
): ActivityResultLauncher<String> {
    return registerForActivityResult(GoogleSignInContract(onFailure)) { account ->
        if (account == null) {
            onFailure(IllegalStateException("GoogleSignInAccount is null"))
            return@registerForActivityResult
        }
        GoogleLoginUtil.googleSignIn(
            activity = this,
            account = account,
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }
}