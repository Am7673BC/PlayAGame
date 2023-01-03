package com.example.playagame.user_params

import android.app.Activity
import android.graphics.Point
import android.view.Display

class ScreenSize(activity: Activity) {

  private  val display: Display = activity.windowManager.defaultDisplay

    private var screenWidth: Int = 0
    private var screenHeight: Int = 0

    fun init(){
        val point = Point()
        display.getSize(point)
       screenWidth = point.x
        screenHeight= point.y

    }
    //this functions return scale of the height and width of screen of user
    fun getScaleHeight():Float=screenHeight/2195F
    fun getScaleWidth():Float=screenWidth/1080F


    }
