package com.vs.easyanalytics

import com.chibatching.kotpref.KotprefModel

object AppPrefs : KotprefModel() {


    var lastRecordedDataConsumption by longPref()
}