package com.cheng.user.data.protocol

import com.cheng.baselibrary.ext.PoKo

/**
 * User: Cheng
 * Date: 2018-01-08
 * Time: 22:49
 * Describe: User的数据类
 */

@PoKo
data class UserInfo(val id: String,
                    val userIcon: String,
                    val userName: String,
                    val userGender: String,
                    val userMobile: String,
                    val userSign: String,
                    val pushId: String)