package com.vs.easyanalytics.reports

import android.content.Context
import android.os.Environment
import android.util.Log
import android.widget.Toast
import com.vs.easyanalytics.R
import com.vs.easyanalytics.entity.EasyAnalyticsLogger
import com.vs.easyanalytics.utils.Utils
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

object EasyAnalyticsReport {

    fun prepareEasyAnalyticsReport(context: Context, logger: EasyAnalyticsLogger) {
        val currentTime = Utils.getFormattedUtcDate(Date())
        val path = Utils.reportSaveAtPath.absolutePath
        if (Utils.permissionGranted(context)) createReportFile(context, path, currentTime, logger)
    }

    private fun getAbsoluteFile(relativePath: String, context: Context): File? {
        return if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()) {
            File(context.getExternalFilesDir(null), relativePath)
        } else {
            File(context.filesDir, relativePath)
        }
    }

    private fun createReportFile(context: Context, path: String, currentTime: String?, logger: EasyAnalyticsLogger) {
        val reportFile = getAbsoluteFile(path, context)
        if (isExternalStorageWritable()) {
            try {
                if (reportFile!!.exists()) {
                    FileOutputStream(reportFile).apply {
                        write(Utils.givePhoneInformation())
                        write(Utils.createHeaderForReport())
                        write(Utils.getAnalyticalRecord(logger))
                    }.close()
                    Log.d("ComingHere","fileCreated ${reportFile.path}")
                } else {
                    Toast.makeText(context, "Error creating log file", Toast.LENGTH_LONG).show()
                }
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
                Toast.makeText(context, "Error writing log file", Toast.LENGTH_LONG).show()
            } catch (e: IOException) {
                e.printStackTrace()
                Toast.makeText(context, "Error writing log file", Toast.LENGTH_LONG).show()
            }
        }
    }


    private fun isExternalStorageWritable(): Boolean {
        val state = Environment.getExternalStorageState()
        return Environment.MEDIA_MOUNTED == state
    }

    private const val TAG = "EasyAnalyticsReport"
}
