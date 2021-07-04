package com.vs.easyanalytics.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Environment
import androidx.core.content.ContextCompat
import com.vs.easyanalytics.entity.EasyAnalyticsLogger
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

object Utils {

    fun givePhoneInformation(): ByteArray {
        val header = "    ~Phone Information~    "
        val aboutPhone = "Phone- ${Build.MANUFACTURER} ${Build.DEVICE} ${Build.MODEL}"
        return "$header\n$aboutPhone\n\n ${giveAppInformation()}".toByteArray()
    }

    private fun giveAppInformation():String{
        return "AppName- EasyAnalytics"
    }

    val reportSaveAtPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)


    fun getFormattedUtcDate(date: Date): String? {
        val formattedDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(date)
//        val date: Date = sdf.parse(date)
//        val formattedDate = SimpleDateFormat("dd MMM yyyy").format(date)
//        println("MM/dd/yyyy formatted date : " + SimpleDateFormat("dd/MM/yyyy").format(date))
//        println("yyyy-MM-dd formatted date : " + SimpleDateFormat("yyyy-MM-dd").format(date))
        return formattedDate
    }

    fun getReadableDateValue(date: Date): String? {
//        val formattedDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(date)
//        val date: Date = sdf.parse(date)
        val formattedDate = SimpleDateFormat("dd MMM yyyy").format(date)
//        println("MM/dd/yyyy formatted date : " + SimpleDateFormat("dd/MM/yyyy").format(date))
//        println("yyyy-MM-dd formatted date : " + SimpleDateFormat("yyyy-MM-dd").format(date))
        return formattedDate
    }

    fun createHeaderForReport(): ByteArray {
        return ("Screen/Action_taken"+
                "Internet Data used" +"\n").toByteArray()
    }

    fun getAnalyticalRecord(logger:EasyAnalyticsLogger): ByteArray {
        return ("${logger.name} , ${logger.dataConsumed}" ).toByteArray()
    }

    fun permissionGranted(context:Context): Boolean {
//        val state = Environment.getExternalStorageState()
//        return Environment.MEDIA_MOUNTED == state
        return (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)

    }

    fun getCurrentTime():String{
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
        val formattedTime = current.format(formatter)
        println("Current Date is: $formattedTime")
        return formattedTime
    }

}