package com.example.multithreading.subtask3

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL

class ImageViewModelCoroutines : ViewModel() {
    private val IMAGE_LINK =
        "https://img4.goodfon.ru/original/800x480/1/25/kot-glaza-sherst-ushi.jpg"

    private val image = MutableLiveData<Bitmap>()

    private fun downloadImage() {
        viewModelScope.launch(Dispatchers.IO) {
            val newurl = URL(IMAGE_LINK)
            val bitmap = BitmapFactory.decodeStream(newurl.openConnection().getInputStream())
            viewModelScope.launch(Dispatchers.Main) {
                image.value = bitmap
            }
        }
    }

    fun getImage(): LiveData<Bitmap> {
        downloadImage()
        return image
    }

}

