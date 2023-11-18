package com.manta.snslogindemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.manta.snslogin.google.registerForGoogleLoginResult

class MainActivity : AppCompatActivity() {

    private val googleLoginLauncher = registerForGoogleLoginResult(
        onSuccess = {
            Toast.makeText(this, "success", Toast.LENGTH_SHORT).show()
        },
        onFailure = {
            Toast.makeText(this, "failure", Toast.LENGTH_SHORT).show()
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        googleLoginLauncher.launch()

    }


}