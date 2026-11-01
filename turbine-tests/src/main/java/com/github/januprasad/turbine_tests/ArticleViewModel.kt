package com.github.januprasad.turbine_tests

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ArticleViewModel(
    private val dataRepository: DataRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default,
) : ViewModel() {
    private val _state = MutableStateFlow<ArticleState>(ArticleState.Waiting)
    val state = _state.asStateFlow()

    fun loadData() {
        viewModelScope.launch(dispatcher) {
            _state.update { ArticleState.Running }
            val result = dataRepository.fetchData()
            _state.update { ArticleState.Loaded(result) }
        }
    }
}
