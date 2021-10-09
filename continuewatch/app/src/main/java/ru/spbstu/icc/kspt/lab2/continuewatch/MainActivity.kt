package ru.spbstu.icc.kspt.lab2.continuewatch

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.ReentrantLock

class MainActivity : AppCompatActivity() {
    var secondsElapsed: Int = 0
    lateinit var textSecondsElapsed: TextView
    var canCompute = true


    var backgroundThread = Thread {
        while (true) {
            Thread.sleep(1000)
            if (canCompute) {
                textSecondsElapsed.post {
                    textSecondsElapsed.setText("Seconds elapsed: " + secondsElapsed++)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPref = this.getSharedPreferences(
            (R.string.sharedPref.toString()),
            Context.MODE_PRIVATE
        )
        secondsElapsed = sharedPref.getInt(resources.getString(R.string.seconds_elapsed), 0)

        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
        backgroundThread.start()
        Log.println(Log.ASSERT, "onCreateSecondElapsed:", secondsElapsed.toString())
        Log.println(Log.ASSERT, "info", "i was created")
    }


    override fun onPause() {
        super.onPause()
        Log.println(Log.ASSERT, "info", "i was paused")
    }

    override fun onResume() {
        super.onResume()
        Log.println(Log.ASSERT, "info", "i was resumed")
        canCompute = true
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.println(Log.ASSERT, "info", "i was destroyed")
        val sharedPref = this.getSharedPreferences(
            (R.string.sharedPref.toString()),
            Context.MODE_PRIVATE
        )
        with(sharedPref.edit()) {
            Log.println(Log.ASSERT, "onDestroySecondsElapsed:", secondsElapsed.toString())
            putInt(resources.getString(R.string.seconds_elapsed), secondsElapsed)
            apply()
        }
    }

    override fun onStop() {
        super.onStop()
        Log.println(Log.ASSERT, "info", "i was stopped")
        canCompute = false

    }

}
