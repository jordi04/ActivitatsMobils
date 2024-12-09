package com.example.uf1_act15

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var tvInput: TextView

    private var canAddOperation = false
    private var canAddDecimal = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Assegura't que el layout és correcte
        setContentView(R.layout.activity_main)

        // Inicialitza el TextView
        tvInput = findViewById(R.id.tvInput)

        // Assigna els listeners als botons
        val buttonIds = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9,
            R.id.btnAdd, R.id.btnSubtract, R.id.btnMultiply, R.id.btnDivide,
            R.id.btnDecimal, R.id.btnClear, R.id.btnEquals
        )

        for (id in buttonIds) {
            findViewById<Button>(id).setOnClickListener(this)
        }
    }

    override fun onClick(v: View?) {
        if (v is Button) {
            when (v.id) {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3,
                R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7,
                R.id.btn8, R.id.btn9 -> {
                    appendNumber(v.text.toString())
                }
                R.id.btnAdd, R.id.btnSubtract, R.id.btnMultiply, R.id.btnDivide -> {
                    appendOperation(v.text.toString())
                }
                R.id.btnDecimal -> {
                    appendDecimal()
                }
                R.id.btnClear -> {
                    clearInput()
                }
                R.id.btnEquals -> {
                    calculateResult()
                }
            }
        }
    }

    private fun appendNumber(number: String) {
        if (tvInput.text.toString() == "0") {
            tvInput.text = number
        } else {
            tvInput.append(number)
        }
        canAddOperation = true
    }

    private fun appendOperation(operation: String) {
        if (canAddOperation) {
            tvInput.append(" $operation ")
            canAddOperation = false
            canAddDecimal = true
        }
    }

    private fun appendDecimal() {
        if (canAddDecimal) {
            tvInput.append(".")
            canAddDecimal = false
            canAddOperation = false
        }
    }

    private fun clearInput() {
        tvInput.text = "0"
        canAddOperation = false
        canAddDecimal = true
    }

    private fun calculateResult() {
        val input = tvInput.text.toString()
        val tokens = input.split(" ")

        if (tokens.size < 3) {
            Toast.makeText(this, "Entrada no vàlida", Toast.LENGTH_SHORT).show()
            return
        }

        try {
            var result = tokens[0].toDouble()
            var i = 1
            while (i < tokens.size) {
                val operator = tokens[i]
                val nextNumber = tokens[i + 1].toDouble()

                result = when (operator) {
                    "+" -> result + nextNumber
                    "-" -> result - nextNumber
                    "*" -> result * nextNumber
                    "/" -> {
                        if (nextNumber == 0.0) {
                            Toast.makeText(this, "No es pot dividir per zero", Toast.LENGTH_SHORT).show()
                            return
                        }
                        result / nextNumber
                    }
                    else -> result
                }
                i += 2
            }

            tvInput.text = removeTrailingZero(result.toString())
            canAddOperation = false
            canAddDecimal = true
        } catch (e: Exception) {
            Toast.makeText(this, "Error en el càlcul", Toast.LENGTH_SHORT).show()
        }
    }

    private fun removeTrailingZero(result: String): String {
        return if (result.endsWith(".0")) {
            result.substring(0, result.length - 2)
        } else {
            result
        }
    }
}
