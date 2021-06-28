package com.example.newsapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
/**
 * Created by Sachin.
 */
open class Repo {

    protected val disposables = CompositeDisposable()

    protected val _showProgressBar = MutableLiveData<Boolean>()

    val showProgressBar: LiveData<Boolean>
        get() = _showProgressBar

    fun onCleared() {
        disposables.dispose()
    }
}