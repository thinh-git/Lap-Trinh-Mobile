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
fun StudentListScreen(modifier: Modifier = Modifier, vm: LibraryViewModel = viewModel()) {
    var newName by remember { mutableStateOf("") }

    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Danh sách sinh viên", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(12.dp))

        if (vm.students.isEmpty()) {
            Text("Chưa có sinh viên nào được thêm.")
        }

        vm.students.forEach { st ->
            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(12.dp)
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = st.name)
                        if (st.borrowedBooks.isNotEmpty()) {
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "Mượn: ${st.borrowedBooks.size} sách",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                    IconButton(onClick = { vm.deleteStudent(st.id) }) {
                        Icon(Icons.Default.Delete, contentDescription = "Xóa sinh viên")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))
        OutlinedTextField(
            value = newName,
            onValueChange = { newName = it },
            label = { Text("Tên sinh viên mới") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            if (newName.isNotBlank()) {
                vm.addStudent(newName.trim())
                newName = ""
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Thêm sinh viên")
        }
    }
}
