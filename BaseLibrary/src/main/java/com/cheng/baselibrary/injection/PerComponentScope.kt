package com.cheng.baselibrary.injection

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy.RUNTIME
import javax.inject.Scope

/**
 * User: Cheng
 * Date: 2018-01-05
 * Time: 19:46
 * Describe: 定义业务层级别的 Scope
 */

@Scope
//@Retention(RUNTIME)
annotation class PerComponentScope