package com.github.interview_prep

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.github.interview_prep.ui.theme.InterviewprepTheme

class StateSaverActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InterviewprepTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting2(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}

data class City(
    val name: String,
)

val CitySaver =
    listSaver<City, Any>(
        save = {
            listOf(it.name)
        },
        restore = {
            City(it[0] as String)
        },
    )

@Composable
fun Greeting2(
    name: String,
    modifier: Modifier = Modifier,
) {
    val city =
        rememberSaveable(stateSaver = CitySaver) {
            mutableStateOf(City("Kochi"))
        }
    Text(
        text = "Hello ${city.value.name}!",
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    InterviewprepTheme {
        Greeting2("Android")
    }
}
