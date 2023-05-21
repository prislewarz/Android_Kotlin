package com.example.hw04_calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var amount = 0
    private val coins = listOf(25, 10, 5, 1)
    private val bills = listOf(2000, 1000, 500, 100)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val amountText = findViewById<TextView>(R.id.amount_text)
        val changeText = findViewById<TextView>(R.id.change_text)

        val numberButtons = listOf(
            findViewById<Button>(R.id.button_0),
            findViewById<Button>(R.id.button_1),
            findViewById<Button>(R.id.button_2),
            findViewById<Button>(R.id.button_3),
            findViewById<Button>(R.id.button_4),
            findViewById<Button>(R.id.button_5),
            findViewById<Button>(R.id.button_6),
            findViewById<Button>(R.id.button_7),
            findViewById<Button>(R.id.button_8),
            findViewById<Button>(R.id.button_9)
        )

        for (button in numberButtons) {
            button.setOnClickListener {
                amount = amount * 10 + button.text.toString().toInt()
                updateAmountText(amountText)
                updateChangeText(changeText)
            }
        }

        val clearButton = findViewById<Button>(R.id.clear_button)
        clearButton.setOnClickListener {
            amount = 0
            updateAmountText(amountText)
            updateChangeText(changeText)
        }
    }

    private fun updateAmountText(textView: TextView) {
        val dollars = amount / 100
        val cents = amount % 100
        textView.text = getString(R.string.amount_text, dollars, cents)
    }

    private fun updateChangeText(textView: TextView) {
        var remainingAmount = amount
        val change = mutableListOf<Int>()

        for (bill in bills) {
            val count = remainingAmount / bill
            remainingAmount %= bill
            change.add(count)
        }

        for (coin in coins) {
            val count = remainingAmount / coin
            remainingAmount %= coin
            change.add(count)
        }

        textView.text = getString(
            R.string.change_text,
            change[0], change[1], change[2], change[3],
            change[4], change[5], change[6], change[7],
        )
    }
}