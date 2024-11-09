package com.example.event_planner

import java.time.LocalTime

data class Event(
    val id: String,
    val title: String,
    val description: String,
    val selectedDate: Long,
    val category: String,
    val time: LocalTime
)
