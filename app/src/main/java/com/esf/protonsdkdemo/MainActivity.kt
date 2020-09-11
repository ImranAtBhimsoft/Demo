package com.esf.protonsdkdemo

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.esf.protonsdkdemo.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private var mBinding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(mBinding!!.toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment)
            .addToBackStack(null)
            .setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out)
            .commit()
    }

    override fun setTitle(@StringRes titleId: Int) {
        mBinding!!.title.setText(titleId)
    }

}