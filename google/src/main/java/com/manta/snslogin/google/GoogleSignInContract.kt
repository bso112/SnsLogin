package com.manta.snslogin.google

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.manta.snslogin.common.SnsLoginLogger

class GoogleSignInContract : ActivityResultContract<String, GoogleSignInAccount>() {

    /**
     * @param input default_web_client_id
     */
    override fun createIntent(context: Context, input: String): Intent {
        return GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(input)
            .requestEmail()
            .build()
            .let {
                GoogleSignIn.getClient(context, it)
            }.signInIntent
    }

    @Throws
    override fun parseResult(resultCode: Int, intent: Intent?): GoogleSignInAccount {
        return GoogleSignIn.getSignedInAccountFromIntent(intent)
            .getResult(ApiException::class.java)
    }
}