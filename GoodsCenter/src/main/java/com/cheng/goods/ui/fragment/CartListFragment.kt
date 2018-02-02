package com.cheng.goods.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.launcher.ARouter
import com.cheng.baselibrary.ext.setVisible
import com.cheng.baselibrary.ext.startLoading
import com.cheng.baselibrary.ui.fragment.BaseMvpFragment
import com.cheng.goods.R
import com.cheng.goods.common.GoodsConstant
import com.cheng.goods.event.CartCheckedEvent
import com.cheng.goods.event.UpDateTotalPriceEvent
import com.cheng.goods.event.UpdateCartCountEvent
import com.cheng.goods.injection.component.DaggerCartComponent
import com.cheng.goods.injection.module.CartModule
import com.cheng.goods.presenter.CartListPresenter
import com.cheng.goods.presenter.view.CartListView
import com.cheng.provider.router.RouterPath
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.kennyc.view.MultiStateView
import com.kotlin.base.utils.AppPrefsUtils
import com.kotlin.base.utils.YuanFenConverter
import com.kotlin.goods.data.protocol.CartGoodsInfo
import com.kotlin.goods.ui.adapter.CartGoodsAdapter
import com.kotlin.provider.common.ProviderConstant
import kotlinx.android.synthetic.main.fragment_cart.*
import org.jetbrains.anko.support.v4.toast

/**
 * User: Cheng
 * Date: 2018-01-27
 * Time: 9:33
 * Describe: 购物车列表
 */

class CartListFragment : BaseMvpFragment<CartListPresenter>(), CartListView, View.OnClickListener {

    private var totalPrice = 0L

    private lateinit var cartGoodsAdapter: CartGoodsAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_cart, null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserve()
    }

    override fun onStart() {
        super.onStart()
        loadData()
        allCheckedCb.isChecked = false
    }

    /**
     * 初始化视图
     */
    private fun initView() {
        cartGoodsRv.layoutManager = LinearLayoutManager(activity)
        cartGoodsAdapter = CartGoodsAdapter(activity)
        cartGoodsRv.adapter = cartGoodsAdapter

        allCheckedCb.setOnClickListener(this)
        mHeaderBar.getRightView().setOnClickListener(this)
        deleteBtn.setOnClickListener(this)
        settleAccountsBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            allCheckedCb -> {
                checkAll()
            }

            mHeaderBar.getRightView() -> {
                refreshEditStatus()
            }

            deleteBtn -> {
                deleteCartList()
            }

            settleAccountsBtn -> {
                submitCartList()
            }
        }
    }

    /**
     * 全选
     */
    private fun checkAll() {
        cartGoodsAdapter.dataList.forEach {
            it.isSelected = allCheckedCb.isChecked
        }
        cartGoodsAdapter.notifyDataSetChanged()
        upDateTotalPrice()
    }

    /**
     * 删除购物车商品
     */
    private fun deleteCartList() {
        val deleteList = cartGoodsAdapter.dataList
                .filter { it.isSelected }
                .map { it.id }
        if (deleteList.isEmpty()) {
            toast("请选择需要删除的商品")
        } else {
            mPresenter.deleteCartList(deleteList)
        }
    }

    /**
     * 提交购物车商品
     */
    private fun submitCartList() {
        val submitList = cartGoodsAdapter.dataList
                .filter { it.isSelected }
                .map { it }
        if (submitList.isEmpty()) {
            toast("请选择需要提交的商品")
        } else {
            mPresenter.submitCart(submitList, totalPrice)
        }
    }

    /**
     * 改变编辑状态
     */
    private fun refreshEditStatus() {
        val isEditStatus = mHeaderBar.getRightText() == getString(R.string.common_edit)

        totalPriceTv.setVisible(isEditStatus.not())
        settleAccountsBtn.setVisible(isEditStatus.not())
        deleteBtn.setVisible(isEditStatus)

        mHeaderBar.getRightView().text = if (isEditStatus) {
            getString(R.string.common_complete)
        } else {
            getString(R.string.common_edit)
        }
    }

    /**
     * 初始化接收商品全选事件
     */
    private fun initObserve() {
        Bus.observe<CartCheckedEvent>()
                .subscribe {
                    allCheckedCb.isChecked = it.isAllSelected
                }
                .registerInBus(this)

        Bus.observe<UpDateTotalPriceEvent>()
                .subscribe {
                    upDateTotalPrice()
                }
                .registerInBus(this)
    }

    /**
     * 更新总价显示数值
     */
    private fun upDateTotalPrice() {
        totalPrice = cartGoodsAdapter.dataList
                .filter { it.isSelected }
                .map { it.goodsPrice * it.goodsCount }
                .sum()
        totalPriceTv.text = "合计:${YuanFenConverter.changeF2Y(totalPrice)}"
    }

    /**
     * 获得购物车列表的回调
     */
    override fun onGetCartListResult(result: MutableList<CartGoodsInfo>?) {
        if (result != null && result.size != 0) {
            cartGoodsAdapter.setData(result)
            multiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
            mHeaderBar.getRightView().setVisible(true)
        } else {
            multiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
            mHeaderBar.getRightView().setVisible(false)
        }

        AppPrefsUtils.putInt(GoodsConstant.SP_CART_SIZE, result?.size ?: 0)
        Bus.send(UpdateCartCountEvent())
    }

    /**
     * 删除购物车商品的回调
     */
    override fun onDeleteCartListResult(result: Boolean) {
        loadData()
        refreshEditStatus()
    }

    /**
     * 提交购物车商品的回调
     */
    override fun onSubmitCartListResult(result: Int) {
        ARouter.getInstance()
                .build(RouterPath.OrderCenter.PATH_ORDER_CONFIRM)
                .withInt(ProviderConstant.KEY_ORDER_ID, result)
                .navigation()
    }

    /**
     * 获取分类数据
     */
    private fun loadData() {
        multiStateView.startLoading()
        mPresenter.getCartList()
    }

    /**
     * 初始化依赖对象
     */
    override fun injectComponent() {
        DaggerCartComponent.builder()
                .activityComponent(activityComponent)
                .cartModule(CartModule())
                .build()
                .inject(this)
        mPresenter.mView = this
    }

    /**
     * 设置左上角back按钮是否显示
     */
    fun setBackVisible(isVisible: Boolean) {
        mHeaderBar.getLeftView().setVisible(isVisible)
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }

}