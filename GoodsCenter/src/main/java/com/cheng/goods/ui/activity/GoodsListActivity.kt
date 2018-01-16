package com.cheng.goods.ui.activity

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.cheng.baselibrary.ext.srartLoading
import com.cheng.baselibrary.ui.activity.BaseMvpActivity
import com.cheng.goods.R
import com.cheng.goods.injection.component.DaggerGoodsCompoent
import com.cheng.goods.injection.module.GoodsModule
import com.cheng.goods.presenter.GoodsListPresenter
import com.cheng.goods.presenter.view.GoodsListView
import com.kennyc.view.MultiStateView
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.kotlin.goods.data.protocol.GoodsInfo
import com.kotlin.goods.ui.adapter.GoodsListAdapter
import kotlinx.android.synthetic.main.activity_goods.*
import org.jetbrains.anko.toast

/**
 * User: Cheng
 * Date: 2018-01-15
 * Time: 17:32
 * Describe: 商品列表页面
 */

class GoodsListActivity : BaseMvpActivity<GoodsListPresenter>(), GoodsListView {

    private lateinit var goodsListAdapter: GoodsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods)

        initView()
        loadData()
    }

    /**
     * 初始化视图
     */
    private fun initView() {
        goodsListRv.layoutManager = GridLayoutManager(this,2)
        goodsListAdapter = GoodsListAdapter(this)
        goodsListRv.adapter = goodsListAdapter

        goodsListAdapter
                .setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<GoodsInfo> {
                    override fun onItemClick(item: GoodsInfo, position: Int) {
                        toast("${item.goodsCode}")

                    }
                })
    }

    /**
     * 获取数据
     */
    private fun loadData() {
        multiStateView.srartLoading()
        mPresenter.getGoodsList(categoryId = intent.getIntExtra("categoryId", 1), pageNo = 1)
    }

    /**
     * 获得商品列表成功的回调
     */
    override fun getGoodsListResult(result: MutableList<GoodsInfo>?) {
        if (result != null && result.size != 0) {
            goodsListAdapter.setData(result)
            multiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            multiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }

    }

    /**
     * 初始化依赖对象
     */
    override fun injectComponent() {
        DaggerGoodsCompoent.builder()
                .activityComponent(activityComponent)
                .goodsModule(GoodsModule())
                .build()
                .inject(this)

        mPresenter.mView = this
    }

}