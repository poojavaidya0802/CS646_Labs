package com.ar50645.dailyquotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dialog = SampleDialogFragment()
        dialog.show(supportFragmentManager, "warningDialog")
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, QuotesFragment.newInstance())
                .commitNow()
        }
    }
}