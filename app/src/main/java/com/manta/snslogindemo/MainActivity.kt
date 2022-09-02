package com.manta.snslogindemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.manta.snslogin.SnsLogin
import com.manta.snslogin.login.google.GoogleLoginLauncher

class MainActivity : AppCompatActivity() {

    private val googleLoginLauncher: GoogleLoginLauncher by lazy {
        SnsLogin.googleLogin(getString(R.string.default_web_client_id))
            .onSuccess { idToken ->
                Toast.makeText(this, "Login Succeed! token : $idToken", Toast.LENGTH_LONG).show()
            }.onFailure {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }.build(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("my", SnsLogin.getKeyHash(this))
        //googleLoginLauncher.launch()

        SnsLogin.kakaoLogin(this, onSuccess = {
            Toast.makeText(this, "Login Succeed! token : $it", Toast.LENGTH_LONG).show()
        }, onFailure = {
            Toast.makeText(this, "Login Failed! error: $it", Toast.LENGTH_LONG).show()
        })

    }
}