package com.cheng.user.data.protocol

/**
 * User: Cheng
 * Date: 2018-01-09
 * Time: 12:05
 * Describe: 找回密码请求参数的数据类
 */

data class ForgetPwdRequest(val mobile: String, val verifyCode: String = "123456")