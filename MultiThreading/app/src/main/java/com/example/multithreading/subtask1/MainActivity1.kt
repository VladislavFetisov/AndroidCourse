package com.example.multithreading.subtask1


import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.multithreading.R
import java.util.concurrent.ExecutorService

class MainActivity1 : AppCompatActivity() {
    var secondsElapsed: Int = 0
    lateinit var textSecondsElapsed: TextView
    private var canCompute = true


    var backgroundThread = Thread {
        Log.println(Log.ASSERT, "background", Thread.currentThread().id.toString())
        Thread.currentThread().name = "THREAD" + Thread.currentThread().id
//        val currentThread = Thread.currentThread()
//        val threadGroup: ThreadGroup = currentThread.threadGroup!!
//        val allActiveThreads = threadGroup.activeCount()
//       Log.println(Log.ASSERT, "countThread", allActiveThreads.toString())
//        Log.println(Log.ASSERT, "name", Thread.currentThread().name)
        while (true) {
            Thread.sleep(1000)
            if (canCompute) {
                textSecondsElapsed.post {
                    textSecondsElapsed.setText("Seconds elapsed: " + secondsElapsed++)
                }
            }
        }
    }

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
        backgroundThread.start()
        Log.println(Log.ASSERT, "info", "i was created")
//        var id = android.os.Process.myPid()
//        Log.println(Log.ASSERT, "mainThread id:", id.toString())
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
    }

    override fun onStop() {
        super.onStop()
        Log.println(Log.ASSERT, "info", "i was stopped")
        canCompute = false
    }
}
