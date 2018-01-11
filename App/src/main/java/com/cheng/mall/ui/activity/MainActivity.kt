package com.cheng.mall.ui.activity

import android.os.Bundle
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.cheng.baselibrary.ui.activity.BaseActivity
import com.cheng.baselibrary.ui.fragment.BaseFragment
import com.cheng.mall.R
import com.cheng.mall.ui.fragment.HomeFragment
import com.cheng.mall.ui.fragment.MeFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : BaseActivity() {

    private val fragmentStack: Stack<BaseFragment> by lazy { Stack<BaseFragment>() }

    private val homeFragment: HomeFragment by lazy { HomeFragment() }
    private val categoryFragment: HomeFragment by lazy { HomeFragment() }
    private val cartFragment: HomeFragment by lazy { HomeFragment() }
    private val msgFragment: HomeFragment by lazy { HomeFragment() }
    private val meFragment: MeFragment by lazy { MeFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavBar.checkCartBadge(10)
        bottomNavBar.checkMsgBadge(false)

        initFragment()
        initBottomNav()
        changeFragment(0)
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

    private fun changeFragment(position: Int) {
        val manager = supportFragmentManager.beginTransaction()
        fragmentStack.forEach {
            manager.hide(it)
        }

        manager.show(fragmentStack[position])
        manager.commit()
    }
}
