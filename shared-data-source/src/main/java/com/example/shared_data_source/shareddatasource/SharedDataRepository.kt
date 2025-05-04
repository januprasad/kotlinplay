import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object SharedDataRepository {
    // Example shared state
    private val _sharedState = MutableStateFlow<String>("")
    val sharedState: StateFlow<String> = _sharedState

    // Function to update shared state
    fun updateSharedState(newValue: String) {
        _sharedState.value = newValue
    }
}