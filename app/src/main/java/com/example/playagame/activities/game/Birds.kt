package com.example.playagame.activities.game

import android.app.Activity
import android.content.Context
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.playagame.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Birds(activity: Activity, scaleHeight:Float, scaleWidth:Float) {

    private val bird = ImageView(activity)
    private val layout = activity.findViewById<ConstraintLayout>(R.id.gameLayout)
    private val heightScale = scaleHeight
    private val widthScale = scaleWidth
    private var speedY = 0F
    private val accelerationY = 0.14F*heightScale




    fun getX():Int = bird.x.toInt()+bird.width/2
    fun getY():Int = bird.y.toInt()+bird.height/2
    fun addSpeedY(speed: Float) {
        speedY += -speed
    }


    fun createBird() {
        bird.setImageResource(R.drawable.bird)
        bird.scaleX = 0.215F*widthScale
        bird.scaleY = 0.215F*heightScale
        bird.translationX = -290F*widthScale
        layout.addView(bird)


    }

    fun restart(){
        speedY=0F
    }

     fun moveBird() {
            speedY += accelerationY
            if(bird.translationY<-700*heightScale) {
                speedY=0F
                bird.translationY = -700F*heightScale
            }else if(bird.translationY>1100*heightScale){
                speedY = 0F
                bird.translationY=1100F*heightScale
            }
            bird.translationY = bird.translationY + speedY
    }
}