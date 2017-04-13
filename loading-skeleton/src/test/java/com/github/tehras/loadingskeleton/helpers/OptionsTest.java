package com.github.tehras.loadingskeleton.helpers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

import static com.google.common.truth.Truth.assertThat;


@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class OptionsTest {

    @Test
    public void equalsTest() {
        Options o1 = new Options(true, true, 10f, 1);
        Options o2 = new Options(true, true, 10f, 1);
        Options o3 = new Options(true, false, 10f, 1);
        Options o4 = new Options(true, true, 0f, 1);
        Options o5 = new Options(false, true, 0f, 1);
        Options o6 = new Options(true, true, 0f, 2);

        assertThat(o1).isEqualTo(o2);
        assertThat(o1).isNotEqualTo(o3);
        assertThat(o1).isNotEqualTo(o4);
        assertThat(o1).isNotEqualTo(o5);
        assertThat(o1).isNotEqualTo(o6);
    }


    @Test
    public void getterTests() {
        Options o1 = new Options(true, true, 10f, 1);

        assertThat(o1.getColor()).isEqualTo(1);
        assertThat(o1.getGradient()).isTrue();
        assertThat(o1.getCornerRadius()).isEqualTo(10f);
        assertThat(o1.getShimmer()).isTrue();
    }

    @Test
    public void toStringTest() {
        Options o1 = new Options(true, true, 10f, 1);

        assertThat(o1.toString()).isEqualTo("Options(gradient=true, shimmer=true, cornerRadius=10.0, color=1)");
    }

    @Test
    public void hashCodeTest() {
        Options o1 = new Options(true, true, 10f, 1);
        Options o2 = new Options(true, true, 10f, 1);
        Options o3 = new Options(true, true, 10f, 2);

        ArrayList<Options> options = new ArrayList<>();

        options.add(o1);

        assertThat(options.contains(o2)).isTrue();
        assertThat(options.contains(o3)).isFalse();

        assertThat(o1.hashCode()).isEqualTo(o2.hashCode());
        assertThat(o1.hashCode()).isNotEqualTo(o3.hashCode());
    }

    @Test
    public void copyTest() {
        Options o1 = new Options(true, true, 10f, 1);
        Options o2 = o1.copy(true, true, 10f, 1);

        assertThat(o1).isEqualTo(o2);
    }

}
