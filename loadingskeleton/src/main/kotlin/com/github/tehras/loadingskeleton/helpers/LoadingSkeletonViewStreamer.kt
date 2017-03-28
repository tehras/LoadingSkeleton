package com.github.tehras.loadingskeleton.helpers

import android.content.Context
import android.view.View

/**
 * Loading Skeleton View Streamer is in charge of converting a view into the Skeleton Loading View
 */
abstract class LoadingSkeletonViewStreamer<out T> {
    abstract fun <T> streamView(c: Context, v: T)
    abstract fun convertToType(v: View): T?
}