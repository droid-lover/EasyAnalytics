package com.example.newsapplication.views.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapplication.R

/**
 * Created by Sachin
 */
class SplashScreenActivity : AppCompatActivity() {

    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        startNextActivity(MainActivity::class.java)
    }


    private fun startNextActivity(activity: Class<out AppCompatActivity>?) {
        val intent = Intent(this@SplashScreenActivity, activity).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        handler.postDelayed({ startActivity(intent) }, 4000)
    }

}
