package com.github.januprasad.eventbus_example

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.januprasad.eventbus_example.ui.theme.KotlinTryoutTheme
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class MyEvent(
    val message: String,
    val data: Int,
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinTryoutTheme {
                EventBus.getDefault().register(this)
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        Greeting(
                            name = "Android",
                        ) {
                            val event = MyEvent("Hello", 123)
                            EventBus.getDefault().post(event)
                        }
                    }
                }
            }
        }
    }

    @Subscribe
    fun onMessageReceived(event: MyEvent) {
        // Handle the event and access the data
        Toast.makeText(this, "${event.message} - ${event.data}", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Button(onClick = { onClick() }) {
        Text(
            text = "Hello $name!",
            modifier = modifier,
        )
    }
}
