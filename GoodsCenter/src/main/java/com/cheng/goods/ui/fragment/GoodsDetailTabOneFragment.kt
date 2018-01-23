package com.cheng.goods.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cheng.baselibrary.ext.loadUrl
import com.cheng.baselibrary.ext.onClick
import com.cheng.baselibrary.ui.activity.BaseActivity
import com.cheng.baselibrary.ui.fragment.BaseMvpFragment
import com.cheng.baselibrary.widgets.BannerImageLoader
import com.cheng.goods.R
import com.cheng.goods.common.GoodsConstant
import com.cheng.goods.event.GoodsDetailImageEvent
import com.cheng.goods.event.GoodsSkuChangedEvent
import com.cheng.goods.injection.component.DaggerGoodsComponent
import com.cheng.goods.injection.module.GoodsModule
import com.cheng.goods.presenter.GoodsDetailPresenter
import com.cheng.goods.presenter.view.GoodsDetailView
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.kotlin.base.utils.YuanFenConverter
import com.kotlin.goods.data.protocol.GoodsInfo
import com.kotlin.goods.widget.GoodsSkuPopView
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_goods_detail_tab_one.*
import kotlinx.android.synthetic.main.fragment_goods_detail_tab_two.*

/**
 * User: Cheng
 * Date: 2018-01-20
 * Time: 11:31
 * Describe: 商品详情页
 */

class GoodsDetailTabOneFragment : BaseMvpFragment<GoodsDetailPresenter>(), GoodsDetailView {

    private lateinit var skuPopView: GoodsSkuPopView

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_goods_detail_tab_one, null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBanner()
        initSKUPop()
        initObserve()
        loadData()

        skuView.onClick {
            skuPopView.showAtLocation((activity as BaseActivity).contentView,
                    Gravity.BOTTOM and Gravity.HORIZONTAL_GRAVITY_MASK,
                    0, 0)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initObserve() {
        Bus.observe<GoodsSkuChangedEvent>()
                .subscribe {
                    skuSelectedTv.text = skuPopView.getSelectSku() + GoodsConstant.SKU_SEPARATOR +
                            skuPopView.getSelectCount() + "个"
                }
                .registerInBus(this)
    }

    /**
     * 初始化SKU弹窗
     */
    private fun initSKUPop() {
        skuPopView = GoodsSkuPopView(activity)
    }

    private fun loadData() {
        mPresenter.getGoodsDetail(activity.intent.getIntExtra(GoodsConstant.KEY_GOODS_ID, -1))
    }

    /**
     * 加载SKU数据
     */
    private fun loadPopData(result: GoodsInfo) {
        skuPopView.setGoodsIcon(result.goodsDefaultIcon)
        skuPopView.setGoodsCode(result.goodsCode)
        skuPopView.setGoodsPrice(result.goodsDefaultPrice)
        skuPopView.setSkuData(result.goodsSku)
    }

    /**
     * 初始化广告轮播
     */
    private fun initBanner() {
        //设置banner样式
        goodsDetailBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
        //设置图片加载器
        goodsDetailBanner.setImageLoader(BannerImageLoader())
        //设置banner动画效果
        goodsDetailBanner.setBannerAnimation(Transformer.DepthPage)
        //设置轮播时间
        goodsDetailBanner.setDelayTime(2000)
        //设置指示器位置（当banner模式中有指示器时）
        goodsDetailBanner.setIndicatorGravity(BannerConfig.RIGHT)
    }

    /**
     * 初始化依赖对象
     */
    override fun injectComponent() {
        DaggerGoodsComponent.builder()
                .activityComponent(activityComponent)
                .goodsModule(GoodsModule())
                .build()
                .inject(this)

        mPresenter.mView = this
    }

    /**
     * 获取商品详情的回调
     */
    override fun getGoodsDetailResult(result: GoodsInfo) {
        goodsDetailBanner.setImages(result.goodsBanner.split(","))
        goodsDetailBanner.start()

        goodsDescTv.text = result.goodsDesc
        goodsPriceTv.text = YuanFenConverter.changeF2YWithUnit(result.goodsDefaultPrice)
        skuSelectedTv.text = result.goodsDefaultSku

        loadPopData(result)

        Bus.send(GoodsDetailImageEvent(result.goodsDetailOne, result.goodsDetailTwo))
    }

}