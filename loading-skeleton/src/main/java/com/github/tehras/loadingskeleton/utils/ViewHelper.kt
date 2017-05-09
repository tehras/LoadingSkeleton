package com.github.tehras.loadingskeleton.utils

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import com.github.tehras.loadingskeleton.helpers.Options

/**
 * Returns darker version of specified `color`.
 */
fun darker(color: Int, factor: Float): Int {
    val a = Color.alpha(color)
    val r = Color.red(color)
    val g = Color.green(color)
    val b = Color.blue(color)

    return Color.argb(a,
            Math.max((r.times(factor)).toInt(), 0),
            Math.max((g.times(factor)).toInt(), 0),
            Math.max((b.times(factor)).toInt(), 0))
}

/**
 * This method converts color to a gradient drawable
 *
 * @return GradientDrawable - Gradient drawable with lighter color and 0.6f factor darker color
 */
fun gradientDrawable(options: Options, view: View): GradientDrawable {
    @Suppress("DEPRECATION")
    val lighterColor = view.context.resources.getColor(options.color)
    val darkerColor = darker(lighterColor, 0.8f)

    val gd = GradientDrawable(
            GradientDrawable.Orientation.TL_BR,
            intArrayOf(lighterColor, darkerColor))

    return gd
}

/**
 * This method assigns background based on Options selected
 *
 * The original color will become much lighter as well
 */
@Suppress("DEPRECATION")
fun assignBackground(options: Options, v: View) {
    val background: GradientDrawable
    if (options.gradient) {
        background = gradientDrawable(options, v)
    } else {
        background = GradientDrawable()
        background.setColor(v.resources.getColor(options.color))
    }

    background.cornerRadius = v.measuredHeight.toFloat().div(option.cornerRadius)
    if (!options.shimmer)
        background.alpha = 0.5f.times(255).toInt()

    v.background = background
}
