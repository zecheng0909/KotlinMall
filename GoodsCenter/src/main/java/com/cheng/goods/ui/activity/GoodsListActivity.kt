package com.cheng.goods.ui.activity

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder
import cn.bingoogolapple.refreshlayout.BGARefreshLayout
import com.cheng.baselibrary.ext.startLoading
import com.cheng.baselibrary.ui.activity.BaseMvpActivity
import com.cheng.goods.R
import com.cheng.goods.common.GoodsConstant
import com.cheng.goods.common.GoodsConstant.Companion.KEY_CATEGORY_ID
import com.cheng.goods.common.GoodsConstant.Companion.KEY_GOODS_KEYWORD
import com.cheng.goods.injection.component.DaggerGoodsComponent
import com.cheng.goods.injection.module.GoodsModule
import com.cheng.goods.presenter.GoodsListPresenter
import com.cheng.goods.presenter.view.GoodsListView
import com.kennyc.view.MultiStateView
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.kotlin.goods.data.protocol.GoodsInfo
import com.kotlin.goods.ui.adapter.GoodsListAdapter
import kotlinx.android.synthetic.main.activity_goods.*
import org.jetbrains.anko.startActivity

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
        multiStateView.startLoading()
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
                        startActivity<GoodsDetailActivity>(GoodsConstant.KEY_GOODS_ID to item.id)
                    }
                })
    }

    /**
     * 获取数据
     * 根据intent传递进来的KEY_SEARCH_GOODS_TYPE来判断是根据关键字获取还是分类来获取
     */
    private fun loadData() {
        if (intent.getIntExtra(GoodsConstant.KEY_SEARCH_GOODS_TYPE, 0) == GoodsConstant.SEARCH_GOODS_TYPE_KEYWORD) {
            mPresenter.getGoodsListByKeyword(keyword = intent.getStringExtra(KEY_GOODS_KEYWORD),
                    pageNo = currentPageNo)
        } else {
            mPresenter.getGoodsList(categoryId = intent.getIntExtra(KEY_CATEGORY_ID, 1),
                    pageNo = currentPageNo)
        }
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
            currentPageNo++
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
        DaggerGoodsComponent.builder()
                .activityComponent(activityComponent)
                .goodsModule(GoodsModule())
                .build()
                .inject(this)

        mPresenter.mView = this
    }

}