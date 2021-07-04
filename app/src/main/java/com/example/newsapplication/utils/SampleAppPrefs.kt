package com.example.newsapplication.utils

import com.chibatching.kotpref.KotprefModel

object SampleAppPrefs : KotprefModel() {

    var permissionDeniedAtleastOnce by booleanPref()
}