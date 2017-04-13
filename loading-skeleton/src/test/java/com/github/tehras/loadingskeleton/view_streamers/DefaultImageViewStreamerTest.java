package com.github.tehras.loadingskeleton.view_streamers;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.widget.ImageView;

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
public class DefaultImageViewStreamerTest {

    private Activity activity;

    @Before
    public void setUp() {
        this.activity = setupActivity(Activity.class);
    }

    @Test
    public void convertAndRevertTest() {
        DefaultImageViewStreamer imageViewStreamer = new DefaultImageViewStreamer();
        Options o = new Options(true, true, 10f, R.color.loading_skeleton_default_animation_color);
        ImageView imageView = new ImageView(activity);
        Drawable drawable = new GradientDrawable();
        imageView.setImageDrawable(drawable);

        imageViewStreamer.start();
        imageViewStreamer.convert(activity, imageView, o);

        assertThat(imageView.getDrawable()).isNull();

        imageViewStreamer.stop();
        imageViewStreamer.revert(activity, imageView);

        assertThat(imageView.getDrawable()).isEqualTo(drawable);
    }
}
