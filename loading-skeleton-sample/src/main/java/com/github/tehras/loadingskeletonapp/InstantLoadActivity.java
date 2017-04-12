package com.github.tehras.loadingskeletonapp;

import android.os.Bundle;
import android.view.View;

import com.github.tehras.loadingskeleton.LoadingSkeleton;
import com.github.tehras.loadingskeleton.helpers.LoadingSkeletonViewConverter;

import org.jetbrains.annotations.Nullable;

public class InstantLoadActivity extends MainActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LoadingSkeleton shimmerView = (LoadingSkeleton) findViewById(R.id.include_2);

        //call this instantly
        shimmerView.skeletonViewConverter(new LoadingSkeletonViewConverter.Builder()
                .color(R.color.colorPrimary)
                .shimmer(true)
                .cornerRadius(10)
                .build()).start();
    }

    @Override
    protected void onStart() {
        super.onStart();

        findViewById(R.id.instant_activity).setVisibility(View.GONE);
    }
}
