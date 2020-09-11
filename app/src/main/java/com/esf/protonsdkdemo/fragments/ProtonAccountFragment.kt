package com.esf.protonsdkdemo.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.esf.protonsdkdemo.R
import com.esf.protonsdkdemo.adapters.AccountsAdapter
import com.esf.protonsdkdemo.databinding.FragmentRecyclerViewBinding
import com.esf.protonsdkdemo.utils.Callback
import com.esf.protonsdkdemo.utils.ItemClickListener
import com.metallicus.protonsdk.common.Resource
import com.metallicus.protonsdk.model.Account
import com.metallicus.protonsdk.model.ActiveAccount
import com.metallicus.protonsdk.model.ChainAccount
import timber.log.Timber

/**
 * Created by m.imran
 * Senior Software Engineer at
 * BhimSoft on 10/09/2020.
 */
class ProtonAccountFragment : BaseFragment() {
    private var mBinding: FragmentRecyclerViewBinding? = null
    private var mAccountsAdapter: AccountsAdapter? = null

    override fun getLayout(): Int {
        return R.layout.fragment_recycler_view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHomeAsUp(true)
        setTitle(R.string.account_information)
        mBinding = FragmentRecyclerViewBinding.bind(view)
        mBinding!!.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        mAccountsAdapter = AccountsAdapter(
            requireContext(),
            mBinding!!.noData,
            object : ItemClickListener<Account> {
                override fun onItemClick(t: Account, position: Int) {
                    setActiveAccount(t)
                }
            })
        mBinding!!.recyclerView.adapter = mAccountsAdapter
        getData()
    }

    private fun setActiveAccount(account: Account) {
        val builder = ActiveAccount.Builder(account.accountName)
        builder.run {
            publicKey = PublicKey
        }
        enqueue(mProton!!.setActiveAccount(builder.create()), object : Callback<ChainAccount>() {
            override fun onSuccess(response: ChainAccount?) {
                Toast.makeText(
                    requireContext(),
                    "Account ${response?.account?.accountName} is now Active",
                    Toast.LENGTH_SHORT
                ).show()
                Timber.d(">>>>ActiveAccount $response")
            }

            override fun onError(throwable: Throwable) {
                Timber.e(">>>>ActiveAccount $throwable")
            }

        })
    }

    private fun getData() {
        val chainProvider: LiveData<Resource<List<Account>>> =
            mProton!!.findAccountsForPublicKey(PublicKey)
        enqueue(chainProvider, object : Callback<List<Account>>() {

            override fun onLoading() {
                mBinding!!.progressBar.visibility = View.VISIBLE
            }

            override fun onSuccess(response: List<Account>?) {
                Timber.d(">>>>>onSuccess ${response?.get(0)}")
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