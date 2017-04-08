package com.github.tehras.loadingskeleton.helpers

import android.content.Context
import android.util.Log
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
    open fun stop() {}

    fun convertView(c: Context, v: View, color: Int) {
        Log.d(TAG, "convertView - ${v.javaClass.simpleName}")
        if (checkType(v)) {
            Log.d(TAG, "convert is being called")
            @Suppress("UNCHECKED_CAST")
            convert(c, v as T, color)
        }
    }

    fun revertView(c: Context, v: View) {
        Log.d(TAG, "revertView - ${v.javaClass.simpleName}")
        if (checkType(v)) {
            Log.d(TAG, "revert is being called")
            @Suppress("UNCHECKED_CAST")
            revert(c, v as T)
        }
    }

    abstract fun convert(c: Context, v: T, color: Int)
    abstract fun revert(c: Context, v: T)
}