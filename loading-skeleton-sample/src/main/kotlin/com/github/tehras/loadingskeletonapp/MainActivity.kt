package com.github.tehras.loadingskeletonapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import com.github.tehras.loadingskeleton.LoadingSkeleton
import com.github.tehras.loadingskeleton.helpers.LoadingSkeletonViewConverter
import kotlinx.android.synthetic.main.activity_main.*


open class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val shimmerView = findViewById(R.id.include_2) as LoadingSkeleton
        shimmerView.skeletonViewConverter(LoadingSkeletonViewConverter.Builder().color(R.color.colorPrimary).build())

        val normalView = findViewById(R.id.include) as LoadingSkeleton
        normalView.skeletonViewConverter(LoadingSkeletonViewConverter.Builder().color(R.color.colorPrimary).shimmer(false).build())

        val startButton: Button = findViewById(R.id.start_button) as Button
        val stopButton: Button = findViewById(R.id.stop_button) as Button

        startButton.setOnClickListener {
            shimmerView.start()
            normalView.start()
        }
        stopButton.setOnClickListener {
            shimmerView.stop()
            normalView.stop()
        }

        instant_activity.setOnClickListener { startActivity(Intent(this, InstantLoadActivity::class.java)) }
    }

    override fun onStart() {
        super.onStart()

        instant_activity.visibility = View.VISIBLE
    }
}
