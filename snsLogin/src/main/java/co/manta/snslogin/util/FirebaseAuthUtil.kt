package co.manta.snslogin.util

import android.app.Activity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import java.util.concurrent.CountDownLatch


internal object FirebaseAuthUtil {

    internal fun googleSignIn(
        activity: Activity,
        account: GoogleSignInAccount,
        onSuccess: (token: String) -> Unit,
        onFailure: (errMsg: String) -> Unit
    ) {
        val googleIdToken = account.idToken.takeIf { it?.isNotBlank().toSafe() }
        if (googleIdToken == null) {
            onFailure("invalid google id token")
        } else {
            val credential = GoogleAuthProvider.getCredential(googleIdToken, null)
            val auth = Firebase.auth
            auth.signInWithCredential(credential)
                .addOnSuccessListener(activity) {
                    auth.currentUser?.getIdToken(true)?.addOnSuccessListener { result ->
                        if (result.token != null && result.token.toSafe().isNotBlank()) {
                            onSuccess(result.token.toSafe())
                        } else {
                            onFailure("invalid firebase token")
                            singOut(activity)
                        }
                    }
                }
        }
    }

    internal fun singOut(activity: Activity) {
        Firebase.auth.signOut()
        val opt = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
        val client = GoogleSignIn.getClient(activity, opt)
        client.signOut()
        client.revokeAccess()
    }

    internal fun isUserLogin(): Boolean {
        return (Firebase.auth.currentUser != null)
    }


    internal fun refreshIdToken() = callbackFlow {
        Firebase.auth.currentUser?.getIdToken(true)?.addOnSuccessListener { result ->
            trySend(result.token.toSafe())
        }?.addOnFailureListener {
            close(it)
        }
        awaitClose()
    }

    /**
     * 토큰을 리프레시할 동안 현재 스레드를 블록한다.
     * ANR이 발생할 수 있으니 주의해서 사용해야한다.
     */
    internal fun refreshIdTokenSync(onFailure: (Exception) -> Unit): String {
        val lock = CountDownLatch(1)
        var token = ""
        Firebase.auth.currentUser?.getIdToken(true)?.addOnSuccessListener { result ->
            token = result.token.toSafe()
            lock.countDown()
        }?.addOnFailureListener {
            onFailure(it)
            lock.countDown()
        }
        lock.await()
        return token
    }

}