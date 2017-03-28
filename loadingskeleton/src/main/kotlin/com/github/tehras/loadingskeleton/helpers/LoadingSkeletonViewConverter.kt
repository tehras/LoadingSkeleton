package com.github.tehras.loadingskeleton.helpers

import android.view.View
import com.github.tehras.loadingskeleton.view_streamers.DefaultImageViewStreamer
import com.github.tehras.loadingskeleton.view_streamers.DefaultTextViewStreamer

/**
 * This class converts views into the after effect
 */
class LoadingSkeletonViewConverter private constructor() {

    private lateinit var converters: ArrayList<LoadingSkeletonViewStreamer<Any>>

    private constructor(builder: Builder) : this() {
        if (builder.converters == null) {
            this.converters = defaultStreamers()
        } else {
            this.converters = builder.converters!!
        }
    }

    private fun defaultStreamers(): ArrayList<LoadingSkeletonViewStreamer<Any>> {
        val converters: ArrayList<LoadingSkeletonViewStreamer<Any>> = ArrayList()

        converters.add(DefaultImageViewStreamer())
        converters.add(DefaultTextViewStreamer())

        return converters
    }

    fun convertView(view: View) {
        converters.forEach { converter ->
            val t = converter.convertToType(view)
            t?.let {
                converter.streamView(view.context, t)
            }
        }
    }

    class Builder {
        var converters: ArrayList<LoadingSkeletonViewStreamer<Any>>? = null
            private set

        fun addConvert(converter: LoadingSkeletonViewStreamer<Any>): Builder {
            if (this.converters == null)
                this.converters = ArrayList<LoadingSkeletonViewStreamer<Any>>()

            this.converters!!.add(converter)
            return this
        }

        fun build(): LoadingSkeletonViewConverter {
            return LoadingSkeletonViewConverter(this)
        }
    }

}
