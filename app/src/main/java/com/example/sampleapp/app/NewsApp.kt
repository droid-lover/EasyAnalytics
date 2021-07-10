package com.example.sampleapp.app

import android.app.Application
import com.facebook.cache.disk.DiskCacheConfig
import com.facebook.common.util.ByteConstants
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.cache.MemoryCacheParams
import com.facebook.imagepipeline.core.ImagePipelineConfig
import com.vs.easyanalytics.EasyAnalytics
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by Sachin
 */

/**
 *@HiltAndroidApp: we need to apply this annotation to our Application class,
 *  It will trigger the Hilt code generation and in the process will create our App Component.
 */

@HiltAndroidApp
class NewsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        initializeFresco()

        //initialize EasyAnalytics
        EasyAnalytics.register(this)
    }

    companion object {
        @get:Synchronized
        var instance: NewsApp? = null
        private val MAX_HEAP_SIZE = Runtime.getRuntime().maxMemory().toInt()
        fun applicationContext(): NewsApp {
            return instance as NewsApp
        }
    }



    private val MAX_DISK_CACHE_SIZE = 40 * ByteConstants.MB
    private val MAX_MEMORY_CACHE_SIZE = MAX_HEAP_SIZE / 4
    private val IMAGE_PIPELINE_CACHE_DIR = "imagepipeline_cache"
    private fun initializeFresco() {

        val bitmapCacheParams = MemoryCacheParams(
            MAX_MEMORY_CACHE_SIZE, // Max total size of elements in the cache
            Integer.MAX_VALUE, // Max entries in the cache
            MAX_MEMORY_CACHE_SIZE, // Max total size of elements in eviction queue
            Integer.MAX_VALUE, // Max length of eviction queue
            Integer.MAX_VALUE
        )

        val config = ImagePipelineConfig.newBuilder(this)
            .setBitmapMemoryCacheParamsSupplier { bitmapCacheParams }
            .setMainDiskCacheConfig(
                DiskCacheConfig.newBuilder(this)
                    .setBaseDirectoryPath(applicationContext.cacheDir)
                    .setBaseDirectoryName(IMAGE_PIPELINE_CACHE_DIR)
                    .setMaxCacheSize(MAX_DISK_CACHE_SIZE.toLong())
                    .build()
            )
            .build()
        Fresco.initialize(this, config)

    }

}
