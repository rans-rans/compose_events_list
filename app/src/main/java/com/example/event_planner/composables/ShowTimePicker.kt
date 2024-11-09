package com.example.event_planner.composables

import androidx.compose.foundation.clickable
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowTimepicker(
    onTimeSelected: (TimePickerState) -> Unit,
    shouldTimeAccept: (TimePickerState, Boolean) -> Unit
) {
    val timePickerState = rememberTimePickerState()
    AlertDialog(
        onDismissRequest = {
            shouldTimeAccept(timePickerState, false)
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onTimeSelected(timePickerState)
                    shouldTimeAccept(timePickerState, true)
                },
                content = { Text("DONE") }
            )
        },
        text = {
            TimePicker(
                state = timePickerState,
                modifier = Modifier.clickable {
                }
            )
        }
    )

}