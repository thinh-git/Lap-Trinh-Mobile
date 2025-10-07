package com.example.tuan2

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class Bai2EmailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bai2_email)

        val edtEmail = findViewById<EditText>(R.id.edtEmail)
        val btnCheck = findViewById<Button>(R.id.btnCheck)

        btnCheck.setOnClickListener {
            val email = edtEmail.text.toString()

            when {
                email.isEmpty() -> Toast.makeText(this, "Email không hợp lệ", Toast.LENGTH_SHORT).show()
                !email.contains("@") -> Toast.makeText(this, "Email không đúng định dạng", Toast.LENGTH_SHORT).show()
                else -> Toast.makeText(this, "Bạn đã nhập email hợp lệ", Toast.LENGTH_SHORT).show()
            }
        }
    }
}