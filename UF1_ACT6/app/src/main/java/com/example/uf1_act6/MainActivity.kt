package com.example.uf1_act6

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText: EditText = findViewById(R.id.edit_text)
        val button: Button = findViewById(R.id.submit_button)

        button.setOnClickListener {
            val inputText = editText.text.toString()
            Toast.makeText(this, inputText, Toast.LENGTH_SHORT).show()
        }
    }
}
