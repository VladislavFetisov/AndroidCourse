package com.example.multithreading.subtask1


import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.multithreading.R

class SolveByThread : AppCompatActivity() {
    var secondsElapsed: Int = 0
    lateinit var textSecondsElapsed: TextView
    lateinit var thread: Thread


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


    override fun onPause() {
        super.onPause()
        Log.println(Log.ASSERT, "info", "i was paused")
        thread.interrupt()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.println(Log.ASSERT, "info", "i was destroyed")
    }

    override fun onResume() {
        super.onResume()
        val background = Thread {
            Log.println(Log.ASSERT, "background", Thread.currentThread().id.toString())
            Thread.currentThread().name = "THREAD" + Thread.currentThread().id
            while (!Thread.currentThread().isInterrupted) {
                try {
                    Thread.sleep(1000)
                    textSecondsElapsed.post {
                        textSecondsElapsed.text = "Seconds elapsed: " + secondsElapsed++
                    }
                } catch (e: InterruptedException) {
                    Thread.currentThread().interrupt()
                    Log.println(Log.ASSERT, "info", "i was interrupted")
                }
            }
        }
        thread = background
        background.start()
    }
}
