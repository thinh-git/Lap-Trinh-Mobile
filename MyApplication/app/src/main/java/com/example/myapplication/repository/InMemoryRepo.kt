package com.example.myapplication.repository

import com.example.myapplication.models.Book
import com.example.myapplication.models.Student
import java.util.UUID

object InMemoryRepo {
    private var books = mutableListOf(
        Book(id = "b1", title = "Sách 01"),
        Book(id = "b2", title = "Sách 02"),
        Book(id = "b3", title = "Sách 03")
    )

    private var students = mutableListOf(
        Student(id = "s1", name = "Nguyen Van A"),
        Student(id = "s2", name = "Nguyen Thi B"),
        Student(id = "s3", name = "Nguyen Van C")
    )

    fun getBooks(): List<Book> = books.toList()
    fun getStudents(): List<Student> = students.toList()

    fun addBook(title: String) {
        books.add(Book(id = UUID.randomUUID().toString(), title = title))
    }

    fun addStudent(name: String) {
        students.add(Student(id = UUID.randomUUID().toString(), name = name))
    }

    fun deleteBook(bookId: String) {
        books.removeAll { it.id == bookId }
    }

    fun deleteStudent(studentId: String) {
        students.removeAll { it.id == studentId }
    }

    fun toggleBorrow(studentId: String, bookId: String) {
        val studentIndex = students.indexOfFirst { it.id == studentId }
        val bookIndex = books.indexOfFirst { it.id == bookId }

        if (studentIndex == -1 || bookIndex == -1) return

        val student = students[studentIndex]
        val book = books[bookIndex]

        val newBorrowedBooks = student.borrowedBooks.toMutableList()
        val isBorrowed = bookId in newBorrowedBooks

        val newBook = book.copy(isBorrowed = !isBorrowed)
        val newStudent = if (isBorrowed) {
            newBorrowedBooks.remove(bookId)
            student.copy(borrowedBooks = newBorrowedBooks)
        } else {
            newBorrowedBooks.add(bookId)
            student.copy(borrowedBooks = newBorrowedBooks)
        }

        // Replace the old objects with the new ones
        books = books.toMutableList().also { it[bookIndex] = newBook }
        students = students.toMutableList().also { it[studentIndex] = newStudent }
    }
}
