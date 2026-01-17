package com.example.tiktokdownloader.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.core.graphics.createBitmap

/**
 * Converts a ByteArray to a Bitmap.
 * If the ByteArray is null, returns a 1x1 pixel Bitmap.
 *
 * @param byteArray The ByteArray to convert.
 * @return The resulting Bitmap.
 * @author Abdulrahman Nisar
 */
fun byteArrayToBitmap(byteArray: ByteArray?): Bitmap {
    return if (byteArray != null) {
        BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    } else {
        createBitmap(1, 1)
    }
}
