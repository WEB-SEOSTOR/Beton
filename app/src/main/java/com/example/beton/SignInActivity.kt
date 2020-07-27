package com.example.beton

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignInActivity : AppCompatActivity() {
    private lateinit var signUpButton: TextView

    private lateinit var nameTextEdit: EditText
    private lateinit var passwordTextEdit: EditText
    private lateinit var logIn: Button

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        auth = Firebase.auth

        signUpButton = findViewById(R.id.SignUpButton)

        nameTextEdit = findViewById(R.id.nameTextEdit)
        passwordTextEdit = findViewById(R.id.passwordEditText)
        logIn = findViewById(R.id.logIn)

        logIn.setOnClickListener {
            logInAction(nameTextEdit.text.toString().trim(), passwordTextEdit.text.toString().trim())
        }

        signUpButton.setOnClickListener {
            startActivity(Intent(
                this,
                RegisterActivity::class.java
            ))
        }
    }

    private fun logInAction(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("login", "signInWithEmail:success")
                    val user = auth.currentUser
                    startActivity(Intent(
                        this,
                        HomeActivity::class.java
                    ))
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("login", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    //updateUI(null)
                }

            }
    }
}