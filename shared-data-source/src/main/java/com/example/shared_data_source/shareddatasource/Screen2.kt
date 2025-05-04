import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController

@Composable
fun Screen2(navController: NavController, viewModel: ScreenViewModel) {
    val sharedState by viewModel.sharedState.collectAsState()

    // Display the shared state
    MyComposableContent(
        sharedState = sharedState,
        onUpdateSharedState = { newValue ->
            viewModel.updateSharedState(newValue)
        }
    )
}