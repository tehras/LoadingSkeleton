package com.github.tehras.loadingskeleton.view_streamers

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.github.tehras.loadingskeleton.helpers.LoadingSkeletonViewStreamer

/**
 * This is the Default Image View Streamer
 */
class DefaultImageViewStreamer : LoadingSkeletonViewStreamer<ImageView>(ImageView::class.java) {

    private var drawables = ArrayList<Drawable>()
    private var index = 0

    override fun start() {
        super.start()

        drawables = ArrayList()
    }

    override fun stop() {
        super.stop()

        index = 0
    }

    override fun revert(c: Context, v: ImageView) {
        if (v.drawable != null) {
            v.drawable.clearColorFilter()
        }

        v.setImageDrawable(drawables[index])
        index++
    }

    override fun convert(c: Context, v: ImageView, color: Int) {
        drawables.add(v.drawable)
        v.setImageDrawable(null)

        //re-assign drawable
        @Suppress("DEPRECATION")
        v.setImageDrawable(c.resources.getDrawable(color))

        v.drawable.setColorFilter(color, PorterDuff.Mode.DST_OVER)
        v.drawable.setColorFilter(color, PorterDuff.Mode.DST_ATOP)
    }

}
