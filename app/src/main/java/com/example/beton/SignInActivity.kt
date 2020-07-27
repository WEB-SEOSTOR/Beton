package com.example.beton

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SignInActivity : AppCompatActivity() {
    private lateinit var signUpButton: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        signUpButton = findViewById(R.id.SignUpButton)

        signUpButton.setOnClickListener {
            startActivity(Intent(
                this,
                RegisterActivity::class.java
            ))
        }
    }
}