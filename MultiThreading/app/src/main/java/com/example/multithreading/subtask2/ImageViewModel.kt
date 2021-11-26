package com.example.multithreading.subtask2

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.net.URL
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

open class ImageViewModel : ViewModel() {
    private val IMAGE_LINK =
        "https://img4.goodfon.ru/original/800x480/1/25/kot-glaza-sherst-ushi.jpg"

    private val service: ExecutorService = Executors.newFixedThreadPool(1)

    private val image: MutableLiveData<Bitmap> by lazy {
        MutableLiveData<Bitmap>().also {
            loadImage()
        }
    }

    private fun loadImage() {
        service.execute {
            val newurl = URL(IMAGE_LINK)
            val bitmap = BitmapFactory.decodeStream(newurl.openConnection().getInputStream())
            image.postValue(bitmap)
        }
    }

    fun getImage(): LiveData<Bitmap> {
        return image
    }
}