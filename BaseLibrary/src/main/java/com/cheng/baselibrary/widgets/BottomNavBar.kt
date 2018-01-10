package com.cheng.baselibrary.widgets

import android.content.Context
import android.util.AttributeSet
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.ashokvarma.bottomnavigation.ShapeBadgeItem
import com.ashokvarma.bottomnavigation.TextBadgeItem
import com.cheng.baselibrary.R

/**
 * User: Cheng
 * Date: 2018-01-10
 * Time: 11:19
 * Describe: BottomNavigationBar的封装
 */

class BottomNavBar @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BottomNavigationBar(context, attrs, defStyleAttr) {

    private val cartBadge: TextBadgeItem by lazy { TextBadgeItem() }
    private val msgBadge: ShapeBadgeItem by lazy {
        ShapeBadgeItem().setShape(ShapeBadgeItem.SHAPE_OVAL)
    }

    /**
     * 初始化底部导航的Item,角标,模式,背景颜色,背景风格
     * 设置了Item的图片,文字,颜色
     */
    init {
        //首页
        val homeItem = BottomNavigationItem(R.drawable.btn_nav_home_press, R.string.nav_bar_home)
                .setInactiveIconResource(R.drawable.btn_nav_home_normal)
                .setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal)

        //分类
        val categoryItem = BottomNavigationItem(R.drawable.btn_nav_category_press, R.string.nav_bar_category)
                .setInactiveIconResource(R.drawable.btn_nav_category_normal)
                .setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal)

        //购物车
        val cartItem = BottomNavigationItem(R.drawable.btn_nav_cart_press, R.string.nav_bar_cart)
                .setInactiveIconResource(R.drawable.btn_nav_cart_normal)
                .setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal)
                .setBadgeItem(cartBadge)

        //消息
        val msgItem = BottomNavigationItem(R.drawable.btn_nav_msg_press, R.string.nav_bar_msg)
                .setInactiveIconResource(R.drawable.btn_nav_msg_normal)
                .setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal)
                .setBadgeItem(msgBadge)

        //我的
        val userItem = BottomNavigationItem(R.drawable.btn_nav_user_press, R.string.nav_bar_user)
                .setInactiveIconResource(R.drawable.btn_nav_user_normal)
                .setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal)

        setMode(com.ashokvarma.bottomnavigation.BottomNavigationBar.MODE_FIXED)
        setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
        setBackgroundResource(R.color.common_white)

        addItem(homeItem)
                .addItem(categoryItem)
                .addItem(cartItem)
                .addItem(msgItem)
                .addItem(userItem)
                .setFirstSelectedPosition(0)
                .initialise()
    }

    /**
     * 购物车角标设置数值
     * @count 角标数值,为0则隐藏
     */
    fun checkCartBadge(count: Int) {
        if (count <= 0) {
            cartBadge.hide()
        } else {
            cartBadge.setText("$count")
            cartBadge.show()
        }
    }

    /**
     * 消息角标设置是否显示
     * @isVisibility 是否显示小红点
     */
    fun checkMsgBadge(isVisibility: Boolean) {
        if (isVisibility) {
            msgBadge.show()
        } else {
            msgBadge.hide()
        }
    }
}