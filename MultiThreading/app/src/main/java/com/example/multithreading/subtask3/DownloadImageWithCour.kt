package com.example.multithreading.subtask3

import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.multithreading.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.net.URL

class DownloadImageWithCour : AppCompatActivity() {
    private val IMAGE_LINK =
        "https://img4.goodfon.ru/original/800x480/1/25/kot-glaza-sherst-ushi.jpg"
    private lateinit var imageTask: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.image_downloading)
        val imageView = findViewById<ImageView>(R.id.imageView)
        imageTask = lifecycleScope.launch(Dispatchers.IO) {
            val newurl = URL(IMAGE_LINK)
            val bitmap = BitmapFactory.decodeStream(newurl.openConnection().getInputStream())
            lifecycleScope.launch {
                imageView.setImageBitmap(bitmap)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        imageTask.cancel()
    }
}