package com.cheng.provider.common

import com.cheng.baselibrary.common.BaseConstant
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