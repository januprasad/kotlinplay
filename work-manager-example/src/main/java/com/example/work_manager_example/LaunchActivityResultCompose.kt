package com.example.work_manager_example

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.work_manager_example.ui.theme.KotlinTryoutTheme

class LaunchActivityResultCompose : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinTryoutTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(Modifier.padding(innerPadding)) {
                        Greeting(
                            name = "SCAN",
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    fun abcd(abc: ()-> Unit) {
        println("Greeting")
    }
    var result by remember { mutableStateOf("") }
    if (result.isEmpty()) {
        when (name) {
            "SCAN" -> {
                Demo_DropDownMenu {
                    result = it
                }
            }
        }
    } else {
        Text(result)
        Button(onClick = {
            result = ""
        }) {
            Text("Reset")
        }
    }
}

@Composable
fun Demo_DropDownMenu(result: (String) -> Unit) {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
    ) {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = Icons.Default.MoreVert, contentDescription = "More"
            )
        }

        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            DropdownMenuItem(text = { Text("Load") }, onClick = {
                result("Loaded")
                expanded = false
            })
            DropdownMenuItem(text = { Text("Save") }, onClick = {
                result("Saved")
                expanded = false
            })
        }
    }
}