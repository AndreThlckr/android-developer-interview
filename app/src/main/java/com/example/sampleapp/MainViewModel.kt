package com.example.sampleapp

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleapp.repository.HttpRequestException
import com.example.sampleapp.repository.WordNotFoundException
import com.example.sampleapp.repository.WordsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: WordsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    fun notifyQueryChanged(query: String) {
        _state.update { it.copy(query = query) }
    }

    fun loadWordData() {
        viewModelScope.launch {
            val dataResult = runCatching {
                repository.getDefinitions(state.value.query)
            }

            _state.update { state ->
                dataResult.fold(
                    onSuccess = { newData -> state.withData(newData) },
                    onFailure = { throwable -> state.withError(messageFrom(throwable)) }
                )
            }
        }
    }

    @StringRes
    private fun messageFrom(exception: Throwable): Int = when (exception) {
        is WordNotFoundException -> R.string.word_not_found_message
        is HttpRequestException -> R.string.network_error_message
        else -> R.string.generic_error_message
    }
}
