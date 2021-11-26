package com.example.multithreading.subtask1


import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.multithreading.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.*

class SolveByCoroutines : AppCompatActivity() {
    var secondsElapsed: Int = 0
    lateinit var textSecondsElapsed: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycleScope.launchWhenResumed {
            Thread.currentThread().name="thread ${Thread.currentThread().id}"
            while (!Thread.currentThread().isInterrupted) {
                delay(1000)
                textSecondsElapsed.post {
                    textSecondsElapsed.setText("Seconds elapsed: " + secondsElapsed++)
                }
            }
        }
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
        Log.println(Log.ASSERT, "info", "i was created")
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

}
