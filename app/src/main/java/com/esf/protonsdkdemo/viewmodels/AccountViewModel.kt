package com.esf.protonsdkdemo.viewmodels

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.metallicus.protonsdk.model.Account

/**
 * Created by m.imran
 * Senior Software Engineer at
 * BhimSoft on 08/09/2020.
 */
data class AccountViewModel(val account: Account?) : BaseObservable() {

    @Bindable
    fun getName(): String {
        return account?.accountName ?: ""
    }

    @Bindable
    fun getHeadBlockNum(): Int {
        return account?.headBlockNum ?: 0
    }

    @Bindable
    fun getHeadBlockTime(): String {
        return account?.headBlockTime ?: ""
    }

    @Bindable
    fun isPrivileged(): Boolean {
        return account?.privileged ?: false
    }

    @Bindable
    fun getLastCodeUpdate(): String {
        return account?.lastCodeUpdate ?: ""
    }

    @Bindable
    fun getCreated(): String {
        return account?.created ?: ""
    }

    @Bindable
    fun getCoreLiquidBalance(): String {
        return account?.coreLiquidBalance ?: ""
    }

    @Bindable
    fun getRamQuota(): Int {
        return account?.ramQuota ?: 0
    }

    @Bindable
    fun getNetWeight(): Long {
        return account?.netWeight ?: 0
    }

    @Bindable
    fun getCpuWeight(): Long {
        return account?.cpuWeight ?: 0
    }

    @Bindable
    fun getRamUsage(): Long {
        return account?.ramUsage ?: 0
    }

}