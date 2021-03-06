package com.vs.easyanalytics.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
/**
 * Created by Sachin.
 * https://iamsachinrajput.medium.com/
 */
@Entity
data class EasyAnalyticsLogger( @ColumnInfo val name:String,  @ColumnInfo val dataConsumed:String , @ColumnInfo val time:String)
{
    @PrimaryKey(autoGenerate = true)
    var logId: Int = 0
}

