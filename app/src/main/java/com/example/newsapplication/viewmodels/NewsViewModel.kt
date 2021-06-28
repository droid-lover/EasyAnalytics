package com.example.newsapplication.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.newsapplication.repository.NewsRepo
import retrofit2.Retrofit

/**
 * Created by Sachin
 */


class NewsViewModel @ViewModelInject constructor(
    private val repo: NewsRepo
) : ViewModel() {


    var showProgressBar = repo.showProgressBar

    var newsHeadlines = repo.newsHeadlines
    var error = repo.error

    override fun onCleared() {
        super.onCleared()
        repo.onCleared()
    }

    fun getNewsHeadlines() {
        newsHeadlines = repo.getNewsHeadlinesFromServer()
    }

}

