package com.example.event_planner

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class EventsViewModel : ViewModel() {
    var events = mutableStateListOf<Event>()

    fun addEvent(event: Event) {
        events.add(event)
    }


    fun removeEvent(id: String) {
        events.removeIf {
            it.id == id
        }
    }
}