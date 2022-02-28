package com.example.sampleapp.views.activities

/**
 * Created by Sachin
 */
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.sampleapp.R
import com.example.sampleapp.utils.SampleAppPrefs
import com.example.sampleapp.views.fragments.NewsFragment
import com.vs.easyanalytics.EasyAnalytics
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_toolbar_view.view.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupLayout()
        setupClickListeners()
    }

    private fun setupClickListeners() {
        btnAskForPermissions.setOnClickListener {
            askPermissions()
        }
        toolbar.checkEasyAnalyticsReport.setOnClickListener {
            EasyAnalytics.showReports(this)
        }
    }

    private fun setupLayout(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            btnAskForPermissions.visibility = View.GONE
            setNewsFragment()
        }else{
            btnAskForPermissions.visibility = View.VISIBLE
        }
    }

    private fun setNewsFragment() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.rlContainer, NewsFragment())
                .commitAllowingStateLoss()
    }

//    override fun onPause() {
//        super.onPause()
//        EasyAnalytics.getAppUsageInfo(this, "HomeScreenPaused_NewsDetailsOpened")
//    }

    private fun askPermissions() {
        val builder = AlertDialog.Builder(this).apply {
            title = "Do you want to access EasyAnalytics reports?"
            setMessage("EasyAnalytics needs storage permissions to write the analytics in a file and save in your phone")
            setIcon(android.R.drawable.ic_dialog_alert)
            setPositiveButton("Yes") { dialog, _ ->
                dialog.dismiss()
                askForWritingPermissions()
            }
            setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
                btnAskForPermissions.visibility = View.GONE
                setNewsFragment()
            }
        }

        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }


    companion object {
        private const val REQUEST_CODE_WRITE_EXTERNAL_STORAGE = 7890
    }

    private fun askForWritingPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            btnAskForPermissions.visibility = View.GONE
            setNewsFragment()
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    showPermissionDeniedDialog(Manifest.permission.WRITE_EXTERNAL_STORAGE, REQUEST_CODE_WRITE_EXTERNAL_STORAGE)
                } else {
                    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_CODE_WRITE_EXTERNAL_STORAGE)
                }
            }
        }
    }


    private fun showPermissionDeniedDialog(permissions: String, permissionRequestCode: Int) {
        ActivityCompat.requestPermissions(this@MainActivity, arrayOf(permissions), permissionRequestCode)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE_WRITE_EXTERNAL_STORAGE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    btnAskForPermissions.visibility = View.GONE
                    setNewsFragment()
                } else {

                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        SampleAppPrefs.permissionDeniedAtleastOnce = true
                        showPermissionDeniedDialog(Manifest.permission.WRITE_EXTERNAL_STORAGE, REQUEST_CODE_WRITE_EXTERNAL_STORAGE)
                    } else {
                        if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA) && (SampleAppPrefs.permissionDeniedAtleastOnce)) {
                            goToSettingsForPermissions()
                        }
                    }
                }
            }
        }
    }

    private fun goToSettingsForPermissions() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        val uri = Uri.fromParts("package", packageName, null)
        intent.data = uri
        startActivity(intent)
    }
}
