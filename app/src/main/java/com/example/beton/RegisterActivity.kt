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
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    private lateinit var logInButton: TextView

    private lateinit var auth: FirebaseAuth

    private lateinit var nameRegister: EditText
    private lateinit var emailRegister: EditText
    private lateinit var passwordRegister: EditText
    private lateinit var confirmPasswordRegister: EditText

    private lateinit var registerButton: Button

    private lateinit var database: FirebaseDatabase
    private lateinit var users: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = Firebase.auth

        database = Firebase.database
        users = database.reference.child("users")

        logInButton = findViewById(R.id.logInButton)

        nameRegister = findViewById(R.id.nameRegister)
        emailRegister = findViewById(R.id.emailRegister)
        passwordRegister = findViewById(R.id.passwordRegister)
        confirmPasswordRegister = findViewById(R.id.confirmPasswordRegister)

        registerButton = findViewById(R.id.registerButton)

        registerButton.setOnClickListener {
            registerUser(
                emailRegister.text.toString().trim(),
                passwordRegister.text.toString().trim(),
                confirmPasswordRegister.text.toString().trim()
            )
        }

        logInButton.setOnClickListener {
            startActivity(
                Intent(
                this,
                SignInActivity::class.java
                )
            )
        }
    }

    private fun registerUser(email: String, password: String, confirmPassword: String) {
        if (password == confirmPassword) {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("Register", "createUserWithEmail:success")
                        val user = auth.currentUser
                        addUserToDatabase(user);
                    } else {
                        Log.w("Register", "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }


                }
        } else {
            Toast.makeText(
                baseContext, "Password don't match.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun addUserToDatabase(currentUser: FirebaseUser?) {
        val user = User()
        user.uid = currentUser?.uid
        user.email = currentUser?.email
        user.name = nameRegister.text.toString().trim()

        users.push().setValue(user)
    }
}