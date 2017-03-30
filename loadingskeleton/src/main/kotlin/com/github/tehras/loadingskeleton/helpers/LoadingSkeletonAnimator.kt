package com.github.tehras.loadingskeleton.helpers

import com.github.tehras.loadingskeleton.R

/**
 * This class will help the user to define the type of animation that will be used in this API
 */
open class LoadingSkeletonAnimator private constructor() {

    var shimmer: Boolean = false
        private set
    var color: Int = -1
        private set

    private constructor(builder: Builder) : this() {
        this.shimmer = builder.shimmer
        this.color = builder.color
    }

    class Builder {
        var color: Int = R.color.default_animation_color//-1 Denotes default
            private set
        var shimmer: Boolean = false
            private set

        /**
         * Please make sure this is ColorRes
         */
        fun color(color: Int): Builder {
            this.color = color
            return this
        }

        fun shimmer(enableShimmer: Boolean): Builder {
            this.shimmer = enableShimmer
            return this
        }

        fun build(): LoadingSkeletonAnimator {
            return LoadingSkeletonAnimator(this)
        }
    }
}
