package com.cheng.provider.router

/**
 * User: Cheng
 * Date: 2018-01-25
 * Time: 09:48
 * Describe:
 */

object RouterPath {

    /**
     * 登录页
     */
    class UserCenter {
        companion object {
            const val PATH_LOGIN = "/userCenter/login"
        }
    }

    /**
     * 订单确认
     */
    class OrderCenter {
        companion object {
            const val PATH_ORDER_CONFIRM = "/orderCenter/confirm"
        }
    }

}