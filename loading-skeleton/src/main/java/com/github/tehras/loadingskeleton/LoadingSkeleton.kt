package com.github.tehras.loadingskeleton

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.facebook.shimmer.ShimmerFrameLayout
import com.github.tehras.loadingskeleton.helpers.LoadingSkeletonViewConverter

/**
 * Loading Skeleton object is here to convert a normal R.layout into a
 * great looking Facebook-like loading skeleton
 */
@Suppress("unused")
class LoadingSkeleton private constructor(context: Context, attrs: AttributeSet?, defStyleAttrs: Int) : FrameLayout(context, attrs, defStyleAttrs) {

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    /**
     * This is assigned to the temporary Container layout
     */
    private val containerViewId: Int = 10001
    var skeletonViewConverter: LoadingSkeletonViewConverter

    init {
        skeletonViewConverter = LoadingSkeletonViewConverter.Builder().build()
    }

    fun skeletonViewConverter(skeletonViewConverter: LoadingSkeletonViewConverter): LoadingSkeleton {
        this.skeletonViewConverter = skeletonViewConverter

        return this
    }

    fun stop() {
        if (childCount != 1)
            throw RuntimeException("View must have 1 child")

        val container = getChildAt(0) as ViewGroup

        if (container.id != containerViewId)
            return

        val child = container.getChildAt(0)

        if (container is ViewGroup) {
            revertView(container, this.skeletonViewConverter)
        } else {
            throw Exception("Layout must be a ViewGroup")
        }

        container.removeView(child)

        this.removeView(container)
        this.addView(child)
    }

    fun start() {
        if (childCount != 1)
            throw RuntimeException("View must have 1 child")

        val layout = getChildAt(0)

        if (layout.id == containerViewId)
            return

        this.removeView(layout)

        if (layout is ViewGroup) {
            populateView(layout, skeletonViewConverter)
        } else {
            throw Exception("Layout must be a ViewGroup")
        }

        val container: ViewGroup

        if (this.skeletonViewConverter.shimmerEnabled()) {
            container = ShimmerFrameLayout(context)
        } else {
            container = FrameLayout(context)
        }
        container.id = containerViewId
        container.addView(layout)

        if (container is ShimmerFrameLayout) {
            container.startShimmerAnimation()
        }

        this.addView(container)
    }

    private fun populateView(v: ViewGroup?, viewConverter: LoadingSkeletonViewConverter) {
        v?.let {
            (0..v.childCount)
                    .map { v.getChildAt(it) }
                    .forEach {
                        if (it is ViewGroup) {
                            populateView(it, viewConverter)
                        } else if (it is View) {
                            viewConverter.convertView(it)
                        }
                    }
        }
    }

    private fun revertView(v: ViewGroup?, viewConverter: LoadingSkeletonViewConverter) {
        v?.let {
            (0..v.childCount)
                    .map { v.getChildAt(it) }
                    .forEach {
                        if (it is ViewGroup) {
                            revertView(it, viewConverter)
                        } else if (it is View) {
                            viewConverter.revertView(it)
                        }
                    }
        }
    }

}
