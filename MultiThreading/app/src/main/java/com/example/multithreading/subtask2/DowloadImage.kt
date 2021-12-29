package com.example.multithreading.subtask2


import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.multithreading.R
import com.example.multithreading.subtask1.MyApplication
import java.net.URL
import java.util.concurrent.Future


class DownloadImage : AppCompatActivity() {
    private val IMAGE_LINK =
        "https://img4.goodfon.ru/original/800x480/1/25/kot-glaza-sherst-ushi.jpg"


    private lateinit var imageTask: Future<*>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.image_downloading)
        imageTask = (application as MyApplication).getExecutor().submit {
            val newurl = URL(IMAGE_LINK)
            val image = BitmapFactory.decodeStream(newurl.openConnection().getInputStream())
            val imageView = findViewById<ImageView>(R.id.imageView)
            imageView.post {
                imageView.setImageBitmap(image)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        imageTask.cancel(true)
    }
}
