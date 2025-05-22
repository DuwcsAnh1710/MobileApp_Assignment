package com.example.bt2_w2

import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val emailEditText = findViewById<EditText>(R.id.editTextText)
        val resultTextView = findViewById<TextView>(R.id.resultTextView)
        val checkButton = findViewById<Button>(R.id.button)

        checkButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()

            when {
                TextUtils.isEmpty(email) -> {
                    resultTextView.text = "Vui lòng nhập email"
                }
                !email.contains("@") || !email.contains(".") -> {
                    resultTextView.text = "Email không đúng định dạng"
                }
                else -> {
                    resultTextView.text = "Email hợp lệ"
                }
            }
        }
    }
}
