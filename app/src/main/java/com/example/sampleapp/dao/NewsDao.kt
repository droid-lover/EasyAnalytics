package com.example.sampleapp.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.sampleapp.models.NewsHeadlines
/**
 * Created by Sachin.
 */
@Dao
interface NewsDao {

    @Insert
    fun insertNews(news: NewsHeadlines)

    @Query("Select * from NewsHeadlines")
    fun getNewsHeadlinesFromDB(): NewsHeadlines
}