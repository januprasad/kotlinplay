package com.github.kotlin_tryout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.github.kotlin_tryout.ui.theme.KotlinTryoutTheme

class Navigation3Example : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinTryoutTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavGraph(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun NavGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "screen1",
        modifier = modifier
    ) {
        composable("screen1") {
            Screen1(navController)
        }
        composable(
            route = "screen2/{userId}",
            arguments = listOf(navArgument("userId") { type = NavType.StringType })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")
            Screen2(navController, userId)
        }
    }

}

@Composable
fun Screen1(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "This is Screen 1")
        Button(onClick = { navController.navigate("screen2/user-123") }) {
            Text(text = "Go to Screen 2")
        }
    }
}

@Composable
fun Screen2(navController: NavController, userId: String?) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "This is Screen 2 for user: $userId")
        Button(onClick = { navController.navigateUp() }) {
            Text(text = "Go Back")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KotlinTryoutTheme {
        NavGraph()
    }
}
