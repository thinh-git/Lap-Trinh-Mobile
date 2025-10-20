package com.example.myapplication.library

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
fun ManageScreen(modifier: Modifier = Modifier, vm: LibraryViewModel = viewModel()) {
    var showDialog by remember { mutableStateOf(false) }

    // --- Dialog to Add/Remove books for a student ---
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Chọn sách để mượn") },
            text = {
                LazyColumn {
                    items(vm.books) { book ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Checkbox(
                                checked = vm.selectedStudent?.borrowedBooks?.contains(book.id) == true,
                                onCheckedChange = { vm.toggleBorrow(book.id) }
                            )
                            Text(text = book.title, modifier = Modifier.padding(start = 8.dp))
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Xong")
                }
            }
        )
    }

    // --- Main Screen Layout ---
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Hệ thống Quản lý Thư viện", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        // Student selection
        Text(text = "Sinh viên", style = MaterialTheme.typography.titleMedium)
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            vm.selectedStudent?.let {
                OutlinedTextField(
                    value = it.name,
                    onValueChange = {},
                    readOnly = true,
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { vm.changeStudent() }) {
                Text("Thay đổi")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Borrowed book list
        Text(text = "Danh sách sách đã mượn", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        Column(modifier = Modifier.weight(1f)) {
            val borrowedBooks = vm.books.filter { vm.selectedStudent?.borrowedBooks?.contains(it.id) == true }

            if (vm.selectedStudent == null) {
                Text("Vui lòng thêm sinh viên để quản lý.")
            } else if (borrowedBooks.isEmpty()) {
                Text("Bạn chưa mượn quyển sách nào.\nNhấn 'Thêm' để bắt đầu hành trình đọc sách!")
            } else {
                borrowedBooks.forEach { book ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Checkbox(
                            checked = true, // Always checked in this list
                            onCheckedChange = { vm.toggleBorrow(book.id) } // Still allow un-borrowing
                        )
                        Text(text = book.title, modifier = Modifier.padding(start = 8.dp))
                    }
                }
            }
        }

        // Add button to trigger dialog
        Button(
            onClick = { showDialog = true },
            modifier = Modifier.fillMaxWidth(),
            enabled = vm.selectedStudent != null
        ) {
            Text("Thêm")
        }
    }
}
