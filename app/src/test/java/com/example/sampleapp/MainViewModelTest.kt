package com.example.sampleapp

import com.example.sampleapp.repository.WordData
import com.example.test.FakeWordsRepository
import com.example.test.MainCoroutineRule
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldBeEmpty
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private val repository = FakeWordsRepository(
        fakeData = WordData(
            word = "test",
            function = "sample",
            definitions = listOf("first definition", "second definition")
        )
    )

    private fun viewModel() = MainViewModel(
        repository = repository
    )

    @Test
    fun `query should be empty and data should be null at start`() = runTest {
        val viewModel = viewModel()

        val state = viewModel.state.value

        state.query.shouldBeEmpty()
        state.data.shouldBeNull()
    }

    @Test
    fun `notifyQueryChanged should update state query`() = runTest {
        val viewModel = viewModel()

        viewModel.notifyQueryChanged("kotlin")

        val state = viewModel.state.value
        state.query shouldBe "kotlin"
    }

    @Test
    fun `loadWordData should search definitions with current query and update data`() = runTest {
        val viewModel = viewModel()
        viewModel.notifyQueryChanged("java")

        viewModel.loadWordData()

        val state = viewModel.state.value
        repository.lastSearchedWord shouldBe "java"
        state.data.shouldNotBeNull()
    }
}
