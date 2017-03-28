package com.github.tehras.loadingskeleton.helpers

import android.support.annotation.ColorRes
import com.github.tehras.loadingskeleton.R

/**
 * This class will help the user to define the type of animation that will be used in this API
 */
class LoadingSkeletonAnimator private constructor() {

    class Builder {
        var color: Int = R.color.default_animation_color//-1 Denotes default
            private set
        var shimmer: Boolean = false
            private set

        fun color(@ColorRes color: Int): Builder {
            this.color = color
            return this
        }

        fun shimmer(enableShimmer: Boolean): Builder {
            this.shimmer = enableShimmer
            return this
        }

        fun build(): LoadingSkeletonAnimator {
            return LoadingSkeletonAnimator()
        }
    }
}
