package com.cheng.mall.ui.activity

import android.os.Bundle
import com.cheng.baselibrary.ui.activity.BaseActivity
import com.cheng.mall.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavBar.checkCartBadge(10)
        bottomNavBar.checkMsgBadge(false)


//        Observable.timer(10, TimeUnit.SECONDS)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe {
//                    bottomNavBar.checkCartBadge(0)
//                }
//
//        Observable.timer(8, TimeUnit.SECONDS)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe {
//                    bottomNavBar.checkMsgBadge(true)
//                }
    }
}
