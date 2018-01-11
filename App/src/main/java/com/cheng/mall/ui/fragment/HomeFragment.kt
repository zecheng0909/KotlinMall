package com.cheng.mall.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cheng.baselibrary.ui.fragment.BaseFragment
import com.cheng.baselibrary.widgets.BannerImageLoader
import com.cheng.mall.R
import com.cheng.mall.ui.adapter.HomeDiscountAdapter
import com.kotlin.mall.common.*
import com.kotlin.mall.ui.adapter.TopicAdapter
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_home.*
import me.crosswall.lib.coverflow.CoverFlow


/**
 * User: Cheng
 * Date: 2018-01-10
 * Time: 16:45
 * Describe: 首页Fragment
 */

class HomeFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_home, null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBanner()
        initNews()
        initDiscount()
        initTopic()
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

    private fun initNews() {
        newsFlipperView.setData(arrayOf("垂死病中惊坐起", "谈笑风生又一年"))
    }

    private fun initDiscount() {
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val adapter = HomeDiscountAdapter(activity)
        adapter.setData(mutableListOf(HOME_DISCOUNT_ONE, HOME_DISCOUNT_TWO, HOME_DISCOUNT_THREE,
                HOME_DISCOUNT_FOUR, HOME_DISCOUNT_FIVE))
        homeDiscountRv.layoutManager = layoutManager
        homeDiscountRv.adapter = adapter
    }

    /**
     * 初始化主题
     */
    private fun initTopic() {
        //话题
        topicPager.adapter = TopicAdapter(context, listOf(HOME_TOPIC_ONE, HOME_TOPIC_TWO, HOME_TOPIC_THREE, HOME_TOPIC_FOUR, HOME_TOPIC_FIVE))
        topicPager.currentItem = 1
        topicPager.offscreenPageLimit = 5

        CoverFlow.Builder().with(topicPager).scale(0.3f).pagerMargin(-30.0f).spaceSize(0.0f).build()
    }

}