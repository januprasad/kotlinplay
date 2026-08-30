package com.github.userlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private val allUsers = listOf(
        User(1, "Alice", "alice@example.com"),
        User(2, "Bob", "bob@example.com"),
        User(3, "Charlie", "charlie@example.com"),
        User(4, "David", "david@example.com"),
        User(5, "Eve", "eve@example.com")
    )

    private val _uiState = MutableStateFlow<UserUiState>(UserUiState.Success(allUsers))
    val uiState: StateFlow<UserUiState> = _uiState.asStateFlow()

    private var searchJob: Job? = null

    fun onSearch(query: String) {
        // Cancel the previous job if it's still running
        // This is where cooperative cancellation starts!
        searchJob?.cancel()

        searchJob = viewModelScope.launch {
            _uiState.value = UserUiState.Loading

            // Simulate a delay (e.g., network or heavy processing)
            delay(500)

            // Perform "heavy" filtering cooperatively
            val filteredList = allUsers.filter { user ->
                // Simulate extra heavy work per item
                var i = 0
                while (i < 1000) { 
                    i++
                    // Check for cancellation manually in a tight loop if it was really heavy
                    // ensureActive() or if (!isActive) return@filter false
                }
                
                user.name.contains(query, ignoreCase = true)
            }

            // Only update if the coroutine wasn't cancelled during the filter
            if (isActive) {
                _uiState.value = UserUiState.Success(filteredList)
            }
        }
    }
}

sealed class UserUiState {
    object Loading : UserUiState()
    data class Success(val users: List<User>) : UserUiState()
}
