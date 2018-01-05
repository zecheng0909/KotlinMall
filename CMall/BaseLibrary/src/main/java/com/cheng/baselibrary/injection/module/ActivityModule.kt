package com.cheng.baselibrary.injection.module

import android.app.Activity
import dagger.Module
import dagger.Provides

/**
 * User: wangzecheng (514118702@qq.com)
 * Date: 2018-01-05
 * Time: 19:43
 * Describe: Activity级别的Module
 */

@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    fun providesActivity(): Activity {
        return activity
    }

}