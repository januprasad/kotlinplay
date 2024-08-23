package com.github.januprasad.hilt_example_2.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.github.januprasad.hilt_example_2.app.AppEvent
import com.github.januprasad.hilt_example_2.app.ui.components.appbar.AppBar

@Composable
fun HomeScreen(
    drawerState: DrawerState, onEvent: (appEvent: AppEvent) -> Unit
) {
    Scaffold(topBar = { AppBar(drawerState = drawerState) }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Home")
            Button(onClick = { onEvent(AppEvent.SendMail) }) {
                Text("Send mail")
            }
        }
    }
}