package com.example.baitap_android

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private lateinit var tilName: TextInputLayout
    private lateinit var etName: TextInputEditText
    private lateinit var tilEmail: TextInputLayout
    private lateinit var etEmail: TextInputEditText
    private lateinit var tilMsv: TextInputLayout
    private lateinit var etMsv: TextInputEditText
    private lateinit var tilPhone: TextInputLayout
    private lateinit var etPhone: TextInputEditText
    private lateinit var tilClass: TextInputLayout
    private lateinit var etClass: TextInputEditText
    private lateinit var btnRegister: MaterialButton
    private lateinit var btnGoToDetail: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initViews()
        setupListeners()
    }

    private fun initViews() {
        tilName = findViewById(R.id.tilName)
        etName = findViewById(R.id.etName)
        tilEmail = findViewById(R.id.tilEmail)
        etEmail = findViewById(R.id.etEmail)
        tilMsv = findViewById(R.id.tilMsv)
        etMsv = findViewById(R.id.etMsv)
        tilPhone = findViewById(R.id.tilPhone)
        etPhone = findViewById(R.id.etPhone)
        tilClass = findViewById(R.id.tilClass)
        etClass = findViewById(R.id.etClass)
        btnRegister = findViewById(R.id.btnRegister)
        btnGoToDetail = findViewById(R.id.btnGoToDetail)
    }

    private fun setupListeners() {
        btnRegister.setOnClickListener {
            if (validateForm()) {
                val name = etName.text.toString().trim()
                val email = etEmail.text.toString().trim()
                val msv = etMsv.text.toString().trim()
                val phone = etPhone.text.toString().trim()
                val className = etClass.text.toString().trim()

                Snackbar.make(it, "Đăng ký thành công!", Snackbar.LENGTH_LONG).show()
                navigateToDetail(name, email, msv, phone, className)
            }
        }

        btnGoToDetail.setOnClickListener {
            navigateToDetail("", "", "", "", "")
        }
    }

    private fun validateForm(): Boolean {
        var isValid = true

        val name = etName.text.toString().trim()
        if (name.isEmpty()) {
            tilName.error = "Họ và tên không được để trống"
            isValid = false
        } else {
            tilName.error = null
        }

        val email = etEmail.text.toString().trim()
        if (email.isEmpty()) {
            tilEmail.error = "Email không được để trống"
            isValid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tilEmail.error = "Email không hợp lệ"
            isValid = false
        } else {
            tilEmail.error = null
        }

        val msv = etMsv.text.toString().trim()
        if (msv.isEmpty()) {
            tilMsv.error = "Mã sinh viên không được để trống"
            isValid = false
        } else {
            tilMsv.error = null
        }

        val phone = etPhone.text.toString().trim()
        if (phone.isEmpty()) {
            tilPhone.error = "Số điện thoại không được để trống"
            isValid = false
        } else if (!Regex("^[0-9]{10,11}$").matches(phone)) {
            tilPhone.error = "Số điện thoại phải từ 10-11 chữ số"
            isValid = false
        } else {
            tilPhone.error = null
        }

        val className = etClass.text.toString().trim()
        if (className.isEmpty()) {
            tilClass.error = "Lớp không được để trống"
            isValid = false
        } else {
            tilClass.error = null
        }

        return isValid
    }

    private fun navigateToDetail(name: String, email: String, msv: String, phone: String, className: String) {
        try {
            val intent = Intent().setClassName(this, "com.example.baitap_android.DetailActivity").apply {
                putExtra("EXTRA_NAME", name)
                putExtra("EXTRA_EMAIL", email)
                putExtra("EXTRA_MSV", msv)
                putExtra("EXTRA_PHONE", phone)
                putExtra("EXTRA_CLASS", className)
            }
            startActivity(intent)
        } catch (_: ActivityNotFoundException) {
            Toast.makeText(this, "Màn hình Chi tiết (DetailActivity) chưa được tạo", Toast.LENGTH_SHORT).show()
        }
    }
}
