package com.github.tehras.loadingskeleton.view_streamers

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.github.tehras.loadingskeleton.helpers.LoadingSkeletonViewStreamer

/**
 * This is the Default Image View Streamer
 */
class DefaultImageViewStreamer : LoadingSkeletonViewStreamer<ImageView>() {
    override fun <T> streamView(c: Context, v: T) {

    }

    override fun convertToType(v: View): ImageView? {
        if (v is ImageView)
            return v

        return null
    }

}
