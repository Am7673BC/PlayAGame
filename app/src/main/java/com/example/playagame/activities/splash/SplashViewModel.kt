package com.example.playagame.activities.splash

import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.playagame.firebase.RealtimeDatabaseModel
import com.example.playagame.firebase.RemoteConfigModel
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

class SplashViewModel : ViewModel() {
    val  remote = RemoteConfigModel()
    val dataBase = RealtimeDatabaseModel()

  suspend  fun loadConfigFromFirebase(): Boolean {
        return  remote.getConfig()
    }

   suspend fun getUrl(context: Context):String{
       return dataBase.getLinkFromRealtimeDataBase(context)
    }

}