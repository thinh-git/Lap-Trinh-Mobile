package com.example.tuan2

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class Bai3TenTuoiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bai3_ten_tuoi)

        val edtName = findViewById<EditText>(R.id.edtName)
        val edtAge = findViewById<EditText>(R.id.edtAge)
        val btnCheckAge = findViewById<Button>(R.id.btnCheckAge)

        btnCheckAge.setOnClickListener {
            val name = edtName.text.toString()
            val ageStr = edtAge.text.toString()

            if (name.isEmpty() || ageStr.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val age = ageStr.toInt()
            val result = when {
                age > 65 -> "Người già"
                age >= 6 -> "Người lớn"
                age >= 2 -> "Trẻ em"
                else -> "Em bé"
            }

            Toast.makeText(this, "$name là $result", Toast.LENGTH_SHORT).show()
        }
    }
}