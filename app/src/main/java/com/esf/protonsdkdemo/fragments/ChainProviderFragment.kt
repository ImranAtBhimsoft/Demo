package com.esf.protonsdkdemo.fragments

import android.os.Bundle
import android.view.View
import androidx.lifecycle.LiveData
import com.esf.protonsdkdemo.utils.Callback
import com.esf.protonsdkdemo.R
import com.esf.protonsdkdemo.databinding.FragmentChainProviderBinding
import com.esf.protonsdkdemo.viewmodels.ChainProviderViewModel
import com.metallicus.protonsdk.common.Resource
import com.metallicus.protonsdk.model.ChainProvider
import timber.log.Timber

/**
 * Created by m.imran
 * Senior Software Engineer at
 * BhimSoft on 08/09/2020.
 */
class ChainProviderFragment : BaseFragment() {
    private var mBinding: FragmentChainProviderBinding? = null
    override fun getLayout(): Int {
        return R.layout.fragment_chain_provider
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHomeAsUp(true)
        setTitle(R.string.chain_provider)
        Timber.d("onViewCreated()")
        mBinding = FragmentChainProviderBinding.bind(view)
        getData()
    }

    private fun getData() {
        val chainProvider: LiveData<Resource<ChainProvider>> = mProton!!.getChainProvider()
        enqueue(chainProvider, object : Callback<ChainProvider>() {
            override fun onSuccess(response: ChainProvider?) {
                Timber.d(">>>>> ${response.toString()}")
                mBinding!!.viewModel = ChainProviderViewModel(response)
            }

            override fun onError(throwable: Throwable) {
                Timber.d("Error ${throwable.message}")
                Timber.e(throwable)
            }
        })

    }
}