package com.esf.protonsdkdemo.fragments

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.esf.protonsdkdemo.utils.Callback
import com.esf.protonsdkdemo.R
import com.esf.protonsdkdemo.adapters.TokenContractsAdapter
import com.esf.protonsdkdemo.databinding.FragmentRecyclerViewBinding
import com.metallicus.protonsdk.model.TokenContract
import timber.log.Timber

/**
 * Created by m.imran
 * Senior Software Engineer at
 * BhimSoft on 08/09/2020.
 */
class TokenContractsFragment : BaseFragment() {
    private var mBinding: FragmentRecyclerViewBinding? = null
    private var mAccountsAdapter: TokenContractsAdapter? = null

    override fun getLayout(): Int {
        return R.layout.fragment_recycler_view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHomeAsUp(true)
        setTitle(R.string.token_contracts)
        mBinding = FragmentRecyclerViewBinding.bind(view)
        mBinding!!.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        mAccountsAdapter = TokenContractsAdapter(requireContext(), mBinding!!.noData)
        mBinding!!.recyclerView.adapter = mAccountsAdapter
        getData()
    }

    private fun getData() {
        enqueue(mProton!!.getTokenContracts(), object : Callback<List<TokenContract>>() {
            override fun onLoading() {
                mBinding!!.progressBar.visibility = View.VISIBLE
            }

            override fun onSuccess(response: List<TokenContract>?) {
                Timber.d(">>>>>onSuccess $response")
                mAccountsAdapter?.setData(response!!)
                mBinding!!.progressBar.visibility = View.GONE
            }

            override fun onError(throwable: Throwable) {
                Timber.d("Error ${throwable.message}")
                Timber.e(throwable)
                mBinding!!.progressBar.visibility = View.GONE
            }
        })
    }
}