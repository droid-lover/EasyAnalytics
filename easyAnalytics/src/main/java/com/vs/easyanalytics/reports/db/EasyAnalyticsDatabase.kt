package com.vs.easyanalytics.reports.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vs.easyanalytics.entity.EasyAnalyticsLogger

/**
 * Created by Sachin.
 */
@Database(entities = [EasyAnalyticsLogger::class], version = 1,exportSchema = false)
abstract class EasyAnalyticsDatabase : RoomDatabase() {
    abstract fun easyAnalyticsDao(): EasyAnalyticsDao

    companion object {
        @Volatile
        private var INSTANCE: EasyAnalyticsDatabase? = null

        fun getDatabase(context: Context): EasyAnalyticsDatabase {
            if (INSTANCE != null) {
                return INSTANCE!!
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(context, EasyAnalyticsDatabase::class.java, "easy_analytics_db").build()
                INSTANCE = instance
                return instance
            }

        }
    }
}