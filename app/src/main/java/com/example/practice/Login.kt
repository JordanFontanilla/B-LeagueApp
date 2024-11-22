package com.example.practice

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Login : AppCompatActivity() {

    private lateinit var titleTextView: TextView
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var forgotPasswordTextView: TextView
    private lateinit var signUpTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        titleTextView = findViewById(R.id.titleTextView)
        usernameEditText = findViewById(R.id.email)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)
        forgotPasswordTextView = findViewById(R.id.forgotPasswordTextView)
        signUpTextView = findViewById(R.id.signUpTextView)

        // Set up click listeners
        loginButton.setOnClickListener { onLoginClicked() }
        forgotPasswordTextView.setOnClickListener { onForgotPasswordClicked() }
        signUpTextView.setOnClickListener { onSignUpClicked() }
    }

    private fun onLoginClicked() {
        val username = usernameEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter both username and password", Toast.LENGTH_SHORT)
                .show()
            return
        }

        // TODO: Implement actual login logic here
        // For now, we'll just show a success message
        Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun onForgotPasswordClicked() {
        // TODO: Implement forgot password functionality
        Toast.makeText(this, "Forgot password clicked", Toast.LENGTH_SHORT).show()
    }

    private fun onSignUpClicked() {
        // TODO: Implement sign up functionality
        Toast.makeText(this, "Sign up clicked", Toast.LENGTH_SHORT).show()

    }
}