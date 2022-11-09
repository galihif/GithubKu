package com.giftech.githubku.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.giftech.githubku.R

object AppUtils {
    fun ImageView.loadImage(imageSource : String?) {
        Glide.with(context)
            .load(imageSource)
            .circleCrop()
            .placeholder(R.drawable.ic_placeholder_avatar)
            .error(R.drawable.ic_error_avatar)
            .into(this)
    }
}