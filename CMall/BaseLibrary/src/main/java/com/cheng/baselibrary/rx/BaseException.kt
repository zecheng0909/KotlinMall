package com.cheng.baselibrary.rx

/**
 * User: wangzecheng (514118702@qq.com)
 * Date: 2018-01-04
 * Time: 21:44
 * Describe: 自定义异常,当网络访问出现问题时,由Observable抛出此异常
 */

class BaseException(val status: Int, val msg: String) : Throwable()