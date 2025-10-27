package com.example.myapplication.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun LibraryHomeScreen(
    onOpenBooks: () -> Unit,
    onOpenStudents: () -> Unit,
    onOpenManage: () -> Unit
) {
    Text("Library Home Screen")
}
