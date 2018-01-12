package com.cheng.goods.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cheng.baselibrary.ext.loadUrl
import com.cheng.goods.R
import com.cheng.goods.data.protocol.CategoryInfo
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import kotlinx.android.synthetic.main.layout_second_category_item.view.*

/**
 * User: Cheng
 * Date: 2018-01-11
 * Time: 23:08
 * Describe: 二级分类列表的适配
 */

class SecondCategoryAdapter(context: Context) :
        BaseRecyclerViewAdapter<CategoryInfo, SecondCategoryAdapter.ViewHolder>(context) {


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_second_category_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        holder.itemView.mSecondCategoryNameTv.text = dataList[position].categoryName
        holder.itemView.categoryIconIv.loadUrl(dataList[position].categoryIcon)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}