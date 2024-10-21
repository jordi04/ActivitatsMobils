package com.example.uf1_act7

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usernameEditText = findViewById<EditText>(R.id.username_edit_text).apply {}
        passwordEditText = findViewById<EditText>(R.id.password_edit_text).apply {}
        loginButton = findViewById<Button>(R.id.login_button)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()


            Toast.makeText(this, "Username: $username, Password: $password", Toast.LENGTH_SHORT).show()

            Log.d("LoginActivity", "Username: $username, Password: $password")
        }
    }
}
