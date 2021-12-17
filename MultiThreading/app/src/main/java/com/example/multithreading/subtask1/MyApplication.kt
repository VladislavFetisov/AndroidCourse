package com.example.multithreading.subtask1

import android.app.Application
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService

class MyApplication : Application() {
    private  val executor1: ScheduledExecutorService = Executors.newScheduledThreadPool(1)

    fun getExecutor(): ScheduledExecutorService {
        return executor1
    }
}