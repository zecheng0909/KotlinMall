package com.cheng.user.data.protocol

/**
 * User: Cheng
 * Date: 2018-01-04
 * Time: 16:02
 * Describe: 网络请求参数的数据类
 */

data class RegisterRequest(val mobile: String, val pwd: String, val verifyCode: String = "123456")