package com.vs.easyanalytics.utils

import com.chibatching.kotpref.KotprefModel

object AppPrefs : KotprefModel() {

    var lastRecordedDataConsumption by longPref()
}