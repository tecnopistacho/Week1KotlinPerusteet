package com.example.myapplication.domain

import android.accessibilityservice.GestureDescription

data class Task (
    val id: Int,
    val title: String,
    val description: String,
    val priority: Int,
    val dueDate: String,
    val done: Boolean
)
