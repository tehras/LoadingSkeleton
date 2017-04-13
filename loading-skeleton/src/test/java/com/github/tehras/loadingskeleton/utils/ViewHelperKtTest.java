package com.github.tehras.loadingskeleton.utils;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.LinearLayout;

import com.github.tehras.loadingskeleton.BuildConfig;
import com.github.tehras.loadingskeleton.R;
import com.github.tehras.loadingskeleton.helpers.Options;
import com.github.tehras.loadingskeleton.shadows.ShadowResources;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static com.google.common.truth.Truth.assertThat;
import static org.robolectric.Robolectric.setupActivity;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, shadows = {ShadowResources.class})
public class ViewHelperKtTest {


    private Activity activity;

    @Before
    public void setUp() {
        this.activity = setupActivity(Activity.class);
    }

    @Test
    public void darkerTest() {
        int color = Color.RED;

        //Just makes sure no crash
        ViewHelperKt.darker(color, .5f);
        ViewHelperKt.darker(color, 1.5f);
        ViewHelperKt.darker(color, 25.5f);
        ViewHelperKt.darker(color, -5.5f);
    }

    @Test
    public void gradientTest() {
        Options o1 = new Options(true, true, 10f, R.color.loading_skeleton_default_animation_color);

        GradientDrawable gd = ViewHelperKt.gradientDrawable(o1, new View(activity));

        assertThat(gd.getCornerRadius()).isEqualTo(0f);
        assertThat(gd.getGradientType()).isEqualTo(0);
    }

    @Test
    public void assignBackgroundtest() {
        Options o1 = new Options(true, true, 10f, R.color.loading_skeleton_default_animation_color);

        LinearLayout v = new LinearLayout(activity);

        ViewHelperKt.assignBackground(o1, v);

        assertThat(v.getBackground()).isInstanceOf(GradientDrawable.class);
        assertThat(v.getBackground().getAlpha()).isEqualTo(255);
        assertThat(((GradientDrawable) v.getBackground()).getCornerRadius()).isEqualTo(0f);

        o1 = new Options(true, false, 10f, R.color.loading_skeleton_default_animation_color);
        ViewHelperKt.assignBackground(o1, v);

        assertThat(v.getBackground()).isInstanceOf(GradientDrawable.class);
        assertThat(v.getBackground().getAlpha()).isEqualTo(127);
        assertThat(((GradientDrawable) v.getBackground()).getCornerRadius()).isEqualTo(0f);

    }

}
