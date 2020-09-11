package com.esf.protonsdkdemo.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.esf.protonsdkdemo.R
import com.esf.protonsdkdemo.databinding.FragmentMainBinding
import com.esf.protonsdkdemo.utils.Callback
import com.esf.protonsdkdemo.utils.VibratorHelper
import com.google.android.material.snackbar.Snackbar
import com.metallicus.protonsdk.model.TokenCurrencyBalance
import timber.log.Timber


/**
 * Created by m.imran
 * Senior Software Engineer at
 * BhimSoft on 08/09/2020.
 */
class MainFragment : BaseFragment() {
    override fun getLayout(): Int {
        return R.layout.fragment_main
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("onViewCreated()")
        setHomeAsUp(false)
        setTitle(R.string.proton_sdk_test)
        val binding: FragmentMainBinding = FragmentMainBinding.bind(view)
        binding.chainProvider.setOnClickListener { v ->

            VibratorHelper.getInstance(this).dispatch(v) {
                addFragment(ChainProviderFragment())
            }
        }
        binding.protonAccount.setOnClickListener { v ->
            VibratorHelper.getInstance(this).dispatch(v) {
                addFragment(ProtonAccountFragment())
            }
        }
        binding.tokenContracts.setOnClickListener { v ->
            VibratorHelper.getInstance(this).dispatch(v) {
                addFragment(TokenContractsFragment())
            }
        }
        binding.tokenBalances.setOnClickListener { v ->
            VibratorHelper.getInstance(this).dispatch(v, null)
            test()
        }
        binding.transactionHistory.setOnClickListener { v ->
            VibratorHelper.getInstance(this).dispatch(v, null)
        }
        binding.updateAccount.setOnClickListener { v ->
            VibratorHelper.getInstance(this).dispatch(v, null)
        }
    }

    private fun test() {
        enqueue(mProton!!.getActiveAccountTokenBalances(),
            object : Callback<List<TokenCurrencyBalance>>() {
                override fun onSuccess(response: List<TokenCurrencyBalance>?) {
                    Toast.makeText(requireContext(),"ActiveBalance ->Success",Toast.LENGTH_SHORT).show()
                    Timber.d(">>>>ActiveBalance $response")
                }

                override fun onError(throwable: Throwable) {
                    Timber.e(">>>>ActiveBalance $throwable")
                }

            })
    }
}