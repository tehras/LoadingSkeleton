package com.github.tehras.loadingskeleton.helpers

import android.view.View
import com.github.tehras.loadingskeleton.R
import com.github.tehras.loadingskeleton.view_streamers.DefaultImageViewStreamer
import com.github.tehras.loadingskeleton.view_streamers.DefaultTextViewStreamer

/**
 * This class converts views into the after effect
 */
class LoadingSkeletonViewConverter private constructor() {

    private lateinit var options: Options
    private lateinit var converters: ArrayList<LoadingSkeletonViewStreamer<*>>
    private var isFirstConvert: Boolean = true
    private var isFirstRevert: Boolean = true

    private constructor(builder: Builder) : this() {
        if (builder.converters == null) {
            this.converters = defaultStreamers()
        } else {
            this.converters = builder.converters!!
        }

        this.options = Options(builder.gradient, builder.shimmer, builder.cornerRadius, builder.color)
    }


    fun shimmerEnabled(): Boolean {
        return options.shimmer
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
            converter.convertView(view.context, view, options)
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

    @Suppress("unused")
    class Builder {
        var converters: ArrayList<LoadingSkeletonViewStreamer<*>>? = null
            private set
        var color: Int = R.color.loading_skeleton_default_animation_color
            private set
        var cornerRadius: Float = 5f
            private set
        var gradient: Boolean = true
            private set
        var shimmer: Boolean = true
            private set

        fun color(color: Int): Builder {
            this.color = color
            return this
        }

        fun cornerRadius(radius: Float): Builder {
            this.cornerRadius = radius
            return this
        }

        fun gradient(gradient: Boolean): Builder {
            this.gradient = gradient
            return this
        }

        fun shimmer(shimmer: Boolean): Builder {
            this.shimmer = shimmer
            return this
        }

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
