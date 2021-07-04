package com.vs.easyanalytics.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
/**
 * Created by Sachin.
 * https://iamsachinrajput.medium.com/
 */
object Utils {

    private fun giveAppInformation():String{
        return "AppName- EasyAnalytics"
    }



    fun getCurrentTime():String{
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
        val formattedTime = current.format(formatter)
        println("Current Date is: $formattedTime")
        return formattedTime
    }

}