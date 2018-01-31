package com.cheng.baselibrary.common

/**
 * User: Cheng
 * Date: 2018-01-03
 * Time: 20:34
 * Describe:
 */

class BaseConstant {

    companion object {

        /**
         * BaseUrl
         */
//        const val SERVER_ADDRESS = "http://192.168.199.212:8080/CMall/"
//        const val SERVER_ADDRESS = "http://192.168.0.112:8080/CMall/"

        //本地
        const val SERVER_ADDRESS = "http://10.0.2.2:8080/CMall/"

        //外网
//        const val SERVER_ADDRESS = "http://120.79.59.193:8080/Kotlin_Server/"

        /**
         * 七牛服务地址
         */
        const val IMAGE_SERVER_ADDRESS = "http://osea2fxp7.bkt.clouddn.com/"

        /**
         * SP表名
         */
        const val TABLE_PREFS = "Kotlin_Mall"

        /**
         * 用户Token
         */
        const val KEY_SP_TOKEN = "TOKEN"
    }
}