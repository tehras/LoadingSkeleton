package com.github.tehras.loadingskeleton.shadows;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

import org.apache.tools.ant.types.resources.Resources;
import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;

@Implements(Resources.class)
public class ShadowResources {

    @Deprecated
    @Implementation
    public Drawable getDrawable(int id) throws android.content.res.Resources.NotFoundException {
        return new ColorDrawable();
    }

    @Deprecated
    @Implementation
    public int getColor(int id) throws android.content.res.Resources.NotFoundException {
        return Color.RED;
    }
}
