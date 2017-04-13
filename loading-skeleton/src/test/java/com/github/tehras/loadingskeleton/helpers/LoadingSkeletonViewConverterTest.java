package com.github.tehras.loadingskeleton.helpers;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.widget.ImageView;

import com.github.tehras.loadingskeleton.BuildConfig;
import com.github.tehras.loadingskeleton.R;
import com.github.tehras.loadingskeleton.ReflectionUtils;
import com.github.tehras.loadingskeleton.view_streamers.DefaultImageViewStreamer;
import com.github.tehras.loadingskeleton.view_streamers.DefaultTextViewStreamer;
import com.google.common.truth.Truth;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.lang.reflect.Modifier;
import java.util.ArrayList;

import static com.google.common.truth.Truth.*;
import static org.robolectric.Robolectric.setupActivity;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class LoadingSkeletonViewConverterTest {


    private Activity activity;

    @Before
    public void setUp() {
        this.activity = setupActivity(Activity.class);
    }

    @Test
    public void builderTest() {
        LoadingSkeletonViewConverter.Builder builder = new LoadingSkeletonViewConverter.Builder();

        builder.shimmer(true);
        assertThat(builder.getShimmer()).isTrue();
        builder.shimmer(false);
        assertThat(builder.getShimmer()).isFalse();

        builder.cornerRadius(10f);
        assertThat(builder.getCornerRadius()).isEqualTo(10f);

        builder.gradient(true);
        assertThat(builder.getGradient()).isTrue();
        builder.gradient(false);
        assertThat(builder.getGradient()).isFalse();

        builder.addConvert(new DefaultImageViewStreamer());
        assertThat(builder.getConverters().size()).isEqualTo(1);

        builder.color(20);
        assertThat(builder.getColor());

        LoadingSkeletonViewConverter loadingSkeletonViewConverter = builder.build();

        Options o = ReflectionUtils.getProperty(loadingSkeletonViewConverter, "options");

        assertThat(o.getCornerRadius()).isEqualTo(10f);
        assertThat(o.getShimmer()).isFalse();
        assertThat(o.getGradient()).isFalse();
        assertThat(o.getColor()).isEqualTo(20);

        ArrayList<LoadingSkeletonViewStreamer<?>> loadingSkeletonViewStreamers = ReflectionUtils.getProperty(loadingSkeletonViewConverter, "converters");
        assertThat(loadingSkeletonViewStreamers.size()).isEqualTo(1);
        assertThat(loadingSkeletonViewStreamers.get(0).getClass()).isEqualTo(DefaultImageViewStreamer.class);
    }

    @Test
    public void defaultBuilderTest() {
        LoadingSkeletonViewConverter.Builder builder = new LoadingSkeletonViewConverter.Builder();

        LoadingSkeletonViewConverter loadingSkeletonViewConverter = builder.build();

        Options o = ReflectionUtils.getProperty(loadingSkeletonViewConverter, "options");

        assertThat(o.getCornerRadius()).isEqualTo(5f);
        assertThat(o.getShimmer()).isTrue();
        assertThat(o.getGradient()).isTrue();
        assertThat(o.getColor()).isEqualTo(R.color.loading_skeleton_default_animation_color);

        ArrayList<LoadingSkeletonViewStreamer<?>> loadingSkeletonViewStreamers = ReflectionUtils.getProperty(loadingSkeletonViewConverter, "converters");
        assertThat(loadingSkeletonViewStreamers.size()).isEqualTo(2);
        assertThat(loadingSkeletonViewStreamers.get(1).getClass()).isEqualTo(DefaultTextViewStreamer.class);
        assertThat(loadingSkeletonViewStreamers.get(0).getClass()).isEqualTo(DefaultImageViewStreamer.class);
    }

    @Test
    public void convertRevertTest() {
        Drawable drawable = new BitmapDrawable();

        ImageView imageView = new ImageView(activity);
        imageView.setImageDrawable(drawable);

        LoadingSkeletonViewConverter converter = new LoadingSkeletonViewConverter.Builder().build();

        assertThat(ReflectionUtils.getProperty(converter, "isFirstRevert")).isEqualTo(true);
        assertThat(ReflectionUtils.getProperty(converter, "isFirstConvert")).isEqualTo(true);

        converter.convertView(imageView);

        assertThat(ReflectionUtils.getProperty(converter, "isFirstRevert")).isEqualTo(true);
        assertThat(ReflectionUtils.getProperty(converter, "isFirstConvert")).isEqualTo(false);

        assertThat(imageView.getBackground()).isNotEqualTo(drawable);

        converter.revertView(imageView);

        assertThat(ReflectionUtils.getProperty(converter, "isFirstRevert")).isEqualTo(false);
        assertThat(ReflectionUtils.getProperty(converter, "isFirstConvert")).isEqualTo(true);

        assertThat(imageView.getDrawable()).isEqualTo(drawable);
    }

}
