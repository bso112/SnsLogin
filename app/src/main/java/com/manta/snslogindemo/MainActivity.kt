package com.manta.snslogindemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.manta.snslogin.SnsLogin
import com.manta.snslogin.login.google.GoogleLoginLauncher

/**
 * this demo will not work properly.
 * Because it needs to be set all configuration on auth provider dashboard.
 * For example, you need to create Firebase project to use [SnsLogin.googleLogin], and configure all setting it needs
 */
class MainActivity : AppCompatActivity() {

    private val googleLoginLauncher: GoogleLoginLauncher by lazy {
        SnsLogin.googleLogin(getString(R.string.default_web_client_id))
            .onSuccess { googleUser ->
                Toast.makeText(this, "Login Succeed! token : $googleUser", Toast.LENGTH_LONG).show()
                Log.d(javaClass.simpleName, "Login Succeed! token : $googleUser")
            }.onFailure {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
                Log.d(javaClass.simpleName, "Login Failed! error: $it")
            }.build(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        googleLoginLauncher.launch()

//        SnsLogin.kakaoLogin(this, onSuccess = {
//            Toast.makeText(this, "Login Succeed! token : $it", Toast.LENGTH_LONG).show()
//        }, onFailure = {
//            Toast.makeText(this, "Login Failed! error: $it", Toast.LENGTH_LONG).show()
//            Log.d(javaClass.simpleName, "Login Failed! error: $it")
//        })
//
//        SnsLogin.naverLogin(this, onSuccess = {
//            Toast.makeText(this, "Login Succeed! token : $it", Toast.LENGTH_LONG).show()
//        }, onFailure = {
//            Toast.makeText(this, "Login Failed! error: $it", Toast.LENGTH_LONG).show()
//        })
//
//
//        SnsLogin.twitterLogin(this, onSuccess = {
//            Toast.makeText(this, "Login Succeed! token : $it", Toast.LENGTH_LONG).show()
//        }, onFailure = {
//            Toast.makeText(this, "Login Failed! error: $it", Toast.LENGTH_LONG).show()
//        })

    }
}