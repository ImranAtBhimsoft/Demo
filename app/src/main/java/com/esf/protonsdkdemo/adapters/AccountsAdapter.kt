package com.esf.protonsdkdemo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.esf.protonsdkdemo.databinding.RowProtonAccountBinding
import com.esf.protonsdkdemo.utils.ItemClickListener
import com.esf.protonsdkdemo.viewmodels.AccountViewModel
import com.metallicus.protonsdk.model.Account

/**
 * Created by m.imran
 * Senior Software Engineer at
 * BhimSoft on 09/09/2020.
 */
class AccountsAdapter(
    private val context: Context,
    private val noData: View,
    private val clickListener: ItemClickListener<Account>
) :
    RecyclerView.Adapter<AccountsAdapter.Holder>() {
    private val mData: MutableList<Account> = ArrayList()
    private val clickViewListener: View.OnClickListener = View.OnClickListener { v ->
        val index: Int = v.tag as Int
        clickListener.onItemClick(mData[index], index)
    }

    init {
        registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                noData.visibility = if (mData.size > 0) View.GONE else View.VISIBLE
            }
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            RowProtonAccountBinding.inflate(LayoutInflater.from(context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.binding.viewModel = AccountViewModel(mData[position])

        holder.binding.active.tag = position
        holder.binding.active.setOnClickListener(clickViewListener)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    fun setData(data: List<Account>) {
        mData.addAll(data)
        notifyDataSetChanged()
    }

    data class Holder(var binding: RowProtonAccountBinding) :
        RecyclerView.ViewHolder(binding.root)
}