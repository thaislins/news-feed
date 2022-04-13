package com.thaislins.newsfeed.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.thaislins.newsfeed.modules.news.model.News
import com.thaislins.newsfeed.modules.news.model.repository.NewsRepository
import com.thaislins.newsfeed.modules.news.viewmodel.NewsResource
import com.thaislins.newsfeed.modules.news.viewmodel.NewsViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnit

class NewsViewModelTest : KoinTest {

    lateinit var viewModel: NewsViewModel
    lateinit var testList: List<News>

    @Mock
    lateinit var newsRepository: NewsRepository

    @Mock
    lateinit var listObserver: Observer<NewsResource>

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule //initMocks
    var initMocks = MockitoJUnit.rule()

    @ObsoleteCoroutinesApi
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @ExperimentalCoroutinesApi
    @ObsoleteCoroutinesApi
    @Before
    fun before() {
        Dispatchers.setMain(mainThreadSurrogate)
        viewModel = NewsViewModel(newsRepository)
        viewModel.newsList.observeForever(listObserver)

        testList = listOf(
            News(0, "News Item 1", 164971, "story", null),
            News(1, "News Item 2", 164971, "stub", null),
            News(2, "News Item 3", 164971, "stub", null),
        )
    }

    @ExperimentalCoroutinesApi
    @ObsoleteCoroutinesApi
    @After
    fun after() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun testApiFetchNull() = runBlocking {
        `when`(newsRepository.getNewsList()).thenReturn(null)
        viewModel.loadNewsList()
        delay(50)
        assertTrue(viewModel.newsList.value?.hasError == false)
        assertNull(viewModel.newsList.value?.data)
        assertTrue(viewModel.newsList.hasObservers())
    }

    @Test
    fun testApiFetchError() = runBlocking {
        `when`(newsRepository.getNewsList()).thenThrow(RuntimeException("Api error"))
        viewModel.loadNewsList()
        delay(50)
        assertTrue(viewModel.newsList.value?.hasError == true)
        assertNull(viewModel.newsList.value?.data)
        assertTrue(viewModel.newsList.hasObservers())
    }

    @Test
    fun testApiFetchSuccess() = runBlocking {
        `when`(newsRepository.getNewsList()).thenReturn(emptyList())
        viewModel.loadNewsList()
        delay(50)
        assertTrue(viewModel.newsList.value?.hasError == false)
        assertTrue(viewModel.newsList.value?.data?.isEmpty() == true)
        assertTrue(viewModel.newsList.hasObservers())
    }

    @Test
    fun testFilterAllNewsList() = runBlocking {
        `when`(newsRepository.getNewsList()).thenReturn(testList)
        viewModel.loadNewsList()
        delay(50)
        viewModel.filterNewsList("all")
        assertEquals(testList, viewModel.newsList.value?.data);
    }

    @Test
    fun testFilterTypeNewsList() = runBlocking {
        `when`(newsRepository.getNewsList()).thenReturn(testList)
        viewModel.loadNewsList()
        delay(50)
        viewModel.filterNewsList("stub")
        val filteredByType = listOf(
            News(1, "News Item 2", 164971, "stub", null),
            News(2, "News Item 3", 164971, "stub", null),
        )
        assertEquals(filteredByType, viewModel.newsList.value?.data);
    }

    @Test
    fun testInvalidFilterTypeNewsList() = runBlocking {
        `when`(newsRepository.getNewsList()).thenReturn(testList)
        viewModel.loadNewsList()
        delay(50)
        viewModel.filterNewsList("invalid")
        assertEquals(emptyList<News>(), viewModel.newsList.value?.data);
    }
}
