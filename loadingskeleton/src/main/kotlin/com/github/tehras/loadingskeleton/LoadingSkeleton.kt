package com.github.tehras.loadingskeleton

import android.content.Context
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.facebook.shimmer.ShimmerFrameLayout
import com.github.tehras.loadingskeleton.helpers.LoadingSkeletonAnimator
import com.github.tehras.loadingskeleton.helpers.LoadingSkeletonViewConverter

/**
 * Loading Skeleton object is here to convert a normal R.layout into a
 * great looking Facebook-like loading skeleton
 */
@Suppress("unused")
class LoadingSkeleton private constructor(context: Context?) : LinearLayout(context) {

    private lateinit var layout: ViewGroup
    private lateinit var builder: Builder

    private constructor(builder: Builder, context: Context) : this(context) {
        this.layout = LayoutInflater.from(context).inflate(builder.layout, null, false) as ViewGroup
        this.builder = builder

        if (layout is ViewGroup) {
            populateView(layout, builder)
        } else {
            throw Exception("Layout must be a ViewGroup")
        }

        val container: ViewGroup

        View.inflate(context, R.layout.loadin)
        val frameLayout: LinearLayout = LinearLayout(context)
        val p = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        frameLayout.layoutParams = p

        addView(frameLayout)

        val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)

        if (true) {
            container = ShimmerFrameLayout(context)
        } else {
            container = LinearLayout(context)
        }
        container.layoutParams = params

        frameLayout.addView(layout)

        if (container is ShimmerFrameLayout)
            container.startShimmerAnimation()
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

    class Builder(@LayoutRes val layout: Int) {
        var skeletonAnimator: LoadingSkeletonAnimator? = null
            private set
        var skeletonViewConverter: LoadingSkeletonViewConverter? = null
            private set

        fun skeletonAnimator(skeletonAnimator: LoadingSkeletonAnimator): Builder {
            this.skeletonAnimator = skeletonAnimator
            return this
        }

        fun skeletonViewConverter(skeletonViewConverter: LoadingSkeletonViewConverter): Builder {
            this.skeletonViewConverter = skeletonViewConverter
            return this
        }

        fun build(context: Context): LoadingSkeleton = LoadingSkeleton(this, context)
    }

}
