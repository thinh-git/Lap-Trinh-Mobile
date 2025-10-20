package com.example.myapplication.library

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.viewmodel.LibraryViewModel

@Composable
fun BookListScreen(modifier: Modifier = Modifier, vm: LibraryViewModel = viewModel()) {
    var newTitle by remember { mutableStateOf("") }

    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Danh sách sách", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(12.dp))

        if (vm.books.isEmpty()) {
            Text("Chưa có sách nào được thêm.")
        }

        vm.books.forEach { book ->
            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp)) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(Modifier.weight(1f)) {
                        Text(text = book.title)
                        Text(text = if (book.isBorrowed) "Đã mượn" else "Có sẵn")
                    }
                    IconButton(onClick = { vm.deleteBook(book.id) }) {
                        Icon(Icons.Default.Delete, contentDescription = "Xóa sách")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))
        OutlinedTextField(
            value = newTitle,
            onValueChange = { newTitle = it },
            label = { Text("Tên sách mới") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            if (newTitle.isNotBlank()) {
                vm.addBook(newTitle.trim())
                newTitle = ""
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Thêm sách")
        }
    }
}
