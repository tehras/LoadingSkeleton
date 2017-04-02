package com.github.tehras.loadingskeleton.view_streamers

import android.content.Context
import android.graphics.PorterDuff
import android.widget.ImageView
import com.github.tehras.loadingskeleton.helpers.LoadingSkeletonViewStreamer

/**
 * This is the Default Image View Streamer
 */
class DefaultImageViewStreamer : LoadingSkeletonViewStreamer<ImageView>(ImageView::class.java) {
    override fun revert(c: Context, v: ImageView) {
        if (v.drawable != null) {
            v.drawable.clearColorFilter()
        }
    }

    override fun convert(c: Context, v: ImageView, color: Int) {
        if (v.drawable == null) {
            v.setImageDrawable(c.resources.getDrawable(color))
        }
        v.drawable.setColorFilter(color, PorterDuff.Mode.DST_OVER)
        v.drawable.setColorFilter(color, PorterDuff.Mode.DST_ATOP)
    }

}
