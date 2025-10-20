package com.example.myapplication.models

data class Book(
    val id: String,
    val title: String,
    val isBorrowed: Boolean = false
)

data class Student(
    val id: String,
    val name: String,
    val borrowedBooks: List<String> = emptyList() // Changed to immutable List
)
