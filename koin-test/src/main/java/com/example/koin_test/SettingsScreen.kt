package com.example.koin_test
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(
    nickname: String,
    onNicknameChange: (String) -> Unit,
    turboMode: Boolean,
    onTurboModeChange: (Boolean) -> Unit,
    throttle: Float,
    onThrottleChange: (Float) -> Unit,
    onSave: () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(32.dp),
        modifier =
            Modifier
                .fillMaxSize()
                .padding(32.dp),
    ) {
        TextField(
            label = { Text("Nickname") },
            value = nickname,
            onValueChange = onNicknameChange,
            modifier = Modifier.fillMaxWidth(),
        )

        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text(
                "Enable Turbo Mode",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.align(Alignment.CenterVertically),
            )
            Switch(
                checked = turboMode,
                onCheckedChange = onTurboModeChange,
            )
        }

        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text(
                "Throttle",
                style = MaterialTheme.typography.bodyLarge,
                modifier =
                    Modifier
                        .align(Alignment.CenterVertically)
                        .padding(end = 32.dp),
            )
            Slider(
                value = throttle,
                onValueChange = onThrottleChange,
                valueRange = 1.0f..5.0f,
            )
        }

        Button(onClick = onSave, modifier = Modifier.fillMaxWidth()) {
            Text("Save")
        }
    }
}
