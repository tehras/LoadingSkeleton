package com.github.tehras.loadingskeleton

import android.content.Context
import android.util.AttributeSet
import android.util.Log
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

    /**
     * This is assigned to the temporary Container layout
     */
    private val containerViewId: Int = 10001
    private var builder: Builder? = null

    private fun attach(builder: Builder): LoadingSkeleton {
        this.builder = builder
        return this
    }

    private fun performWarningCheck() {
        if (builder == null)
            Log.e(this.javaClass.simpleName, "Please use LoadingSkeleton.Builder().attach() in order to use the loading effects")

    }

    fun stop() {
        performWarningCheck()

        if (childCount != 1)
            throw RuntimeException("View must have 1 child")

        val container = getChildAt(0) as ViewGroup

        if (container.id != containerViewId)
            return

        val child = container.getChildAt(0)

        if (container is ViewGroup) {
            revertView(container, builder)
        } else {
            throw Exception("Layout must be a ViewGroup")
        }

        container.removeView(child)

        this.removeView(container)
        this.addView(child)
    }

    fun start() {
        performWarningCheck()

        builder?.let {
            if (childCount != 1)
                throw RuntimeException("View must have 1 child")

            val layout = getChildAt(0)

            if (layout.id == containerViewId)
                return

            this.removeView(layout)

            if (layout is ViewGroup) {
                populateView(layout, it)
            } else {
                throw Exception("Layout must be a ViewGroup")
            }

            val container: ViewGroup

            if (it.skeletonAnimator?.shimmer ?: true) {
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
    }

    private fun populateView(v: ViewGroup?, builder: Builder) {
        v?.let {
            (0..v.childCount)
                    .map { v.getChildAt(it) }
                    .forEach {
                        if (it is ViewGroup) {
                            populateView(it, builder)
                        } else if (it is View) {
                            builder.skeletonViewConverter?.convertView(it)
                        }
                    }
        }
    }

    private fun revertView(v: ViewGroup?, builder: Builder?) {
        builder?.let { builder ->
            v?.let {
                (0..v.childCount)
                        .map { v.getChildAt(it) }
                        .forEach {
                            if (it is ViewGroup) {
                                revertView(it, builder)
                            } else if (it is View) {
                                builder.skeletonViewConverter?.revertView(it)
                            }
                        }
            }
        }
    }

    class Builder {
        internal var skeletonAnimator: LoadingSkeletonAnimator? = null
            private set
        internal var skeletonViewConverter: LoadingSkeletonViewConverter? = null
            private set

        fun skeletonAnimator(skeletonAnimator: LoadingSkeletonAnimator): Builder {
            this.skeletonAnimator = skeletonAnimator
            return this
        }

        fun skeletonViewConverter(skeletonViewConverter: LoadingSkeletonViewConverter): Builder {
            this.skeletonViewConverter = skeletonViewConverter
            return this
        }

        fun attach(view: LoadingSkeleton): LoadingSkeleton {
            if (skeletonAnimator == null) {
                skeletonAnimator = DefaultLoadingSkeletonAnimator.generate()
            }
            if (skeletonViewConverter == null) {
                skeletonViewConverter = LoadingSkeletonViewConverter.Builder().build()
            }

            return view.attach(this)
        }
    }
}
