package com.cheng.goods.ui.fragment

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cheng.baselibrary.ext.isVisible
import com.cheng.baselibrary.ext.srartLoading
import com.cheng.baselibrary.ui.fragment.BaseMvpFragment
import com.cheng.goods.R
import com.cheng.goods.data.protocol.CategoryInfo
import com.cheng.goods.injection.component.DaggerCategoryComponent
import com.cheng.goods.injection.module.CategoryModule
import com.cheng.goods.presenter.CategoryPresenter
import com.cheng.goods.presenter.view.CategoryView
import com.cheng.goods.ui.adapter.SecondCategoryAdapter
import com.cheng.goods.ui.adapter.TopCategoryAdapter
import com.kennyc.view.MultiStateView
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_category.*

/**
 * User: Cheng
 * Date: 2018-01-11
 * Time: 22:52
 * Describe: 商品分类页面
 */

class CategoryFragment : BaseMvpFragment<CategoryPresenter>(), CategoryView {

    private lateinit var topCategoryAdapter: TopCategoryAdapter
    private lateinit var secondCategoryAdapter: SecondCategoryAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater?.inflate(R.layout.fragment_category, null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTopCategoryRv()
        initSecondCategoryRv()
        loadData()
    }

    /**
     * 初始化依赖对象
     */
    override fun injectComponent() {
        DaggerCategoryComponent.builder()
                .activityComponent(activityComponent)
                .categoryModule(CategoryModule())
                .build()
                .inject(this)
        mPresenter.mView = this
    }

    /**
     * 初始化了一级分类列表
     */
    private fun initTopCategoryRv() {
        topCategoryRv.layoutManager = LinearLayoutManager(activity)
        topCategoryAdapter = TopCategoryAdapter(activity)
        topCategoryRv.adapter = topCategoryAdapter

        topCategoryAdapter
                .setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<CategoryInfo> {
                    override fun onItemClick(item: CategoryInfo, position: Int) {
                        topCategoryAdapter.dataList.forEach {
                            it.isSelected = item.id == it.id
                        }
                        topCategoryAdapter.notifyDataSetChanged()
                        loadData(item.id)
                    }
                })

    }

    /**
     * 初始化了二级分类列表
     */
    private fun initSecondCategoryRv() {
        secondCategoryRv.layoutManager = GridLayoutManager(activity, 3)
        secondCategoryAdapter = SecondCategoryAdapter(activity)
        secondCategoryRv.adapter = secondCategoryAdapter

        secondCategoryAdapter
                .setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<CategoryInfo> {
                    override fun onItemClick(item: CategoryInfo, position: Int) {
                    }
                })
    }

    /**
     * 获取分类列表成功的回调
     */
    override fun onGetCategoryResult(result: MutableList<CategoryInfo>?) {
        if (result != null && result.size != 0) {
            if (result[0].parentId == 0) {
                topCategoryAdapter.setData(result)
                topCategoryAdapter.dataList[0].isSelected = true
                loadData(result[0].id)
            } else {
                secondCategoryAdapter.setData(result)
                multiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
                topCategoryIv.isVisible(true)
                categoryTitleTv.isVisible(true)
            }
        } else {
            multiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
            topCategoryIv.isVisible(false)
            categoryTitleTv.isVisible(false)
        }

    }

    /**
     * 获取分类数据
     */
    private fun loadData(parentId: Int = 0) {
        if (parentId != 0) {
            multiStateView.srartLoading()
        }
        mPresenter.getCategory(parentId = parentId)
    }

}