package com.esf.protonsdkdemo.utils

import android.animation.Animator
import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import com.esf.protonsdkdemo.fragments.BaseFragment
import com.metallicus.protonsdk.common.SingletonHolder
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Created by m.imran
 * Senior Software Engineer at
 * BhimSoft on 09/09/2020.
 */
class VibratorHelper private constructor(private val parent: BaseFragment) {
    companion object : SingletonHolder<VibratorHelper, BaseFragment>(::VibratorHelper)

    private val isInProgress: AtomicBoolean = AtomicBoolean(false)

    fun dispatch(view: View, callback: Callback?) {
        if (isInProgress.get()) return
        val rotate = ObjectAnimator.ofFloat(
            view,
            "translationX",
            0.0f
                    - 10.0f,
            0.0f,
            10.0f,
            0.0f
        )
        rotate.repeatCount = 5
        rotate.duration = 50
        rotate.interpolator = AccelerateDecelerateInterpolator()
        rotate.addListener(object : AnimatorListenerWrapper() {
            override fun onAnimationStart(animation: Animator?) {
                isInProgress.set(true)
            }

            override fun onAnimationEnd(animation: Animator?) {
                isInProgress.set(false)
                callback?.onEnd()
            }

            override fun onAnimationCancel(animation: Animator?) {
                isInProgress.set(false)
            }

        })
        parent.vibrate()
        rotate.start()
    }

    fun interface Callback {
         fun onEnd()
    }
}