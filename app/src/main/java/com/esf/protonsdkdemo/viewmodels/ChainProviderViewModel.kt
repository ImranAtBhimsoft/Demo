package com.esf.protonsdkdemo.viewmodels

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.metallicus.protonsdk.model.ChainProvider

/**
 * Created by m.imran
 * Senior Software Engineer at
 * BhimSoft on 08/09/2020.
 */
data class ChainProviderViewModel(val chainProvider: ChainProvider?) : BaseObservable() {

    @Bindable
    fun getName(): String {
        return chainProvider?.name ?: ""
    }

    @Bindable
    fun getDescription(): String {
        return chainProvider?.description ?: ""
    }

    @Bindable
    fun getChainUrl(): String {
        return chainProvider?.chainUrl ?: ""
    }

    @Bindable
    fun getExplorerName(): String {
        return chainProvider?.explorerName ?: ""
    }

    @Bindable
    fun getExplorerUrl(): String {
        return chainProvider?.explorerUrl ?: ""
    }

    @Bindable
    fun getIconUrl(): String {
        return chainProvider?.iconUrl ?: ""
    }

    @Bindable
    fun isTestnet(): Boolean {
        return chainProvider?.isTestnet ?: false
    }

    @Bindable
    fun getResourceTokenSymbol(): String {
        return chainProvider?.resourceTokenSymbol ?: ""
    }

    @Bindable
    fun getResourceTokenContract(): String {
        return chainProvider?.resourceTokenContract ?: ""
    }

    @Bindable
    fun getSystemTokenSymbol(): String {
        return chainProvider?.systemTokenSymbol ?: ""
    }

    @Bindable
    fun getSystemTokenContract(): String {
        return chainProvider?.systemTokenContract ?: ""
    }

}