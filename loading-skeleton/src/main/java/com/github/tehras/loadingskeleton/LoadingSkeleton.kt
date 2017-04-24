package com.github.tehras.loadingskeleton

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import com.facebook.shimmer.ShimmerFrameLayout
import com.github.tehras.loadingskeleton.helpers.LoadingSkeletonViewConverter

/**
 * Loading Skeleton object is here to convert a normal R.layout into a
 * great looking Facebook-like loading skeleton
 */
@Suppress("unused")
class LoadingSkeleton constructor(context: Context, attrs: AttributeSet?, defStyleAttrs: Int) : FrameLayout(context, attrs, defStyleAttrs) {

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    private var layoutFinished: Boolean = false

    /**
     * This is assigned to the temporary Container layout
     */
    private val containerViewId: Int = 10001
    /**
     * Stores original ID
     */
    private var originalContainerId: Int = -1

    var skeletonViewConverter: LoadingSkeletonViewConverter

    init {
        skeletonViewConverter = LoadingSkeletonViewConverter.Builder().build()
    }

    fun skeletonViewConverter(skeletonViewConverter: LoadingSkeletonViewConverter): LoadingSkeleton {
        this.skeletonViewConverter = skeletonViewConverter

        return this
    }

    private fun checkChildCount() {
        if (childCount != 1)
            throw RuntimeException("View must have 1 child")
    }

    private fun checkViewGroup(layout: View, func: (ViewGroup) -> Unit) {
        if (layout is ViewGroup) {
            func(layout)
        } else {
            throw RuntimeException("Layout must be a ViewGroup")
        }
    }

    fun stop() {
        //validations
        checkChildCount()
        checkViewGroup(getChildAt(0), {})

        val container = getChildAt(0) as ViewGroup

        if (container.id != containerViewId)
            return

        if (originalContainerId != -1)
            container.id = originalContainerId

        val child = container.getChildAt(0)

        revertView(container, this.skeletonViewConverter)
        container.removeView(child)

        this.removeView(container)
        this.addView(child)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        layoutFinished = true
    }

    fun start() {
        if (layoutFinished) {
            startWhenLayoutFinished()
        } else {
            this.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    this@LoadingSkeleton.viewTreeObserver.removeOnGlobalLayoutListener(this)
                    layoutFinished = true
                    layoutFinished = true
                    startWhenLayoutFinished()
                }

            })
        }
    }

    private fun startWhenLayoutFinished() {
        checkChildCount()

        val layout = getChildAt(0)

        if (layout.id == containerViewId)
            return

        originalContainerId = layout.id

        this.removeView(layout)
        checkViewGroup(layout, { populateView(it, skeletonViewConverter) })

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
