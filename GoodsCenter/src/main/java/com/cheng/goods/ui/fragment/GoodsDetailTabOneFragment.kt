package com.cheng.goods.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import com.cheng.baselibrary.ext.onClick
import com.cheng.baselibrary.ui.activity.BaseActivity
import com.cheng.baselibrary.ui.fragment.BaseMvpFragment
import com.cheng.baselibrary.widgets.BannerImageLoader
import com.cheng.goods.R
import com.cheng.goods.common.GoodsConstant
import com.cheng.goods.event.AddCartEvent
import com.cheng.goods.event.GoodsDetailImageEvent
import com.cheng.goods.event.GoodsSkuChangedEvent
import com.cheng.goods.event.UpdateCartCountEvent
import com.cheng.goods.injection.component.DaggerGoodsComponent
import com.cheng.goods.injection.module.CartModule
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
import org.jetbrains.anko.support.v4.toast

/**
 * User: Cheng
 * Date: 2018-01-20
 * Time: 11:31
 * Describe: 商品详情页
 */

class GoodsDetailTabOneFragment : BaseMvpFragment<GoodsDetailPresenter>(), GoodsDetailView {

    //商品详情数据
    private var goodsInfo: GoodsInfo? = null

    private lateinit var skuPopView: GoodsSkuPopView

    //SKU弹窗出场动画
    private lateinit var animationStart: Animation
    //SKU弹窗退场动画
    private lateinit var animationEnd: Animation

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_goods_detail_tab_one, null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initAnim()
        initSKUPop()
        loadData()
        initObserve()

    }

    /**
     * 加载商品详情
     */
    private fun loadData() {
        mPresenter.getGoodsDetail(activity.intent.getIntExtra(GoodsConstant.KEY_GOODS_ID, -1))
    }

    /**
     * 添加购物车
     */
    private fun addCart() {
        goodsInfo?.let {
            mPresenter.addCart(goodsId = it.id,
                    goodsDesc = it.goodsDesc,
                    goodsIcon = it.goodsDefaultIcon,
                    goodsPrice = it.goodsDefaultPrice,
                    goodsCount = skuPopView.getSelectCount(),
                    goodsSku = skuPopView.getSelectSku())
        }
    }

    /**
     * 初始化缩放动画
     */
    private fun initAnim() {
        animationStart = ScaleAnimation(
                1f, 0.95f, 1f, 0.95f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        animationStart.duration = 500
        animationStart.fillAfter = true

        animationEnd = ScaleAnimation(
                0.95f, 1f, 0.95f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        animationEnd.duration = 500
        animationEnd.fillAfter = true
    }

    /**
     * sku发生改变时,接收事件
     * 添加购物车接受事件
     */
    @SuppressLint("SetTextI18n")
    private fun initObserve() {
        Bus.observe<GoodsSkuChangedEvent>()
                .subscribe {
                    skuSelectedTv.text = "${skuPopView.getSelectSku()}${GoodsConstant.SKU_SEPARATOR}" +
                            "${skuPopView.getSelectCount()}个"
                }
                .registerInBus(this)

        Bus.observe<AddCartEvent>()
                .subscribe {
                    addCart()
                }
                .registerInBus(this)
    }

    /**
     * 初始化SKU弹窗
     */
    private fun initSKUPop() {
        skuPopView = GoodsSkuPopView(activity)

        skuPopView.setOnDismissListener {

            (activity as BaseActivity).contentView.startAnimation(animationEnd)

        }

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
     * 初始化广告轮播以及其他视图
     */
    private fun initView() {
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

        skuView.onClick {
            skuPopView.showAtLocation((activity as BaseActivity).contentView,
                    Gravity.BOTTOM and Gravity.HORIZONTAL_GRAVITY_MASK,
                    0, 0)

            (activity as BaseActivity).contentView.startAnimation(animationStart)

        }


    }

    /**
     * 初始化依赖对象
     */
    override fun injectComponent() {
        DaggerGoodsComponent.builder()
                .activityComponent(activityComponent)
                .goodsModule(GoodsModule())
                .cartModule(CartModule())
                .build()
                .inject(this)

        mPresenter.mView = this
    }

    /**
     * 获取商品详情的回调
     */
    override fun getGoodsDetailResult(result: GoodsInfo) {

        goodsInfo = result

        goodsDetailBanner.setImages(result.goodsBanner.split(","))
        goodsDetailBanner.start()

        goodsDescTv.text = result.goodsDesc
        goodsPriceTv.text = YuanFenConverter.changeF2YWithUnit(result.goodsDefaultPrice)
        skuSelectedTv.text = result.goodsDefaultSku

        Bus.send(GoodsDetailImageEvent(result.goodsDetailOne, result.goodsDetailTwo))

        loadPopData(result)

    }

    /**
     * 添加购物车成功的回调
     */
    override fun addCartResult(result: Int) {
        Bus.send(UpdateCartCountEvent())
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }

}