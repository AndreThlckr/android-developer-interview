package com.example.test

import com.example.sampleapp.repository.WordData
import com.example.sampleapp.repository.WordsRepository

class FakeWordsRepository(
    var fakeData: WordData
) : WordsRepository {

    var lastSearchedWord: String? = null

    override suspend fun getDefinitions(word: String): WordData {
        lastSearchedWord = word
        return fakeData
    }
}
