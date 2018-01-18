package com.cheng.goods.ui.activity

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder
import cn.bingoogolapple.refreshlayout.BGARefreshLayout
import com.cheng.baselibrary.ext.srartLoading
import com.cheng.baselibrary.ui.activity.BaseMvpActivity
import com.cheng.goods.R
import com.cheng.goods.common.GoodsConstant.Companion.KEY_CATEGORY_ID
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

class GoodsListActivity : BaseMvpActivity<GoodsListPresenter>(),
        GoodsListView, BGARefreshLayout.BGARefreshLayoutDelegate {

    private lateinit var goodsListAdapter: GoodsListAdapter

    private var currentPageNo = 1
    private var maxPageNo = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods)

        initView()
        loadData()
        initRefreshLayout()
    }

    /**
     * 初始化视图
     */
    private fun initView() {
        goodsListRv.layoutManager = GridLayoutManager(this, 2)
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
        mPresenter.getGoodsList(categoryId = intent.getIntExtra(KEY_CATEGORY_ID, 1), pageNo = currentPageNo)
    }

    /**
     * 初始化下拉刷新和上拉加载更多控件
     */
    private fun initRefreshLayout() {
        refreshLayout.setDelegate(this)
        val refreshViewHolder = BGANormalRefreshViewHolder(this, true)
        // 设置整个加载更多控件的背景颜色资源 id
        refreshViewHolder.setLoadMoreBackgroundColorRes(R.color.common_bg)
        // 设置下拉刷新控件的背景颜色资源 id
        refreshViewHolder.setRefreshViewBackgroundColorRes(R.color.common_bg)
        refreshLayout.setRefreshViewHolder(refreshViewHolder)
    }

    /**
     * 获得商品列表成功的回调
     */
    override fun getGoodsListResult(result: MutableList<GoodsInfo>?) {
        refreshLayout.endLoadingMore()
        refreshLayout.endRefreshing()
        if (result != null && result.size != 0) {
            if (currentPageNo != 1) {
                goodsListAdapter.dataList.addAll(result)
                goodsListAdapter.notifyDataSetChanged()
            } else {
                goodsListAdapter.setData(result)
            }
            currentPageNo++
            maxPageNo = result[0].maxPage
            multiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            multiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }

    }

    /**
     * 上拉加载更多的回调
     */
    override fun onBGARefreshLayoutBeginLoadingMore(refreshLayout: BGARefreshLayout?): Boolean {
        return if (currentPageNo < maxPageNo) {
            loadData()
            true
        } else {
            false
        }

    }

    /**
     * 下拉刷新的回调
     */
    override fun onBGARefreshLayoutBeginRefreshing(refreshLayout: BGARefreshLayout?) {
        currentPageNo = 1
        loadData()
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