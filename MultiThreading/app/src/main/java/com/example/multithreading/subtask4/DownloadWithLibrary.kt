package com.example.multithreading.subtask4

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.multithreading.R

class DownloadWithLibrary : AppCompatActivity() {
    private val IMAGE_LINK =
        "https://oir.mobi/uploads/posts/2021-05/thumbs/1619971852_28-oir_mobi-p-koshka-ris-domashnyaya-poroda-zhivotnie-kr-29.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.image_downloading)
        val imageView = findViewById<ImageView>(R.id.imageView)
        Glide.with(this)
            .load(IMAGE_LINK)
            .into(imageView);
    }
}