package com.example.playagame.firebase

import android.content.Context
import com.example.playagame.activities.splash.SplashActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.tasks.await

class RealtimeDatabaseModel {

    suspend fun getLinkFromRealtimeDataBase(context: Context):String{
        var result = ""
        val database = FirebaseDatabase.getInstance("https://play-a-game-48016-default-rtdb.europe-west1.firebasedatabase.app")
        val myRef = database.reference
        myRef.child("Url").get().addOnSuccessListener {
            result = it.value.toString()
        }.addOnFailureListener{
           result = " "
        }.await()
        return  result

    }
}