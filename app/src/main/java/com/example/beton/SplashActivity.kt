package com.example.beton

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.lang.Exception

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Thread {
            try {
                Thread.sleep(3000)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                startActivity(Intent(
                    this,
                    SignInActivity::class.java
                ))
            }
        }.start()
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}