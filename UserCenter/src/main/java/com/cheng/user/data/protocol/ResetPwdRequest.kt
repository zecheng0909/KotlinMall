package com.cheng.user.data.protocol

/**
 * User: Cheng
 * Date: 2018-01-09
 * Time: 12:58
 * Describe: 重置密码请求参数的数据类
 */

data class ResetPwdRequest(val mobile: String, val pwd: String)