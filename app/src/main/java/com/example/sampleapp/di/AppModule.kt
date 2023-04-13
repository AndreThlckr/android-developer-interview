package com.example.sampleapp.di

import com.example.sampleapp.api.WordsService
import com.example.sampleapp.api.WordsServiceImpl
import com.example.sampleapp.repository.WordsRepository
import com.example.sampleapp.repository.WordsRepositoryImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {

    singleOf(::WordsServiceImpl) { bind<WordsService>() }

    singleOf(::WordsRepositoryImpl) { bind<WordsRepository>() }
}