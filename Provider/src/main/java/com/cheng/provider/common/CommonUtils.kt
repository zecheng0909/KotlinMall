package com.cheng.provider.common

import com.alibaba.android.arouter.launcher.ARouter
import com.cheng.baselibrary.common.BaseConstant
import com.cheng.provider.router.RouterPath
import com.kotlin.base.utils.AppPrefsUtils

/**
 * User: Cheng
 * Date: 2018-01-11
 * Time: 16:13
 * Describe:
 */

/**
 * 判断是否登录
 */
fun isLogined(): Boolean {
    return AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN).isNotEmpty()
}

/**
 * 判断是否登录,接收一个函数,如果未登录则跳转至登录页
 */
fun afterLogin(action: () -> Unit) {
    if (isLogined()){
        action()
    }else{
        ARouter.getInstance().build(RouterPath.UserCenter.PATH_LOGIN).navigation()
    }

}