package com.vs.easyanalytics.reports.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vs.easyanalytics.entity.EasyAnalyticsLogger

@Dao
interface EasyAnalyticsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnalyticLog(easyAnalyticsLogger: EasyAnalyticsLogger)

    @Query("Select * from EasyAnalyticsLogger")
    suspend fun getAnalytics(): List<EasyAnalyticsLogger>
}