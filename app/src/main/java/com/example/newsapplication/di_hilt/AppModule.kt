package com.example.newsapplication.di_hilt

import android.content.Context
import com.example.newsapplication.app.NewsApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    var context: Context = NewsApp.applicationContext()

    @Provides
    fun context(): Context{
        return context
    }

}