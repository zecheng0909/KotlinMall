package com.cheng.mall.ui.activity

import android.os.Bundle
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.cheng.baselibrary.common.AppManager
import com.cheng.baselibrary.ui.activity.BaseActivity
import com.cheng.baselibrary.ui.fragment.BaseFragment
import com.cheng.goods.common.GoodsConstant
import com.cheng.goods.event.UpdateCartCountEvent
import com.cheng.goods.ui.fragment.CategoryFragment
import com.cheng.mall.R
import com.cheng.mall.ui.fragment.HomeFragment
import com.cheng.mall.ui.fragment.MeFragment
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.kotlin.base.utils.AppPrefsUtils
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import java.util.*

class MainActivity : BaseActivity() {

    var time: Long = 0

    private val fragmentStack: Stack<BaseFragment> by lazy { Stack<BaseFragment>() }

    private val homeFragment: HomeFragment by lazy { HomeFragment() }
    private val categoryFragment: CategoryFragment by lazy { CategoryFragment() }
    private val cartFragment: HomeFragment by lazy { HomeFragment() }
    private val msgFragment: HomeFragment by lazy { HomeFragment() }
    private val meFragment: MeFragment by lazy { MeFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //测试
        bottomNavBar.checkMsgBadge(true)

        initFragment()
        initBottomNav()
        changeFragment(0)
        loadCartCount()
        initObserve()
    }

    /**
     *  购物车数量变化事件
     */
    private fun initObserve() {
        Bus.observe<UpdateCartCountEvent>()
                .subscribe {
                    loadCartCount()
                }
                .registerInBus(this)
    }

    /**
     * 加载购物车角标数字
     */
    private fun loadCartCount() {
        bottomNavBar.checkCartBadge(AppPrefsUtils.getInt(GoodsConstant.SP_CART_SIZE))
    }

    /**
     * 初始化Fragment
     */
    private fun initFragment() {
        val manager = supportFragmentManager.beginTransaction()
        manager.add(R.id.contentFl, homeFragment)
        manager.add(R.id.contentFl, categoryFragment)
        manager.add(R.id.contentFl, cartFragment)
        manager.add(R.id.contentFl, msgFragment)
        manager.add(R.id.contentFl, meFragment)
        manager.commit()

        fragmentStack.add(homeFragment)
        fragmentStack.add(categoryFragment)
        fragmentStack.add(cartFragment)
        fragmentStack.add(msgFragment)
        fragmentStack.add(meFragment)
    }

    /**
     * 初始化底部导航
     */
    private fun initBottomNav() {
        bottomNavBar.setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener {
            override fun onTabReselected(position: Int) {

            }

            override fun onTabUnselected(position: Int) {

            }

            override fun onTabSelected(position: Int) {
                changeFragment(position)
            }

        })
    }

    /**
     * 切换Fragment
     */
    private fun changeFragment(position: Int) {
        val manager = supportFragmentManager.beginTransaction()
        fragmentStack.forEach {
            manager.hide(it)
        }

        manager.show(fragmentStack[position])
        manager.commit()
    }

    /**
     * 重写Back键事件
     */
    override fun onBackPressed() {
        doubleBackExit()
    }

    /**
     * 双击back退出app
     */
    private fun doubleBackExit() {
        val currentTime = System.currentTimeMillis()
        if (currentTime - time > 2000) {
            toast("再次点击返回键退出应用")
            time = currentTime
        } else {
            AppManager.appManager.exitApp(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }
}
