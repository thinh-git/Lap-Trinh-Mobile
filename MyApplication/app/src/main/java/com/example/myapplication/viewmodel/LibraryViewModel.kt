package com.example.myapplication.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.myapplication.models.Book
import com.example.myapplication.models.Student
import com.example.myapplication.repository.InMemoryRepo

class LibraryViewModel : ViewModel() {
    var books by mutableStateOf(listOf<Book>())
        private set

    var students by mutableStateOf(listOf<Student>())
        private set

    var selectedStudentIndex by mutableIntStateOf(0)
        private set

    val selectedStudent: Student?
        get() = students.getOrNull(selectedStudentIndex)

    init {
        load()
    }

    private fun load() {
        books = InMemoryRepo.getBooks()
        students = InMemoryRepo.getStudents()
        if (selectedStudentIndex >= students.size) {
            selectedStudentIndex = 0
        }
    }

    fun addBook(title: String) {
        InMemoryRepo.addBook(title)
        load()
    }

    fun addStudent(name: String) {
        InMemoryRepo.addStudent(name)
        load()
    }

    fun deleteBook(bookId: String) {
        InMemoryRepo.deleteBook(bookId)
        load()
    }

    fun deleteStudent(studentId: String) {
        InMemoryRepo.deleteStudent(studentId)
        load()
    }

    fun toggleBorrow(bookId: String) {
        selectedStudent?.let {
            InMemoryRepo.toggleBorrow(it.id, bookId)
            load()
        }
    }

    fun changeStudent() {
        if (students.isNotEmpty()) {
            selectedStudentIndex = (selectedStudentIndex + 1) % students.size
            load()
        }
    }
}
