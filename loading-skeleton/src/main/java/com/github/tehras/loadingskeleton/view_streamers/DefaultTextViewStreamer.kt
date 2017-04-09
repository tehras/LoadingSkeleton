package com.github.tehras.loadingskeleton.view_streamers

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.TextView
import com.github.tehras.loadingskeleton.helpers.LoadingSkeletonViewStreamer
import com.github.tehras.loadingskeleton.helpers.Options
import com.github.tehras.loadingskeleton.utils.assignBackground

/**
 * This is the Default Text View Streamer
 */
class DefaultTextViewStreamer : LoadingSkeletonViewStreamer<TextView>(TextView::class.java) {

    private var texts: ArrayList<CharSequence> = ArrayList()
    private var colors: ArrayList<Drawable> = ArrayList()
    private var index: Int = 0

    override fun start() {
        super.start()

        texts = ArrayList()
        colors = ArrayList()
    }

    override fun stop() {
        super.stop()

        index = 0
    }

    override fun revert(c: Context, v: TextView) {
        val text = texts.getOrNull(index)
        val background = colors.getOrNull(index)
        text?.let {
            v.text = text
        }
        v.background = background
        index++
    }

    override fun convert(c: Context, v: TextView, options: Options) {
        texts.add(v.text)
        colors.add(v.background)

        v.text = ""

        assignBackground(options, v)
    }
}
