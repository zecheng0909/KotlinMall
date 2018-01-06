package com.cheng.baselibrary.ui.activity

import android.os.Bundle
import com.cheng.baselibrary.common.AppManager
import com.trello.rxlifecycle.components.support.RxAppCompatActivity

/**
 * User: Cheng
 * Date: 2018-01-02
 * Time: 23:41
 * Describe: 抽取的Activity基类
 */

open class BaseActivity : RxAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppManager.appManager.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        AppManager.appManager.finishActivity(this)
    }

}