package com.ahmed.ntg.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun ErrorScreen(
    navHostController: NavHostController,
    action: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        IconButton(modifier = Modifier.align(Alignment.Center),onClick = {
            action()
        }) {
            Icon(Icons.Filled.Refresh, contentDescription = "Refresh" , tint = Color.Blue, modifier = Modifier.size(100.dp))

        }

    }


}