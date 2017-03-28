package com.github.tehras.loadingskeleton.view_streamers

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.github.tehras.loadingskeleton.helpers.LoadingSkeletonViewStreamer

/**
 * This is the Default Text View Streamer
 */
class DefaultTextViewStreamer : LoadingSkeletonViewStreamer<TextView>() {
    override fun <T> streamView(c: Context, v: T) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun convertToType(v: View): TextView? {
        if (v is TextView)
            return v

        return null
    }
}
