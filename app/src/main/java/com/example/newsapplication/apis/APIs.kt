package com.example.newsapplication.apis

import com.example.newsapplication.models.NewsHeadlines
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Sachin.
 */

interface APIs {

    //APi Using Rx JAVA
    @GET("v2/top-headlines")
    fun getNewsHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Single<NewsHeadlines>


}