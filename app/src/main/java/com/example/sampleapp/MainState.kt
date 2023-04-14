package com.example.sampleapp

import androidx.annotation.StringRes
import com.example.sampleapp.repository.WordData

data class MainState(
    val query: String = "",
    val data: WordData? = null,
    @StringRes val error: Int? = null
) {

    fun withData(data: WordData) = copy(
        data = data,
        error = null
    )

    fun withError(error: Int) = copy(
        data = null,
        error = error
    )
}
