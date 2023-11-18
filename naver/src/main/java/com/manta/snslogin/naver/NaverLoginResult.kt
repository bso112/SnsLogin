package com.manta.snslogin.naver

data class NaverLoginResult(
    val accessToken: String,
    val refreshToken: String,
    val tokenType: String,
    val expire : String,
    val status : String
)