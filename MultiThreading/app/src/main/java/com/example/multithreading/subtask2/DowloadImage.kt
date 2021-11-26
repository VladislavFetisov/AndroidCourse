package com.example.multithreading.subtask2


import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import com.example.multithreading.R


class DownloadImage : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.image_downloading)
        val view = ViewModelProvider(this)[ImageViewModel::class.java]
        view.getImage().observe(this, Observer { image ->
            val imageView = findViewById<ImageView>(R.id.imageView)
            imageView.setImageBitmap(image)
        })
    }
}
