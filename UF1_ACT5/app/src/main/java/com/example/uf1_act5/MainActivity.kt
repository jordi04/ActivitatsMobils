package com.example.uf1_act5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val goToOptionsButton: Button = findViewById(R.id.go_to_options_button)
        goToOptionsButton.setOnClickListener {
            val intent = Intent(this, OptionsActivity::class.java)
            startActivity(intent)
        }
    }
}
