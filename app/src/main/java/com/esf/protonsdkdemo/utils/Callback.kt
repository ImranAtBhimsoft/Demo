package com.esf.protonsdkdemo.utils

/**
 * Created by m.imran
 * Senior Software Engineer at
 * BhimSoft on 09/09/2020.
 */
abstract class Callback<T> {
    abstract fun onSuccess(response: T?)

    abstract fun onError(throwable: Throwable)

    open fun onLoading() {

    }
}