package com.vs.easyanalytics

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.TrafficStats
import android.util.Log
import com.chibatching.kotpref.Kotpref
import com.vs.easyanalytics.entity.EasyAnalyticsLogger
import com.vs.easyanalytics.reports.ui.EasyAnalyticsReportsActivity
import com.vs.easyanalytics.reports.db.EasyAnalyticsDatabase
import com.vs.easyanalytics.utils.AppPrefs
import com.vs.easyanalytics.utils.Utils
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
/**
 * Created by Sachin.
 * https://iamsachinrajput.medium.com/
 */
object EasyAnalytics {

    private const val TAG = "EasyAnalytics"

    /**
     * call this method in your app class
     */
    fun register(appContext: Application){
        Kotpref.init(appContext)
    }
    /**
     * As of Android 11, this method no longer returns information about all apps but still
     * return information about our app automatically as before.
     * https://developer.android.com/training/package-visibility/automatic
     */
    fun getAppUsageInfo(context: Context, screenAction: String): PackageInfo? {
        var ourAppPackage: PackageInfo? = null
        val packageManager: PackageManager = context.packageManager
        val packageInfoList = packageManager.getInstalledPackages(PackageManager.GET_META_DATA)
        for (packageInfo in packageInfoList) {
            if (packageInfo.packageName == context.packageName) {
                ourAppPackage = packageInfo


                // Get Internet consumption data
                val downloadedData: Long = TrafficStats.getUidRxBytes(getAppPackageUid(context, packageInfo.packageName))
                val uploadedData: Long = TrafficStats.getUidTxBytes(getAppPackageUid(context, packageInfo.packageName))

                val totalDataUsed = ((uploadedData + downloadedData) )
                val dataConsumedInLastAction = (totalDataUsed - AppPrefs.lastRecordedDataConsumption)
                val totalDataUsedValue = android.text.format.Formatter.formatFileSize(context, totalDataUsed)
                val dataConsumedInLastActionValue = android.text.format.Formatter.formatFileSize(context, dataConsumedInLastAction)
                Log.e(TAG, "-------------------------------------------------------------------------------")
                if(AppPrefs.lastRecordedDataConsumption!=0L) {
                    Log.e(TAG, "dataConsumption in $screenAction =$dataConsumedInLastActionValue")
                }
                AppPrefs.lastRecordedDataConsumption = totalDataUsed
                Log.e(TAG, "totalDataConsumed by app by now :$totalDataUsedValue by $screenAction")

                val logger = EasyAnalyticsLogger(screenAction,dataConsumedInLastActionValue, Utils.getCurrentTime())

                runBlocking {
                    launch {
                        EasyAnalyticsDatabase.getDatabase(context).easyAnalyticsDao().insertAnalyticLog(logger)
                    }
                }

            }

        }
        return ourAppPackage
    }

    private fun getAppPackageUid(context: Context, packageName: String): Int {
        var appUid = -1
        val packageManager = context.packageManager
        try {
            val packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_META_DATA)
            appUid = packageInfo.applicationInfo.uid
        } catch (e: PackageManager.NameNotFoundException) {
        }
        return appUid
    }

    fun showReports(context: Context){
        context.startActivity(Intent(context,
            EasyAnalyticsReportsActivity::class.java))
    }
}