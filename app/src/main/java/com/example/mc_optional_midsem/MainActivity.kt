package com.example.mc_optional_midsem

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.lang.Math.abs
import java.lang.Math.floor
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    private lateinit var editNumber: EditText
    private lateinit var buttonConvert: Button
    private lateinit var textResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editNumber = findViewById(R.id.edit_number)
        buttonConvert = findViewById(R.id.button_convert)
        textResult = findViewById(R.id.text_result)

        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        buttonConvert.setOnClickListener {
            val input = editNumber.text.toString()
            if (input.isEmpty()) {
                showToast("Please enter a number")
            } else {
                try {
                    val number = input.toDouble()
                    val scientificNotation = convertToScientificNotation(number)
                    displayResult(scientificNotation)
                } catch (e: NumberFormatException) {
                    showToast("Invalid input")
                } catch (e: IllegalArgumentException) {
                    showToast("Number is extra big or extra small")
                }
            }
        }
    }

    private fun convertToScientificNotation(number: Double): String {
        return if (number == 0.0) {
            "0.0 × 10^0"
        } else {
            val exponent = floor(Math.log10(Math.abs(number))).toInt()
            val mantissa = number / 10.0.pow(exponent)
            val formattedMantissa = "%.3f".format(mantissa)
            val sign = if (number < 0) "" else ""
            "$sign$formattedMantissa × 10^$exponent"
        }
    }

    private fun displayResult(result: String) {
        textResult.text = result
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
