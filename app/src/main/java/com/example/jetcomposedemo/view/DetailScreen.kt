package com.example.jetcomposedemo.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetcomposedemo.HomeViewModel

@Composable
fun DetailScreen(vm: HomeViewModel, navController: NavController, year: String) {
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    text = "SPH coding test - Detail",
                    color = Color.White,
                    modifier = Modifier.padding(start = 12.dp)
                )
            },
            navigationIcon = {
                IconButton(onClick = {
                     navController.navigateUp()
                }) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = "Back to home screen"
                    )
                }
            }
        )
    }) {
    }
}
