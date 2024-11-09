package com.example.event_planner.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun BottomSheet(
    getDescription: (String) -> Unit,
    descriptionValue: String,
    onDismiss: () -> Unit
) {
    val descriptionData = remember { mutableStateOf(descriptionValue) }
    val sheetState = rememberModalBottomSheetState(true)

    ModalBottomSheet(
        onDismissRequest = {
            onDismiss()
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .height(350.dp),
        sheetState = sheetState,
        content = {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .padding(10.dp)
                    .fillMaxHeight()
                    .imePadding()
            ) {
                Text(
                    "Description",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Medium
                )

                Box(Modifier.height(5.dp))
                Text(descriptionData.value)
                Spacer(
                    modifier = Modifier.weight(1f)
                )
                TextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = descriptionData.value,
                    onValueChange = {
                        descriptionData.value = it
                    },
                    singleLine = true,
                    placeholder = { Text("Enter your description") },
                    keyboardActions = KeyboardActions(
                        onDone = {
                            getDescription(descriptionData.value)
                        }
                    ),
                    keyboardOptions = KeyboardOptions(
                        autoCorrect = false
                    )
                )

            }
        }
    )

}