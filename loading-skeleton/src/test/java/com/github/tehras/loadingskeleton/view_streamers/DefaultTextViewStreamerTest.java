package com.github.tehras.loadingskeleton.view_streamers;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.widget.TextView;

import com.github.tehras.loadingskeleton.BuildConfig;
import com.github.tehras.loadingskeleton.R;
import com.github.tehras.loadingskeleton.helpers.Options;
import com.github.tehras.loadingskeleton.shadows.ShadowResources;
import com.google.common.truth.Truth;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static com.google.common.truth.Truth.assertThat;
import static org.robolectric.Robolectric.setupActivity;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, shadows = {ShadowResources.class})
public class DefaultTextViewStreamerTest {

    private Activity activity;

    @Before
    public void setUp() {
        this.activity = setupActivity(Activity.class);
    }

    @Test
    public void convertTest() {
        DefaultTextViewStreamer defaultTextViewStreamer = new DefaultTextViewStreamer();

        Drawable drawable = new GradientDrawable();
        drawable.setAlpha(200);

        TextView t = new TextView(activity);
        t.setBackground(drawable);
        Options o = new Options(true, true, 10f, R.color.loading_skeleton_default_animation_color);

        defaultTextViewStreamer.convert(activity, t, o);

        assertThat(t.getBackground().getAlpha()).isNotEqualTo(200);

        t.setText("Test");

        defaultTextViewStreamer.convert(activity, t, o);

        assertThat(t.getText()).isNotEqualTo("Test");
    }

    @Test
    public void revertTest() {
        DefaultTextViewStreamer defaultTextViewStreamer = new DefaultTextViewStreamer();

        Drawable drawable = new GradientDrawable();
        drawable.setAlpha(200);

        TextView t = new TextView(activity);
        t.setText("Test");
        t.setBackground(drawable);
        Options o = new Options(true, true, 10f, R.color.loading_skeleton_default_animation_color);

        defaultTextViewStreamer.start();
        defaultTextViewStreamer.convert(activity, t, o);

        assertThat(t.getText()).isNotEqualTo("Test");
        assertThat(t.getBackground().getAlpha()).isNotEqualTo(200);

        defaultTextViewStreamer.stop();
        defaultTextViewStreamer.revert(activity, t);

        assertThat(t.getText()).isEqualTo("Test");
        assertThat(t.getBackground().getAlpha()).isEqualTo(200);
    }

}
