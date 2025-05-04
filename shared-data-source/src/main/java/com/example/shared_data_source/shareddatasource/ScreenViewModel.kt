import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

class ScreenViewModel : ViewModel() {
    // Expose the shared state from the repository
    val sharedState: StateFlow<String> = SharedDataRepository.sharedState

    // Update shared state from the screen
    fun updateSharedState(value: String) {
        SharedDataRepository.updateSharedState(value)
    }
}