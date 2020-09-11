package com.esf.protonsdkdemo.utils

/**
 * Created by m.imran
 * Senior Software Engineer at
 * BhimSoft on 10/09/2020.
 */
interface ItemClickListener<T> {
    fun onItemClick(t: T, position: Int)
}