package com.example.uf1_act8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val checkBoxAssignatura1 = findViewById<CheckBox>(R.id.assignatura1)
        val checkBoxAssignatura2 = findViewById<CheckBox>(R.id.assignatura2)
        val checkBoxAssignatura3 = findViewById<CheckBox>(R.id.assignatura3)
        val checkBoxAssignatura4 = findViewById<CheckBox>(R.id.assignatura4)
        val radioGroupTorn = findViewById<RadioGroup>(R.id.radioGroupTorn)
        val buttonSubmit = findViewById<Button>(R.id.buttonSubmit)

        buttonSubmit.setOnClickListener {
            val selectedSubjects = mutableListOf<String>()
            if (checkBoxAssignatura1.isChecked) selectedSubjects.add("Interficies")
            if (checkBoxAssignatura2.isChecked) selectedSubjects.add("PSP (Useful)")
            if (checkBoxAssignatura3.isChecked) selectedSubjects.add("PSP (OpenGL for fun)")
            if (checkBoxAssignatura4.isChecked) selectedSubjects.add("Game Design")

            // Get selected torn
            val selectedTornId = radioGroupTorn.checkedRadioButtonId
            val selectedTorn = if (selectedTornId != -1) {
                findViewById<RadioButton>(selectedTornId).text
            } else {
                "No seleccionat"
            }

            // Show the Toast with the selected options
            val message = "${selectedSubjects.joinToString(", ")}\n$selectedTorn"
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
    }
}
