package com.cheng.baselibrary.presenter.view

/**
 * User: Cheng
 * Date: 2018-01-02
 * Time: 23:40
 * Describe:
 */

interface BaseView {
    fun showLoading()

    fun hideLoading()

    fun onError(errorText:String)
}