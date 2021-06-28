package com.example.newsapplication.views.activities
/**
 * Created by Sachin
 */
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapplication.R
import com.example.newsapplication.views.fragments.NewsFragment
import com.vs.easyanalytics.EasyAnalytics
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setNewsFragment()
    }

    private fun setNewsFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.rlContainer, NewsFragment())
            .commitAllowingStateLoss()
    }

    override fun onPause() {
        super.onPause()
        EasyAnalytics.getAppUsageInfo(this,"HomeScreenPaused_NewsDetailsOpened")
    }

}
