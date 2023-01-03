package com.example.playagame.activities.game

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.playagame.R
import com.example.playagame.user_params.ScreenSize
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("ClickableViewAccessibility")
class GameActivity : AppCompatActivity() {
    private lateinit var bird: Birds
    private lateinit var tube: Tubes
    private lateinit var layout: ConstraintLayout
    private lateinit var screen: ScreenSize
    private lateinit var restartGameBtn: ImageButton
    private lateinit var downloader: Job
    private val BUTTONBACKGROUND = 43000000  //Int color for button background
    private var heightScale = 1F
    private var widthScale = 1F


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        init()
    }

    override fun onStart() {
        super.onStart()
        startGame()
    }

    override fun onPause() {
        super.onPause()
        downloader.cancel()
    }

    private fun init() {
        screen = ScreenSize(this)
        screen.init()
        heightScale = screen.getScaleHeight()
        widthScale = screen.getScaleWidth()
        bird = Birds(this, heightScale, widthScale)
        tube = Tubes(this, heightScale, widthScale)
        restartGameBtn = ImageButton(this)
        tube.createColumn()
        bird.createBird()

        layout = findViewById(R.id.gameLayout)
        layout.setOnTouchListener { view, motionEvent ->
            bird.addSpeedY(heightScale * 1.25F)
            return@setOnTouchListener true
        }
        restartGameBtn.setImageResource(R.drawable.ic_baseline_restart_alt_24)
        restartGameBtn.setBackgroundColor(BUTTONBACKGROUND)
        layout.addView(restartGameBtn)
    }

    private fun isBirdCrossedColumn(bird: Birds, tube: Tubes): Boolean {
        return (((bird.getY() > tube.getY() + 280 * heightScale) or (bird.getY() < tube.getY() - 50 * heightScale)) and ((bird.getX() > tube.getX() - 150 * widthScale) and (bird.getX() < tube.getX() + 120 * widthScale))) // case when gamer lose, here sizes of tube are different for different screens
    }

    private fun startGame() {
        downloader = GlobalScope.launch {
            delay(800)
            while (!isBirdCrossedColumn(bird, tube)) {
                bird.moveBird()
                tube.moveColumn()
                delay(7)
            }

        }
        stopGame()
    }

    private fun stopGame() {
        restartGameBtn.setOnClickListener {
            downloader.cancel()
            bird.restart()
            tube.restart()
            startGame()
        }
    }
}