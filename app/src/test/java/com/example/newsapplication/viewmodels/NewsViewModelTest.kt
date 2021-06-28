package com.example.newsapplication.viewmodels

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.newsapplication.app.NewsApp
import com.example.newsapplication.models.Articles
import com.example.newsapplication.repository.NewsRepo
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Sachin
 */
@RunWith(MockitoJUnitRunner.Silent::class)
class NewsViewModelTest {

    lateinit var newsViewModel: NewsViewModel

    @Mock
    lateinit var newsRepo: NewsRepo

    private lateinit var newsList: List<Articles>
    private val mockNewsList: MutableList<Articles> = mutableListOf()
    private val mockNewsLiveData: MutableLiveData<List<Articles>> =
        MutableLiveData<List<Articles>>()

    @get:Rule
    val rule = InstantTaskExecutorRule()


    @Before
    fun init() {
        MockitoAnnotations.initMocks(this);
        this.newsViewModel = NewsViewModel(this.newsRepo)
        mockData()
        // retroViewModel.postInfoLiveData
    }

    @Test
    fun testNewsDataIsNotNull() {
        Mockito.`when`(newsRepo.getNewsHeadlinesFromServer()).thenReturn(mockNewsLiveData)
        newsViewModel.newsHeadlines = mockNewsLiveData
        Assert.assertNotNull(newsViewModel.newsHeadlines.value)
    }

    @Test
    fun testNewsDataFromRepo() {
        Mockito.`when`(newsRepo.getNewsHeadlinesFromServer()).thenReturn(mockNewsLiveData)
        newsViewModel.newsHeadlines = mockNewsLiveData
        Assert.assertNotNull(newsViewModel.newsHeadlines.value)
        Assert.assertTrue(newsViewModel.newsHeadlines.value?.size == 3)

    }

    private fun mockData() {
        newsList = emptyList()

        //actual data
        mockNewsList.add(
            Articles(
                author = "Vincent Barone",
                title = "Kanye West announces he's running for president - New York Post ",
                description = "We must now realize the promise of America by trusting God, unifying our vision and building our future. I am running for president of the United States,” the Chicago rapper tweeted with “#2020VIS…",
                url = "https://nypost.com/2020/07/04/kanye-west-tweets-hes-running-for-president/",
                urlToImage = "https://nypost.com/wp-content/uploads/sites/2/2020/07/kanye-west.jpg?quality=90&strip=all&w=1200",
                publishedAt = "2020-07-05T02:15:31Z",
                content = "This would certainly be a “Late Registration.”\r\nKanye West announced on Twitter Saturday that he is running for president .\r\nWe must now realize the promise of America by trusting God, unifying our v… [+2092 chars]"
            )
        )
        //dummy data
        mockNewsList.add(Articles("a", "a", "a", "a", "a", "a", "a"))
        mockNewsList.add(Articles("a", "a", "a", "a", "a", "a", "a"))

        newsList = mockNewsList.toList()
        mockNewsLiveData.postValue(newsList)

    }
}