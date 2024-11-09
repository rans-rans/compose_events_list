package com.example.event_planner.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun DropDown(
    label: String,
    items: List<String>,
    onSelectedItem: (Int) -> Unit
) {
    val dropdownExpanded = remember { mutableStateOf(false) }
    Column {
        Text("Category")
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 2.dp, color = Color.Black)
                .height(65.dp)
                .padding(vertical = 5.dp, horizontal = 12.dp)
                .clickable {
                    dropdownExpanded.value = true
                }) {
            Text(
                label.uppercase(),
                fontWeight = FontWeight.Medium,
            )
            Box(Modifier.width(10.dp))
            DropdownMenu(
                expanded = dropdownExpanded.value,
                onDismissRequest = {
                    dropdownExpanded.value = false
                },
                modifier = Modifier
                    .fillMaxWidth(fraction = 0.9f)
                    .background(Color.White)
                    .padding(10.dp)
            ) {
                items.forEachIndexed { index, username ->
                    DropdownMenuItem(
                        text = {
                            Text(text = username)
                        },
                        onClick = {
                            onSelectedItem(index)
                            dropdownExpanded.value = false
                        },

                        )
                }
            }
        }
    }

}
