package com.esf.protonsdkdemo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.esf.protonsdkdemo.BaseActivity
import com.esf.protonsdkdemo.utils.Callback
import com.metallicus.protonsdk.Proton
import com.metallicus.protonsdk.common.Resource
import com.metallicus.protonsdk.common.Status
import timber.log.Timber

/**
 * Created by m.imran
 * Senior Software Engineer at
 * BhimSoft on 08/09/2020.
 */
abstract class BaseFragment : Fragment() {
    companion object {
        val PublicKey = "EOS7mYuMqNZEfEBPzNTo5HVJZttB4drzWsLryfSNZYJZR8q97CrQm"
        val Privatekey = "5JWmwASv7YzoeR1NaSN2SeiBPH8qZ5mK86yCBr2RHf7J88B5Tjx"
        val ACCOUNT_NAME = "chainloins"
    }

    protected var mProton: Proton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mProton = Proton.getInstance(requireContext())
        setHasOptionsMenu(true)
        setHomeAsUp(false)
    }

    protected abstract fun getLayout(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayout(), container, false)
    }

    fun addFragment(fragment: Fragment) {
        val baseActivity: BaseActivity = requireActivity() as BaseActivity
        baseActivity.addFragment(fragment)
    }

    fun setTitle(@StringRes id: Int) {
        val baseActivity: BaseActivity = requireActivity() as BaseActivity
        baseActivity.setTitle(id)
    }

    fun setHomeAsUp(isHomeUp: Boolean) {
        val baseActivity: BaseActivity = requireActivity() as BaseActivity
        baseActivity.setHomeAsUp(isHomeUp)
    }

    fun vibrate() {
        val baseActivity: BaseActivity = requireActivity() as BaseActivity
        baseActivity.vibrate()
    }

    fun <T> enqueue(call: LiveData<Resource<T>>, callback: Callback<T>) {
        val observer: Observer<Resource<T>> = Observer { chainProvider ->
            when (chainProvider.status) {
                Status.LOADING -> {
                    Timber.d("Loading")
                    callback.onLoading()
                }
                Status.SUCCESS -> {
                    callback.onSuccess(chainProvider.data)
                    Timber.d("Success and Data is ${chainProvider.data.toString()}")
                }
                else -> {
                    callback.onError(Throwable("Code:" + chainProvider.code + " and message:" + chainProvider.message))
                    Timber.d("Error ${chainProvider.message}")
                }
            }
        }
        call.observe(viewLifecycleOwner, observer)
    }
}