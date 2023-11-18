package com.manta.snslogindemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.manta.snslogin.google.registerForGoogleLoginResult

class MainActivity : AppCompatActivity() {

//    private val googleLoginLauncher = registerForGoogleLoginResult(
//        onSuccess = {
//            Toast.makeText(this, "success $it", Toast.LENGTH_SHORT).show()
//            Log.d("mylog", "success: $it")
//        },
//        onFailure = {
//            Toast.makeText(this, "failure $it", Toast.LENGTH_SHORT).show()
//            Log.d("mylog", "failure: $it")
//        },
//    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        googleLoginLauncher.launch(getString(R.string.default_web_client_id))
    }


}