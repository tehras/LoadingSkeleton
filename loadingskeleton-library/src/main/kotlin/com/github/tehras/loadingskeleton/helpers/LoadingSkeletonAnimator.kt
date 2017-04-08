package com.github.tehras.loadingskeleton.helpers

import com.github.tehras.loadingskeleton.R

/**
 * This class will help the user to define the type of animation that will be used in this API
 */
open class LoadingSkeletonAnimator private constructor() {

    var shimmer: Boolean = true
        private set

    private constructor(builder: Builder) : this() {
        this.shimmer = builder.shimmer
    }

    class Builder {
        internal var shimmer: Boolean = true
            private set

        fun shimmer(enableShimmer: Boolean): Builder {
            this.shimmer = enableShimmer
            return this
        }

        fun build(): LoadingSkeletonAnimator {
            return LoadingSkeletonAnimator(this)
        }
    }
}
