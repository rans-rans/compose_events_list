package com.example.event_planner.screens

import android.annotation.SuppressLint
import android.icu.util.Calendar
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.CalendarLocale
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.event_planner.Event
import com.example.event_planner.EventsViewModel
import com.example.event_planner.composables.BottomSheet
import com.example.event_planner.composables.DropDown
import com.example.event_planner.composables.ShowTimepicker
import java.time.LocalTime

@SuppressLint("NewApi")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateNewEventScreen(eventsViewModel:EventsViewModel) {
    var titleValue by remember { mutableStateOf("") }
    val showTimePicker = remember { mutableStateOf(false) }
    val datePickerState = DatePickerState(locale = CalendarLocale("en"))
    val lazyColumnState = rememberLazyListState()
    var categoryValue by remember { mutableStateOf("No category selected") }
    val categories = listOf("birthday", "church", "home", "party", "work")
    var timePickerState = rememberTimePickerState(
        initialMinute = 0, initialHour = 0, is24Hour = true
    )
    val showBottomSheet = remember { mutableStateOf(false) }
    var descriptionValue by remember { mutableStateOf("") }

    val focus =
        LocalFocusManager.current
    LazyColumn(
        modifier = Modifier.padding(
            top = 50.dp, start = 10.dp, end = 10.dp
        ), state = lazyColumnState, contentPadding = PaddingValues(0.dp)
    ) {
        item {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Title")
                Box(Modifier.width(10.dp))
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = titleValue,
                    onValueChange = {
                        titleValue = it
                    },
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focus.clearFocus()
                        }
                    ),
                    singleLine = true,
                    placeholder = { Text("Enter your title") }
                )
            }
        }
        item {
            Box(Modifier.height(10.dp))
        }
        item {
            DatePicker(
                state = datePickerState,
                title = null,
                headline = null,
                showModeToggle = false,
            )
        }
        item {
            Box(Modifier.height(10.dp))
        }
        item {
            DropDown(label = categoryValue, items = categories, onSelectedItem = {
                categoryValue = categories[it]
            })
        }
        item {
            ListItem(headlineContent = { Text("Event Time") }, trailingContent = {
                val hour = timePickerState.hour.toString().padStart(2, '0')
                val minute = timePickerState.minute.toString().padStart(2, '0')
                Text(
                    text = "$hour:$minute", fontSize = 16.sp, fontWeight = FontWeight.W500
                )
            }, modifier = Modifier.clickable {
                showTimePicker.value = true
                println(showTimePicker.value)
            })
            if (showTimePicker.value) {
                ShowTimepicker(shouldTimeAccept = { state, accept ->
                    showTimePicker.value = false
                    if (accept) {
                        timePickerState = state
                    }
                }, onTimeSelected = {
                    timePickerState = it
                    showTimePicker.value = false
                })
            }
        }
        item {
            Column(modifier = Modifier.clickable {
                showBottomSheet.value = true
            }) {
                ListItem(headlineContent = { Text("Description", fontSize = 16.sp) })
                if (showBottomSheet.value) {
                    BottomSheet(
                        descriptionValue = descriptionValue,
                        getDescription = {
                            descriptionValue = it
                            showBottomSheet.value = false
                        }, onDismiss = {
                            showBottomSheet.value = false
                        }

                    )
                } else {
                    Text(descriptionValue)
                }
            }
        }
        item {
            Box(Modifier.height(100.dp))
        }
        item {
            Button(modifier = Modifier.fillMaxWidth(),
                onClick = {
                    val event = Event(
                        id = Calendar.getInstance().timeInMillis.toString(),
                        title = titleValue,
                        selectedDate = datePickerState.selectedDateMillis ?: 0,
                        category = categoryValue,
                        description = descriptionValue,
                        time = LocalTime.of(timePickerState.hour, timePickerState.minute)
                    )


                    eventsViewModel.addEvent(event)
                },
                content = { Text("Add This Reminder") })
        }

    }
}