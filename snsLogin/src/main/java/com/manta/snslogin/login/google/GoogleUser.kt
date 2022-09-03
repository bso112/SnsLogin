package com.manta.snslogin.login.google


data class GoogleUser(
    val idToken : String,
    val email : String,
    val displayName: String,
    val phoneNumber: String,
    val photoUrl : String,
    val isEmailVerified : Boolean,
    val isAnonymous : Boolean,
    val providerId : String,
    val uid: String
)
