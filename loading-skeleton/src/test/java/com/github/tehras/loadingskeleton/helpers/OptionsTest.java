package com.github.tehras.loadingskeleton.helpers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static com.google.common.truth.Truth.assertThat;


@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class OptionsTest {

    @Test
    public void equalsTest() {
        Options o1 = new Options(true, true, 10f, 1);
        Options o2 = new Options(true, true, 10f, 1);

        assertThat(o1).isEqualTo(o2);
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
    public void copyTest() {
        Options o1 = new Options(true, true, 10f, 1);
        Options o2 = o1.copy(true, true, 10f, 1);

        assertThat(o1).isEqualTo(o2);
    }

}
