package com.electrocoder.githubfetcher.utils

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.*
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.google.android.material.button.MaterialButton
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.reflect.KClass

private const val TAG = "BindingAdapters"

@BindingAdapter("loadImageUrl")
fun loadImage(image: ImageView, url: String?) {
    if (url != null)
        Glide.with(image)
            .load(url)
            .transform(CircleCrop())
            .into(image)
            .clearOnDetach()
}

@BindingAdapter("showTextOrHide")
fun hideViewIfNull(text: MaterialButton, type: Any?) {
    if (type is String?) {
        if (type.isNullOrEmpty()) {
            text.isGone = true
        } else {
            text.isGone = false
            text.text = type
        }
    } else if (type is Int?) {
        if (type == null) {
            text.isGone = true
        } else {
            text.isGone = false
            text.text = type.toString()
        }
    }
}


@BindingAdapter("showTextOrHide")
fun hideViewIfNull(text: TextView, type: Any?) {
    if (type is String?) {
        if (type.isNullOrEmpty()) {
            text.isGone = true
        } else {
            text.isGone = false
            text.text = type
        }
    } else if (type is Int?) {
        if (type == null) {
            text.isGone = true
        } else {
            text.isGone = false
            text.text = type.toString()
        }
    }
}

// 2021-08-03T23:37:22Z

@SuppressLint("SimpleDateFormat")
@BindingAdapter("showDateText")
fun showShortDate(textView: TextView, date: String) {
    val dateParseFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    try {
        val dateObj = dateParseFormat.parse(date)
        val stringFormat = SimpleDateFormat("MMM d, yyyy", Locale.US)
        textView.text = stringFormat.format(dateObj)
    } catch (e: ParseException) {
        e.printStackTrace()
    }


}

