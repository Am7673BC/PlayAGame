package com.example.playagame.firebase

import android.app.Activity
import com.example.playagame.R
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await
import kotlin.properties.Delegates

class RemoteConfigModel {

    private val remoteConfig = Firebase.remoteConfig


    suspend fun getConfig(): Boolean {
        var result = false
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 10
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(R.xml.def_values)
        remoteConfig.fetchAndActivate()
            .addOnSuccessListener {
                result = remoteConfig.getBoolean("isWebView")
            }.addOnFailureListener { result = false }.await()
        delay(1000)
        return result
    }
}