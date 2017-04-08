package com.github.tehras.loadingskeleton.animators

import com.github.tehras.loadingskeleton.helpers.LoadingSkeletonAnimator

object DefaultLoadingSkeletonAnimator {
    fun generate(): LoadingSkeletonAnimator {
        return LoadingSkeletonAnimator.Builder().shimmer(true).build()
    }
}