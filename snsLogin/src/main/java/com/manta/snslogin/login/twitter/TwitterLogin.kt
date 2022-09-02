package com.manta.snslogin.login.twitter

import android.app.Activity
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.OAuthCredential
import com.google.firebase.auth.OAuthProvider
import com.manta.snslogin.util.toSafe


object TwitterLogin {

    fun login(
        activity: Activity,
        onSuccess: (accessToken: String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val provider = OAuthProvider.newBuilder("twitter.com")
        val firebaseAuth = FirebaseAuth.getInstance()
        val pendingResultTask: Task<AuthResult>? = firebaseAuth.pendingAuthResult
        if (pendingResultTask != null) {
            // There's something already here! Finish the sign-in for your user.
            pendingResultTask
                .addOnSuccessListener(
                    OnSuccessListener { authResult ->
                        val accessToken = (authResult.credential as? OAuthCredential)?.accessToken
                        if (accessToken != null) {
                            onSuccess(accessToken)
                        } else {
                            onFailure("Try to login with twitter. but access token is null")
                        }
                    })
                .addOnFailureListener {
                    onFailure(it.message.toSafe())
                }
        } else {
            // There's no pending result so you need to start the sign-in flow.
            // See below.
            firebaseAuth
                .startActivityForSignInWithProvider( /* activity= */activity, provider.build())
                .addOnSuccessListener { authResult ->
                    val accessToken = (authResult.credential as? OAuthCredential)?.accessToken
                    if (accessToken != null) {
                        onSuccess(accessToken)
                    } else {
                        onFailure("Try to login with twitter. but access token is null")
                    }
                }
                .addOnFailureListener {
                    onFailure(it.message.toSafe())
                }
        }
    }

    fun logOut(){
        FirebaseAuth.getInstance().signOut()
    }
}