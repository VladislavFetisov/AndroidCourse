package com.example.multithreading.subtask1


import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.multithreading.R
import java.util.concurrent.*

class SolveByExecutors : AppCompatActivity() {
    var secondsElapsed: Int = 0
    lateinit var textSecondsElapsed: TextView
    private val executor1: ScheduledExecutorService = Executors.newScheduledThreadPool(1)
    private lateinit var increment: ScheduledFuture<*>

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putInt("SECONDS_ELAPSED", secondsElapsed)
        }
        super.onSaveInstanceState(outState)
        Log.println(Log.ASSERT, "info", "Saving instance state")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.run {
            secondsElapsed = getInt("SECONDS_ELAPSED")
        }
        Log.println(Log.ASSERT, "info", "Restoring instance state")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
        Log.println(Log.ASSERT, "info", "i was created")
    }


    override fun onResume() {
        super.onResume()
        Log.println(Log.ASSERT, "info", "i was resumed")
        val task = Runnable {
            textSecondsElapsed.post {
                textSecondsElapsed.setText("Seconds elapsed: " + secondsElapsed++)
            }
        }
        increment = executor1.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS)
    }

    override fun onPause() {
        super.onPause()
        increment.cancel(true)
        Log.println(Log.ASSERT, "info", "i was paused")
    }


    override fun onDestroy() {
        super.onDestroy()
        executor1.shutdownNow()
    }
}
