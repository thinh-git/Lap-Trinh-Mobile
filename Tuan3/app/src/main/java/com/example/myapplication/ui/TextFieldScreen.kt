package com.example.myapplication.ui
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TextFieldScreen() {
    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Thông tin nhập") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = if (text.isEmpty()) "Tự động cập nhật dữ liệu theo textfield" else text,
            color = Color.Red
        )
    }
}
