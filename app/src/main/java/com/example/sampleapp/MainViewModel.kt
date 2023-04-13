package com.example.sampleapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleapp.repository.WordsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: WordsRepository
): ViewModel() {

    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    fun notifyQueryChanged(query: String) {
        _state.update { it.copy(query = query) }
    }

    fun loadWordData() {
        viewModelScope.launch {
            val newData = repository.getDefinitions(state.value.query)
            _state.update { it.copy(data = newData) }
        }
    }
}
