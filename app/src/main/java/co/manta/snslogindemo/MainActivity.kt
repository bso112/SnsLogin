package co.manta.snslogindemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.manta.snslogin.SnsLogin

class MainActivity : AppCompatActivity() {

    val googleLoginLauncher = SnsLogin.googleLogin("loginOption")
        .onSuccess { idToken ->

        }.onFailure {

        }.build(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        SnsLogin.initialize(this)

        googleLoginLauncher.launch()

    }
}