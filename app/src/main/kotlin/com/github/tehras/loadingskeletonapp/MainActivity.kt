package com.github.tehras.loadingskeletonapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.github.tehras.loadingskeleton.LoadingSkeleton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val v = findViewById(R.id.include_2)
        val loadingSkeleton = LoadingSkeleton.Builder().attach(v as LoadingSkeleton)

        val startButton: Button = findViewById(R.id.start_button) as Button
        val stopButton: Button = findViewById(R.id.stop_button) as Button

        startButton.setOnClickListener { loadingSkeleton.start() }
        stopButton.setOnClickListener { loadingSkeleton.stop() }
    }
}
