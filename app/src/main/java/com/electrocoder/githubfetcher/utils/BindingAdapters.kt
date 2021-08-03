package com.electrocoder.githubfetcher.utils

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isGone
import androidx.databinding.*
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.google.android.material.button.MaterialButton
import kotlin.reflect.KClass

private const val TAG = "BindingAdapters"

@BindingAdapter("loadImageUrl")
fun loadImage(image: ImageView,url: String?) {
    if(url != null)
        Glide.with(image)
            .load(url)
            .transform(CircleCrop())
            .into(image)
            .clearOnDetach()
}

@BindingAdapter("showTextOrHide")
fun hideViewIfNull(text: MaterialButton, type: Any?) {
    if(type is String?) {
        if(type.isNullOrEmpty()) {
            text.isGone = true
        } else {
            text.isGone = false
            text.text = type
        }
    } else if(type is Int?) {
        if(type == null) {
            text.isGone = true
        } else {
            text.isGone = false
            text.text = type.toString()
        }
    }
}

