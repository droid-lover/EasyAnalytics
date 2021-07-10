package com.example.sampleapp.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.sampleapp.repository.NewsRepo

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

