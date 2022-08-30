package co.manta.snslogin.contract

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import co.manta.snslogin.util.Logger
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException

class GoogleSignInContract : ActivityResultContract<String, GoogleSignInAccount?>() {
    override fun createIntent(context: Context, input: String): Intent {
        return GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(input)
            .requestEmail()
            .build()
            .let {
                GoogleSignIn.getClient(context, it) //TODO 안됬던거 같은데..
            }.signInIntent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): GoogleSignInAccount? {
        return runCatching {
            GoogleSignIn.getSignedInAccountFromIntent(intent)
                .getResult(ApiException::class.java)
        }.onFailure {
            Logger.e(it)
        }.getOrNull()
    }
}