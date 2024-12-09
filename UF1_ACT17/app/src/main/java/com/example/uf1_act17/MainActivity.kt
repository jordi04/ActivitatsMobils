package com.example.uf1_act17

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var buttons: Array<Array<Button>>
    private lateinit var tvStatus: TextView
    private lateinit var btnReset: Button

    private var playerTurn = true // true -> Jugador X, false -> Jugador O
    private var board = Array(3) { Array(3) { "" } } // Taulell de joc

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvStatus = findViewById(R.id.tvStatus)
        btnReset = findViewById(R.id.btnReset)
        btnReset.setOnClickListener(this)

        buttons = Array(3) { r ->
            Array(3) { c ->
                val buttonId = resources.getIdentifier("btn${r * 3 + c}", "id", packageName)
                val button: Button = findViewById(buttonId)
                button.setOnClickListener(this)
                button
            }
        }

        resetGame()
    }

    override fun onClick(v: View?) {
        v?.let {
            when (it.id) {
                R.id.btnReset -> resetGame()
                else -> {
                    var found = false
                    for (r in 0..2) {
                        for (c in 0..2) {
                            if (buttons[r][c].id == it.id) {
                                onCellClicked(r, c)
                                found = true
                                break
                            }
                        }
                        if (found) break
                    }
                }
            }
        }
    }

    private fun onCellClicked(row: Int, col: Int) {
        if (board[row][col].isNotEmpty()) {
            Toast.makeText(this, "Aquesta casella ja està ocupada!", Toast.LENGTH_SHORT).show()
            return
        }

        val currentSymbol = if (playerTurn) "X" else "O"
        board[row][col] = currentSymbol
        buttons[row][col].text = currentSymbol

        if (checkWin(currentSymbol)) {
            tvStatus.text = "El Jugador $currentSymbol ha guanyat!"
            disableAllButtons()
            return
        }

        if (checkDraw()) {
            tvStatus.text = "Empat!"
            return
        }

        playerTurn = !playerTurn
        tvStatus.text = "El Jugador ${if (playerTurn) "X" else "O"} continua"
    }

    private fun checkWin(symbol: String): Boolean {
        for (r in 0..2) {
            if (board[r][0] == symbol && board[r][1] == symbol && board[r][2] == symbol) {
                highlightWinningCells(arrayOf(r, 0), arrayOf(r, 1), arrayOf(r, 2))
                return true
            }
        }

        for (c in 0..2) {
            if (board[0][c] == symbol && board[1][c] == symbol && board[2][c] == symbol) {
                highlightWinningCells(arrayOf(0, c), arrayOf(1, c), arrayOf(2, c))
                return true
            }
        }
        if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) {
            highlightWinningCells(arrayOf(0, 0), arrayOf(1, 1), arrayOf(2, 2))
            return true
        }

        if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol) {
            highlightWinningCells(arrayOf(0, 2), arrayOf(1, 1), arrayOf(2, 0))
            return true
        }

        return false
    }

    private fun checkDraw(): Boolean {
        for (r in 0..2) {
            for (c in 0..2) {
                if (board[r][c].isEmpty()) {
                    return false
                }
            }
        }
        return true
    }

    private fun disableAllButtons() {
        for (r in 0..2) {
            for (c in 0..2) {
                buttons[r][c].isEnabled = false
            }
        }
    }

    private fun highlightWinningCells(cell1: Array<Int>, cell2: Array<Int>, cell3: Array<Int>) {
        val winningColor = ContextCompat.getColor(this, android.R.color.holo_green_light)
        buttons[cell1[0]][cell1[1]].setBackgroundColor(winningColor)
        buttons[cell2[0]][cell2[1]].setBackgroundColor(winningColor)
        buttons[cell3[0]][cell3[1]].setBackgroundColor(winningColor)
    }

    private fun resetGame() {
        for (r in 0..2) {
            for (c in 0..2) {
                board[r][c] = ""
                buttons[r][c].text = ""
                buttons[r][c].isEnabled = true
                buttons[r][c].setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray))
            }
        }

        playerTurn = true
        tvStatus.text = "El Jugador X comença"
    }
}
