package com.example.event_planner.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.event_planner.EventsViewModel
import com.example.event_planner.composables.BottomNavigationBar
import com.example.event_planner.composables.HomeScreenBody


@Composable
fun getEvents(): EventsViewModel {
    return remember {
        ViewModelProvider.NewInstanceFactory().create(
            EventsViewModel::class.java
        )
    }
}


@Composable
fun HomeScreen() {
    var selectedTab by remember { mutableIntStateOf(1) }
    val vm = getEvents()
    Scaffold(
        topBar = { Box(Modifier.height(0.dp)) },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                when (selectedTab) {
                    0 -> HomeScreenBody(vm)
                    1 -> CreateNewEventScreen(vm)
                    else -> SettingsScreen()

                }
            }
        },
        bottomBar = {
            BottomNavigationBar(
                selectedTab = selectedTab,
                onSelected = {
                    selectedTab = it
                }
            )
        }
    )
}