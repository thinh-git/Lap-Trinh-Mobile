package com.example.tuan2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.content.Intent



class  MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnBai1 = findViewById<Button>(R.id.btnBai1)
        val btnBai2 = findViewById<Button>(R.id.btnBai2)
        val btnBai3 = findViewById<Button>(R.id.btnBai3)

        btnBai1.setOnClickListener {
            startActivity(Intent(this, Bai1NhapSoActivity::class.java))
        }

        btnBai2.setOnClickListener {
            startActivity(Intent(this, Bai2EmailActivity::class.java))
        }

        btnBai3.setOnClickListener {
            startActivity(Intent(this, Bai3TenTuoiActivity::class.java))
        }
    }
}