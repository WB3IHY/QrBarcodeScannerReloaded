package com.example.barcodescanner.usecase

import android.graphics.Bitmap
import com.example.barcodescanner.extension.orZero
import com.google.zxing.BinaryBitmap
import com.google.zxing.InvertedLuminanceSource
import com.google.zxing.MultiFormatReader
import com.google.zxing.NotFoundException
import com.google.zxing.RGBLuminanceSource
import com.google.zxing.Result
import com.google.zxing.common.GlobalHistogramBinarizer
import com.google.zxing.common.HybridBinarizer
import io.reactivex.Single
import io.reactivex.SingleEmitter
import io.reactivex.schedulers.Schedulers

object BarcodeImageScanner {
    private var bitmapBuffer: IntArray? = null

    fun parse(image: Bitmap): Single<Result> {
        return Single
            .create<Result> { emitter ->
                parse(image, emitter)
            }
            .subscribeOn(Schedulers.newThread())
    }

    private fun parse(image: Bitmap, emitter: SingleEmitter<Result>) {
        try {
            emitter.onSuccess(tryParse(image))
        } catch (ex: Exception) {
            Logger.log(ex)
            emitter.onError(ex)
        }
    }

    private fun tryParse(image: Bitmap): Result {
        val width = image.width
        val height = image.height
        val size = width * height

        if (size > bitmapBuffer?.size.orZero()) {
            bitmapBuffer = IntArray(size)
        }

        image.getPixels(bitmapBuffer, 0, width, 0, 0, width, height)

        val source = RGBLuminanceSource(width, height, bitmapBuffer)
        val reader = MultiFormatReader()

        // Pass 1: HybridBinarizer (best for high-contrast codes)
        try {
            return reader.decode(BinaryBitmap(HybridBinarizer(source)))
        } catch (_: NotFoundException) { }

        // Pass 2: GlobalHistogramBinarizer (better for lower-contrast images)
        reader.reset()
        try {
            return reader.decode(BinaryBitmap(GlobalHistogramBinarizer(source)))
        } catch (_: NotFoundException) { }

        // Pass 3: inverted image (handles dark-background codes)
        reader.reset()
        val invertedSource = InvertedLuminanceSource(source)
        try {
            return reader.decode(BinaryBitmap(HybridBinarizer(invertedSource)))
        } catch (_: NotFoundException) { }

        reader.reset()
        throw NotFoundException.getNotFoundInstance()
    }
}