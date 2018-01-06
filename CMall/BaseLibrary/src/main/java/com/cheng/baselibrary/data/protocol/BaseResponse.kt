package com.cheng.baselibrary.data.protocol

/**
 * User: Cheng
 * Date: 2018-01-04
 * Time: 15:56
 * Describe: 响应数据的基类
 */

class BaseResponse<out T>(val status: Int, val message: String, val data: T)