package com.github.tehras.loadingskeleton.helpers

import android.view.View
import com.github.tehras.loadingskeleton.R
import com.github.tehras.loadingskeleton.view_streamers.DefaultImageViewStreamer
import com.github.tehras.loadingskeleton.view_streamers.DefaultTextViewStreamer

/**
 * This class converts views into the after effect
 */
class LoadingSkeletonViewConverter private constructor() {

    private lateinit var converters: ArrayList<LoadingSkeletonViewStreamer<*>>
    private var isFirstConvert: Boolean = true
    private var isFirstRevert: Boolean = true

    private constructor(builder: Builder) : this() {
        if (builder.converters == null) {
            this.converters = defaultStreamers()
        } else {
            this.converters = builder.converters!!
        }
    }

    private fun defaultStreamers(): ArrayList<LoadingSkeletonViewStreamer<*>> {
        val converters: ArrayList<LoadingSkeletonViewStreamer<*>> = ArrayList()

        converters.add(DefaultImageViewStreamer())
        converters.add(DefaultTextViewStreamer())

        return converters
    }

    fun convertView(view: View) {
        converters.forEach { converter ->
            if (isFirstConvert)
                converter.start()
            converter.convertView(view.context, view, R.color.default_animation_color)
        }
        isFirstRevert = true
        isFirstConvert = false
    }

    fun revertView(view: View) {
        converters.forEach { converter ->
            if (isFirstRevert)
                converter.stop()
            converter.revertView(view.context, view)
        }
        isFirstConvert = true
        isFirstRevert = false
    }


    class Builder {
        var converters: ArrayList<LoadingSkeletonViewStreamer<*>>? = null
            private set

        fun addConvert(converter: LoadingSkeletonViewStreamer<*>): Builder {
            if (this.converters == null)
                this.converters = ArrayList()

            this.converters!!.add(converter)
            return this
        }

        fun build(): LoadingSkeletonViewConverter {
            return LoadingSkeletonViewConverter(this)
        }
    }


}
