package co.manta.snslogin

import android.content.Context
import co.manta.snslogin.launcher.GoogleLoginLauncher
import com.google.firebase.FirebaseApp

object SnsLogin {

    fun initialize(context: Context) {
        FirebaseApp.initializeApp(context)
    }

    fun googleLogin(defaultWebClientId : String): GoogleLoginLauncher.Builder {
        return GoogleLoginLauncher.Builder(defaultWebClientId)
    }

}