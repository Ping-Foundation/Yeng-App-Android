package `in`.yeng.user

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator


object AnimUtil {
    fun fadeDown(view: View, duration: Long = 1200, initialOffset: Float = 100f, scaleValue: Float = 0.9f) {
        val translate = ObjectAnimator.ofFloat(view, "translationY", -initialOffset, 0f)
        translate.duration = duration
        translate.interpolator = DecelerateInterpolator(2f)
        translate.start()

        val alpha = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
        alpha.duration = duration * 8 / 10
        alpha.interpolator = AccelerateInterpolator(0.9f)
        alpha.start()

        val scale = ObjectAnimator.ofFloat(view, "scaleX", scaleValue, 1f)
        scale.duration = duration * 8 / 10
        scale.interpolator = DecelerateInterpolator(2f)
        scale.start()

    }

    fun fadeUp(view: View, duration: Long = 1200, initialOffset: Float = 100f, scaleValue: Float = 0.9f) {
        val translate = ObjectAnimator.ofFloat(view, "translationY", initialOffset, 0f)
        translate.duration = duration
        translate.interpolator = DecelerateInterpolator(2f)
        translate.start()

        val alpha = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
        alpha.duration = duration * 8 / 10
        alpha.interpolator = AccelerateInterpolator(0.9f)
        alpha.start()

        val scale = ObjectAnimator.ofFloat(view, "scaleX", scaleValue, 1f)
        scale.duration = duration * 8 / 10
        scale.interpolator = DecelerateInterpolator(2f)
        scale.start()

    }

    fun clickAnimation(view: View) {

        val scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.95f, 1f)
        scaleX.duration = 200
        scaleX.start()

        val scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.95f, 1f)
        scaleY.duration = 300
        scaleY.start()
    }

}