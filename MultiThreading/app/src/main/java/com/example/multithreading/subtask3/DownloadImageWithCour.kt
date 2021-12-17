package com.example.multithreading.subtask3

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.multithreading.R

class DownloadImageWithCour : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.image_downloading)
        val view = ViewModelProvider(this)[ImageViewModelCoroutines::class.java]
        view.getImage().observe(this, { image ->
            val imageView = findViewById<ImageView>(R.id.imageView)
            imageView.setImageBitmap(image)
        })
    }
}