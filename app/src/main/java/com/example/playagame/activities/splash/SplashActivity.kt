package com.example.playagame.activities.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import androidx.lifecycle.ViewModelProvider
import com.example.playagame.R
import com.example.playagame.activities.game.GameActivity
import com.example.playagame.activities.webview.WebViewActivity
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import kotlinx.coroutines.*

class SplashActivity : AppCompatActivity() {

    private lateinit var viewModel: SplashViewModel
    private val remoteConfig = Firebase.remoteConfig
    private var isSucces = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        viewModel = ViewModelProvider(this).get(SplashViewModel::class.java)



    }

    override fun onStart() {
        super.onStart()

        GlobalScope.launch {
            if (viewModel.loadConfigFromFirebase()) {
                startWebView()
            }else{
                startGame()
            }
        }

    }

    private fun startGame() {
        val gameIntent = Intent(this@SplashActivity, GameActivity::class.java)
        startActivity(gameIntent)
    }

    private suspend fun startWebView() {

        val webViewIntent = Intent(this@SplashActivity, WebViewActivity::class.java)
        webViewIntent.putExtra("link",viewModel.getUrl(this@SplashActivity))
        startActivity(webViewIntent)
    }
}