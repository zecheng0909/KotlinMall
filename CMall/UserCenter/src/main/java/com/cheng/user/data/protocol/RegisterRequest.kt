package com.cheng.user.data.protocol

/**
 * User: wangzecheng (514118702@qq.com)
 * Date: 2018-01-04
 * Time: 16:02
 * Describe:
 */

data class RegisterRequest(val mobile: String, val pwd: String, val verifyCode: String)