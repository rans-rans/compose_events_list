package com.example.event_planner.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.event_planner.Event
import com.example.event_planner.EventsViewModel
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.Date

@SuppressLint("NewApi")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenBody(
    events:EventsViewModel
) {
    val searchFieldValue by remember { mutableStateOf("") }
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                "Plan your Events", style = MaterialTheme.typography.titleMedium
            )
        })
    }, content = { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            item {
                InputField(
                    value = searchFieldValue,
                    placeholder = { Text("Search for your Event") },
                    onValueChange = {},
                )
            }
            for (event in events.events) {
                item {
                    Box(Modifier.height(2.dp))
                    Card(
                        modifier = Modifier.padding(15.dp),
                        onClick = {},
                        content = {
                            Column {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(4.dp)
                                ) {
                                    Text(
                                        event.title,
                                        style = MaterialTheme.typography.displaySmall,
                                        fontWeight = FontWeight.Bold,
                                    )
                                    Box(Modifier.height(5.dp))
                                    Text(
                                        event.category,
                                        fontWeight = FontWeight.Bold,
                                    )
                                }
                                Box(Modifier.height(5.dp))
                                Text(
                                    event.description,
                                )
                                Box(Modifier.height(10.dp))
                                Text(
                                    "Time: ${event.time.format(DateTimeFormatter.ISO_LOCAL_TIME)}",
                                )
                                Box(Modifier.height(15.dp))
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        Date.from(Instant.ofEpochMilli(event.selectedDate))
                                            .toString()
                                    )
                                    IconButton(
                                        onClick = {
                                            events.removeEvent(event.id)
                                        },
                                        content = { Icon(
                                            Icons.Filled.Delete,
                                            null,
                                            tint = Color.Red
                                        ) }
                                    )
                                }
                            }
                        },
                    )

                }
            }

        }
    })

}