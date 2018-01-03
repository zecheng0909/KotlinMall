package com.cheng.baselibrary.presenter.view

/**
 * User: wangzecheng (514118702@qq.com)
 * Date: 2018-01-02
 * Time: 23:40
 * Describe:
 */

interface BaseView {
    fun showLoading()

    fun hideLoading()

    fun onError()
}