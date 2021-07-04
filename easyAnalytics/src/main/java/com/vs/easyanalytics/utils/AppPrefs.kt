package com.vs.easyanalytics.utils

import com.chibatching.kotpref.KotprefModel
/**
 * Created by Sachin.
 * https://iamsachinrajput.medium.com/
 */
object AppPrefs : KotprefModel() {

    var lastRecordedDataConsumption by longPref()
}