package com.cheng.user.data.protocol

/**
 * User: Cheng
 * Date: 2018-01-08
 * Time: 22:035
 * Describe: 登录请求参数的数据类
 */

data class LoginRequest(val mobile: String, val pwd: String, val pushId: String = "")