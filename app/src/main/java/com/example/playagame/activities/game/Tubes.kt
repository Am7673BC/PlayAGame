package com.example.playagame.activities.game

import android.app.Activity
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.playagame.R
import kotlin.random.Random

class Tubes(activity: Activity,scaleHeight:Float, scaleWidth:Float) {


    private val layout = activity.findViewById<ConstraintLayout>(R.id.gameLayout)
    private val tube = ImageView(activity)
    private val heightScale = scaleHeight
    private val widthScale = scaleWidth
    private val speedX = 1.75F*widthScale


    fun getX():Int = tube.x.toInt()+tube.width/2
    fun getY():Int = tube.y.toInt()+tube.height/2


    fun createColumn() {
        tube.setImageResource(R.drawable.img)
        tube.scaleX = 2.2F*widthScale
        tube.scaleY = 5.5F*heightScale
        deleteColumn()
        layout.addView(tube)
    }

     fun moveColumn() {
        tube.translationX = tube.translationX-speedX
         if(tube.translationX<-300*widthScale){
            deleteColumn()
         }
    }

    fun restart(){
        deleteColumn()
    }

   private fun deleteColumn() {
       tube.translationX=1000F*widthScale
       tube.translationY= Random.nextInt(150*heightScale.toInt(), 1200*heightScale.toInt()).toFloat()

    }

}