package com.manta.snslogin.twitter

import android.app.Activity
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*
import com.manta.snslogin.common.FirebaseUserData

object TwitterLogin {

    fun login(
        activity: Activity,
        onSuccess: (firebaseUserData: FirebaseUserData) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val provider = OAuthProvider.newBuilder("twitter.com")
        val firebaseAuth = FirebaseAuth.getInstance()
        val pendingResultTask: Task<AuthResult>? = firebaseAuth.pendingAuthResult
        if (pendingResultTask != null) {
            pendingResultTask
                .addOnSuccessListener(
                    OnSuccessListener { authResult ->
                        handleAuthResult(authResult, onSuccess, onFailure)
                    })
                .addOnFailureListener {
                    onFailure(it.message.orEmpty())
                }
        } else {
            firebaseAuth
                .startActivityForSignInWithProvider(activity, provider.build())
                .addOnSuccessListener { authResult ->
                    handleAuthResult(authResult, onSuccess, onFailure)
                }
                .addOnFailureListener {
                    onFailure(it.message.orEmpty())
                }
        }
    }

    fun logOut() {
        FirebaseAuth.getInstance().signOut()
    }

    private fun handleAuthResult(
        authResult: AuthResult,
        onSuccess: (firebaseUserData: FirebaseUserData) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val user = authResult.user ?: run {
            onFailure("Fail to sign in with firebase")
            return
        }

        user.getIdToken(true).addOnSuccessListener { result ->
            if (result.token != null && result.token.orEmpty().isNotBlank()) {
                FirebaseUserData(
                    email = user.email.orEmpty(),
                    idToken = result.token.orEmpty(),
                    displayName = user.displayName.orEmpty(),
                    phoneNumber = user.phoneNumber.orEmpty(),
                    photoUrl = user.photoUrl.toString(),
                    isEmailVerified = user.isEmailVerified,
                    isAnonymous = user.isAnonymous,
                    providerId = user.providerId,
                    uid = user.uid
                ).also {
                    onSuccess(it)
                }
            } else {
                onFailure("invalid firebase token")
            }
        }
    }

}