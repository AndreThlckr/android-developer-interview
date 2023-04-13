package com.example.sampleapp

import com.example.sampleapp.repository.WordData

data class MainState(
    val query: String = "",
    val data: WordData? = null
)
