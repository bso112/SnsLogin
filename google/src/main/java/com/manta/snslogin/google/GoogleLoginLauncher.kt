package com.manta.snslogin.google

import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import com.manta.snslogin.common.FirebaseUserData


/**
 * use R.string.default_web_client_id for [androidx.activity.result.ActivityResultLauncher.launch] input
 */
fun AppCompatActivity.registerForGoogleLoginResult(
    onSuccess: (FirebaseUserData) -> Unit,
    onFailure: (Throwable) -> Unit
): ActivityResultLauncher<String> {
    return registerForActivityResult(GoogleSignInContract()) { account ->
        GoogleLoginUtil.googleSignIn(
            activity = this,
            account = account,
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }
}