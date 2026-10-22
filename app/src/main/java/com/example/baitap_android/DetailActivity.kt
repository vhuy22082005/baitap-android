package com.example.baitap_android

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainDetail)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tvNameValue = findViewById<TextView>(R.id.tvNameValue)
        val tvEmailValue = findViewById<TextView>(R.id.tvEmailValue)
        val tvMsvValue = findViewById<TextView>(R.id.tvMsvValue)
        val tvPhoneValue = findViewById<TextView>(R.id.tvPhoneValue)
        val tvClassValue = findViewById<TextView>(R.id.tvClassValue)
        val btnBack = findViewById<MaterialButton>(R.id.btnBack)

        // Nhận dữ liệu từ Intent
        val name = intent.getStringExtra("EXTRA_NAME")
        val email = intent.getStringExtra("EXTRA_EMAIL")
        val msv = intent.getStringExtra("EXTRA_MSV")
        val phone = intent.getStringExtra("EXTRA_PHONE")
        val className = intent.getStringExtra("EXTRA_CLASS")

        if (!name.isNullOrEmpty()) {
            tvNameValue.text = name
            tvEmailValue.text = email
            tvMsvValue.text = msv
            tvPhoneValue.text = phone
            tvClassValue.text = className
        } else {
            tvNameValue.text = "Chưa có thông tin"
            tvEmailValue.text = "Chưa có thông tin"
            tvMsvValue.text = "Chưa có thông tin"
            tvPhoneValue.text = "Chưa có thông tin"
            tvClassValue.text = "Chưa có thông tin"
        }

        btnBack.setOnClickListener {
            finish()
        }
    }
}
