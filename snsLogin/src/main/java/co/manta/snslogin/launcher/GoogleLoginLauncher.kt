package co.manta.snslogin.launcher

import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import co.manta.snslogin.util.FirebaseAuthUtil
import co.manta.snslogin.contract.GoogleSignInContract


class GoogleLoginLauncher(
    private val defaultWebClientId: String,
    private val launcher: ActivityResultLauncher<String>
) {

    fun launch() {
        launcher.launch(defaultWebClientId)
    }

    class Builder(private val defaultWebClientId: String) {
        private var onSuccessCallback: (String) -> Unit = {}
        private var onFailureCallback: (String) -> Unit = {}

        fun onSuccess(callback: (String) -> Unit) = apply {
            onSuccessCallback = callback
        }

        fun onFailure(callback: (String) -> Unit) = apply {
            onFailureCallback = callback
        }


        fun build(activity: AppCompatActivity): GoogleLoginLauncher {
            activity.registerForActivityResult(GoogleSignInContract()) { account ->
                account?.let {
                    FirebaseAuthUtil.googleSignIn(activity, it, onSuccess = { idToken ->
                        onSuccessCallback(idToken)
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