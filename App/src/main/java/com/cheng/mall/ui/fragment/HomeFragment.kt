package com.cheng.mall.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cheng.baselibrary.ui.fragment.BaseFragment
import com.cheng.baselibrary.widgets.BannerImageLoader
import com.cheng.mall.R
import com.kotlin.mall.common.HOME_BANNER_FOUR
import com.kotlin.mall.common.HOME_BANNER_ONE
import com.kotlin.mall.common.HOME_BANNER_THREE
import com.kotlin.mall.common.HOME_BANNER_TWO
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*


/**
 * User: Cheng
 * Date: 2018-01-10
 * Time: 16:45
 * Describe:
 */

class HomeFragment : BaseFragment() {

    private lateinit var homeBanner: Banner

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater?.inflate(R.layout.fragment_home, null)

        homeBanner = view!!.homeBanner

        initBanner()

        return view
    }

    private fun initBanner() {
        //设置banner样式
        homeBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
        //设置图片加载器
        homeBanner.setImageLoader(BannerImageLoader())
        //设置图片集合
        homeBanner.setImages(listOf(HOME_BANNER_ONE, HOME_BANNER_TWO,
                HOME_BANNER_THREE, HOME_BANNER_FOUR))
        //设置banner动画效果
        homeBanner.setBannerAnimation(Transformer.DepthPage)
        //设置标题集合（当banner样式有显示title时）
        //homeBanner.setBannerTitles(titles)
        //设置自动轮播，默认为true
        //homeBanner.isAutoPlay(true)
        //设置轮播时间
        homeBanner.setDelayTime(2000)
        //设置指示器位置（当banner模式中有指示器时）
        homeBanner.setIndicatorGravity(BannerConfig.RIGHT)
        //banner设置方法全部调用完毕时最后调用
        homeBanner.start()
    }

}