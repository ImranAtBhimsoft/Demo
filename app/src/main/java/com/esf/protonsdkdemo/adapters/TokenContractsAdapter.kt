package com.esf.protonsdkdemo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.esf.protonsdkdemo.databinding.RowTokenContractBinding
import com.metallicus.protonsdk.model.TokenContract

/**
 * Created by m.imran
 * Senior Software Engineer at
 * BhimSoft on 09/09/2020.
 */
class TokenContractsAdapter(private var context: Context, private var noData: View) :
    RecyclerView.Adapter<TokenContractsAdapter.Holder>() {
    private var mData: MutableList<TokenContract> = ArrayList()

    init {
        registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                noData.visibility = if (mData.size > 0) View.GONE else View.VISIBLE
            }
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            RowTokenContractBinding.inflate(LayoutInflater.from(context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.binding.data = mData[position]
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    fun setData(data: List<TokenContract>) {
        mData.addAll(data)
        notifyDataSetChanged()
    }

    data class Holder(var binding: RowTokenContractBinding) :
        RecyclerView.ViewHolder(binding.root)
}