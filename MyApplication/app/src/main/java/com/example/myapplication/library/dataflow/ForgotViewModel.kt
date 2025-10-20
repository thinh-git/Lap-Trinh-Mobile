package com.example.myapplication.library.dataflow

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ForgotViewModel : ViewModel() {
    var email by mutableStateOf("")
    var code by mutableStateOf("")
    var password by mutableStateOf("")
    var confirmPassword by mutableStateOf("")
    var sentCode by mutableStateOf("123456") // giả lập mã
    var showMessage by mutableStateOf<String?>(null)

    fun sendVerification() {
        if (email.isBlank()) {
            showMessage = "Vui lòng nhập email"
            return
        }
        // giả lập gửi code
        sentCode = "123456"
        showMessage = "Đã gửi mã đến $email (mã mẫu: $sentCode)"
    }

    fun verifyCode(): Boolean {
        if (code == sentCode) {
            showMessage = "Xác thực thành công"
            return true
        }
        showMessage = "Mã không đúng"
        return false
    }

    fun resetPassword(): Boolean {
        if (password.length < 6) {
            showMessage = "Mật khẩu ngắn (>=6)"
            return false
        }
        if (password != confirmPassword) {
            showMessage = "Xác nhận mật khẩu không khớp"
            return false
        }
        showMessage = "Đặt lại mật khẩu thành công"
        return true
    }
}
