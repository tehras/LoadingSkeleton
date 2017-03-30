package com.github.tehras.loadingskeletonapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.github.tehras.loadingskeleton.LoadingSkeleton
import com.github.tehras.loadingskeleton.helpers.LoadingSkeletonAnimator

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val loadingSkeleton: LoadingSkeleton = findViewById(R.id.include_2) as LoadingSkeleton
        loadingSkeleton.skeletonAnimator = LoadingSkeletonAnimator.Builder().shimmer(false).build()

        val startButton: Button = findViewById(R.id.start_button) as Button
        val stopButton: Button = findViewById(R.id.stop_button) as Button

        startButton.setOnClickListener { loadingSkeleton.start() }
        stopButton.setOnClickListener { loadingSkeleton.stop() }

//        v.addView(LoadingSkeleton.Builder(R.layout.layout_test).skeletonAnimator(LoadingSkeletonAnimator.Builder().shimmer(false).build()).build(this))
    }
}
