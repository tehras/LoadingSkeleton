package com.github.tehras.loadingskeleton

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.facebook.shimmer.ShimmerFrameLayout
import com.github.tehras.loadingskeleton.animators.DefaultLoadingSkeletonAnimator
import com.github.tehras.loadingskeleton.helpers.LoadingSkeletonAnimator
import com.github.tehras.loadingskeleton.helpers.LoadingSkeletonViewConverter

/**
 * Loading Skeleton object is here to convert a normal R.layout into a
 * great looking Facebook-like loading skeleton
 */
@Suppress("unused")
class LoadingSkeleton private constructor(context: Context, attrs: AttributeSet?, defStyleAttrs: Int) : FrameLayout(context, attrs, defStyleAttrs) {

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    private val containerViewId: Int = 10001

    var skeletonAnimator: LoadingSkeletonAnimator = DefaultLoadingSkeletonAnimator.generate()
    var skeletonViewConverter: LoadingSkeletonViewConverter? = null

    fun stop() {
        if (childCount != 1)
            throw RuntimeException("View must have 1 child")

        val container = getChildAt(0) as ViewGroup
        if (container.id == containerViewId) {

            val child = container.getChildAt(0)

            container.removeView(child)

            this.removeView(container)
            this.addView(child)
        }
    }

    fun start() {
        if (childCount != 1)
            throw RuntimeException("View must have 1 child")

        val layout = getChildAt(0)

        this.removeView(layout)

        if (layout is ViewGroup) {
            populateView(layout)
        } else {
            throw Exception("Layout must be a ViewGroup")
        }

        val container: ViewGroup

        if (skeletonAnimator.shimmer) {
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

    private fun populateView(v: ViewGroup?) {
        v?.let {
            (0..v.childCount)
                    .map { v.getChildAt(it) }
                    .forEach {
                        if (it is ViewGroup) {
                            populateView(it)
                        } else if (it is View) {
                            skeletonViewConverter?.convertView(it)
                        }
                    }
        }
    }
}
