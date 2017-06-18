package com.github.tehras.loadingskeleton.helpers

import android.content.Context
import android.view.View

/**
 * Loading Skeleton View Streamer is in charge of converting a view into the Skeleton Loading View
 */
abstract class LoadingSkeletonViewStreamer<T : View>(val clazz: Class<T>) {

    private val TAG = "LoadingSkeletonView"

    fun checkType(v: View): Boolean {
        return clazz.isAssignableFrom(v.javaClass)
    }

    /**
     * This method is called when start Loading Skeleton is called
     * Override this if you need to clear/start any variables that you're keeping track off
     */
    open fun start() {
    }


    /**
     * This method is called when stop Loading Skeleton is called
     * Override this if you need to clear/start any variables that you're keeping track off
     */
    open fun stop() {
    }

    fun convertView(c: Context, v: View, options: Options) {
        if (checkType(v)) {
            @Suppress("UNCHECKED_CAST")
            convert(c, v as T, options)
        }
    }

    fun revertView(c: Context, v: View) {
        if (checkType(v)) {
            @Suppress("UNCHECKED_CAST")
            revert(c, v as T)
        }
    }

    abstract fun convert(c: Context, v: T, options: Options)
    abstract fun revert(c: Context, v: T)
}