package com.example.tuan2

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class Bai1NhapSoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bai1_nhap_so)

        val edtNumber = findViewById<EditText>(R.id.edtNumber)
        val btnTao = findViewById<Button>(R.id.btnTao)
        val listContainer = findViewById<LinearLayout>(R.id.listContainer)

        btnTao.setOnClickListener {
            listContainer.removeAllViews()
            val input = edtNumber.text.toString()

            try {
                val n = input.toInt()
                for (i in 1..n) {
                    val tv = TextView(this)
                    tv.text = i.toString()
                    tv.textSize = 18f
                    tv.setPadding(20, 10, 20, 10)
                    tv.setBackgroundResource(android.R.color.holo_red_light)
                    val params = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    params.setMargins(0, 10, 0, 0)
                    tv.layoutParams = params
                    listContainer.addView(tv)
                }
            } catch (e: Exception) {
                Toast.makeText(this, "Dữ liệu bạn nhập không hợp lệ", Toast.LENGTH_SHORT).show()
            }
        }
    }
}