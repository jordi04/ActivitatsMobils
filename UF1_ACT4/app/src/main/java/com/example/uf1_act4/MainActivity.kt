package com.example.uf1_act4

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botoLogcat = findViewById<Button>(R.id.botoLogcat)
        val botoToast = findViewById<Button>(R.id.botoToast)
        val imatge = findViewById<ImageView>(R.id.imatge)

        botoLogcat.setOnClickListener {
            Log.d("MainActivity", "boto apretat")
        }

        botoToast.setOnClickListener {
            Toast.makeText(this, "boto apretat", Toast.LENGTH_SHORT).show()
        }
    }
}
