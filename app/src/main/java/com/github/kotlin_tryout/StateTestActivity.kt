package com.github.kotlin_tryout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.kotlin_tryout.ui.theme.KotlinTryoutTheme

class StateTestActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinTryoutTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar =
                        {
                            TopAppBar(title = { Text(text = "Sample App") })
                        },
                ) { innerPadding ->
                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                    ) {
                        Greeting4()
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting4() {
    // Use mutableStateOf to hold and update the VehicleState
    // Provide the current state to the CompositionLocal
    CompositionLocalProvider(LocalVehicleState provides vehicleState) {
        val vehicle = LocalVehicleState.current
        SampleUI(
            onTogglePrimary = {
                // Update the state when the button is clicked
                vehicle.isPrimary.value = false
            }
        )
    }
}

@Composable
fun SampleUI(onTogglePrimary: () -> Unit) {
    val vehicle = LocalVehicleState.current
    Text(text = "Is Primary: ${vehicle.isPrimary.value}")
    Button(onClick = onTogglePrimary) {
        Text("Toggle Primary")
    }
}

data class VehicleState(
    val isPrimary: MutableState<Boolean>
)

val vehicleState = VehicleState(isPrimary = mutableStateOf(true))

val LocalVehicleState = staticCompositionLocalOf<VehicleState> { error("Not yet configured") }
