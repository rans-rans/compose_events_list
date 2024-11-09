package com.example.event_planner.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun BottomNavigationBar(
    onSelected: (Int) -> Unit,
    selectedTab: Int
) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, null) },
            onClick = { onSelected(0) },
            selected = selectedTab == 0,
            label = { Text("Home") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Create, null) },
            onClick = { onSelected(1) },
            selected = selectedTab == 1,
            label = { Text("Create") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Settings, null) },
            onClick = { onSelected(2) },
            selected = selectedTab == 2,
            label = { Text("Settings") }
        )
    }
}