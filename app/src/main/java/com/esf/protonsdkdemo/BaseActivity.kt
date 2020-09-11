package com.esf.protonsdkdemo

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.MenuItem
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import java.util.concurrent.TimeUnit


/**
 * Created by m.imran
 * Senior Software Engineer at
 * BhimSoft on 09/09/2020.
 */
abstract class BaseActivity : AppCompatActivity() {
    companion object {
        private const val VIBRATE_DURATION = 255L
    }

    private var mVibe: Vibrator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mVibe = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else ->
                super.onOptionsItemSelected(item)
        }
    }

    abstract fun addFragment(fragment: Fragment)

    override fun setTitle(@StringRes titleId: Int) {

    }

    fun setHomeAsUp(isHomeUp: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(isHomeUp)
    }

    fun vibrate() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val vibrationEffect =
                VibrationEffect.createOneShot(VIBRATE_DURATION, VibrationEffect.DEFAULT_AMPLITUDE)
            mVibe?.vibrate(vibrationEffect)
        } else {
            @Suppress("DEPRECATION")
            mVibe?.vibrate(TimeUnit.MILLISECONDS.toMillis(VIBRATE_DURATION))
        }
    }
}