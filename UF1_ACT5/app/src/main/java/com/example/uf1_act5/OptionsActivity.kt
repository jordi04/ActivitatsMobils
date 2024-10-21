package com.example.uf1_act5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class OptionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_options)

        val backButton: Button = findViewById(R.id.back_button)
        backButton.setOnClickListener {
            finish()
        }
    }
}
