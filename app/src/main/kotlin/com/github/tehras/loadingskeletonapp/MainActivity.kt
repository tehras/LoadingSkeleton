package com.github.tehras.loadingskeletonapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import com.github.tehras.loadingskeleton.LoadingSkeleton
import com.github.tehras.loadingskeleton.helpers.LoadingSkeletonAnimator

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val v: LinearLayout = findViewById(R.id.linear_layout) as LinearLayout

        v.addView(LoadingSkeleton.Builder(R.layout.layout_test).skeletonAnimator(LoadingSkeletonAnimator.Builder().shimmer(false).build()).build(this))
    }
}
