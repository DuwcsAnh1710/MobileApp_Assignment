package com.example.btvn_w2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var edtName: EditText
    private lateinit var edtAge: EditText
    private lateinit var btnCheck: Button
    private lateinit var txtResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edtName = findViewById(R.id.edtName)
        edtAge = findViewById(R.id.edtAge)
        btnCheck = findViewById(R.id.btnCheck)
        txtResult = findViewById(R.id.txtResult)
        btnCheck.setOnClickListener {
            val name = edtName.text.toString().trim()
            val ageStr = edtAge.text.toString().trim()
            if (name.isEmpty() || ageStr.isEmpty()) {
                Toast.makeText(this, "Vui lòng ghi đầy đủ thông tin", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val age = ageStr.toIntOrNull()
            if (age == null) {
                Toast.makeText(this, "Nhập lại tuổi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val result = when {
                age <= 2 -> "$name là Em bé"
                age <= 6 -> "$name là Trẻ em"
                age <= 65 -> "$name là Người lớn"
                else -> "$name là Người già"
            }
            txtResult.text = result
        }
    }
}
