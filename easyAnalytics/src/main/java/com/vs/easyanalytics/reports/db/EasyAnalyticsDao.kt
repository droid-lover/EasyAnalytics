package com.vs.easyanalytics.reports.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vs.easyanalytics.entity.EasyAnalyticsLogger
/**
 * Created by Sachin.
 * https://iamsachinrajput.medium.com/
 */
@Dao
interface EasyAnalyticsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAnalyticLog(easyAnalyticsLogger: EasyAnalyticsLogger)

    @Query("Select * from EasyAnalyticsLogger")
    fun getAnalytics(): List<EasyAnalyticsLogger>
}