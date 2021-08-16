package ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.image_cache

import android.graphics.Bitmap
import io.reactivex.rxjava3.core.Single

interface ImageToCacheSaver {
    fun save(userId: Long, bitmap: Bitmap): Single<String>
}
