package com.vs.easyanalytics.reports.ui

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.vs.easyanalytics.R
import com.vs.easyanalytics.entity.EasyAnalyticsLogger
import com.vs.easyanalytics.reports.db.EasyAnalyticsDatabase
import kotlinx.android.synthetic.main.activity_easy_analytical_reports.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Created by Sachin.
 * https://iamsachinrajput.medium.com/
 */
class EasyAnalyticsReportsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_easy_analytical_reports)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        runBlocking {
            launch {
                val eaAnalyticsValues = EasyAnalyticsDatabase.getDatabase(this@EasyAnalyticsReportsActivity).easyAnalyticsDao().getAnalytics()
                Log.e(TAG, "inside_get ${eaAnalyticsValues.size}")
                if(!eaAnalyticsValues.isNullOrEmpty()){
                    showEasyAnalyticsReports(eaAnalyticsValues)
                }
            }
        }

    }

    private fun showEasyAnalyticsReports(eaAnalyticsValues: List<EasyAnalyticsLogger>) {
        val values= ArrayList<String>()
        eaAnalyticsValues.forEach {
            val formattedValue = "\nAt ${it.time}\n\n ${it.name} = ${it.dataConsumed}\n"
            values.add(formattedValue)
        }
        val arrayAdapter: ArrayAdapter<*> = ArrayAdapter(this@EasyAnalyticsReportsActivity, android.R.layout.simple_list_item_1,values)
        lvEasyAnalyticsReports.adapter = arrayAdapter
    }


    companion object{
        const val TAG = "EasyAnalyticsReportsActivity"
    }

    override fun onOptionsItemSelected(@NonNull item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}