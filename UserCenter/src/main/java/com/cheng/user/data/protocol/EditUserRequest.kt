package com.kotlin.user.data.protocol

/**
 * User: Cheng
 * Date: 2018-01-09
 * Time: 22:26
 * Describe: 修改用户资料请求体
 */
data class EditUserRequest(val userIcon: String, val userName: String, val gender: String, val sign: String)
