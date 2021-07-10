package com.example.sampleapp.utils

import com.chibatching.kotpref.KotprefModel

object SampleAppPrefs : KotprefModel() {

    var permissionDeniedAtleastOnce by booleanPref()
}