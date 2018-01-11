package com.cheng.mall.ui.adapter

import android.content.Context
import android.graphics.Paint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cheng.mall.R
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.kotlin.base.utils.GlideUtils
import kotlinx.android.synthetic.main.layout_home_discount_item.view.*

/**
 * User: Cheng
 * Date: 2018-01-10
 * Time: 20:47
 * Describe: 首页折扣列表适配
 */

class HomeDiscountAdapter(context: Context) :
        BaseRecyclerViewAdapter<String, HomeDiscountAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_home_discount_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        GlideUtils.loadUrlImage(mContext, dataList[position], holder.itemView.goodsIconIv)

        holder.itemView.discountAfterTv.text = "$ 998.00"
        holder.itemView.discountBeforeTv.text = "$ 1099.00"
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.discountBeforeTv.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
            itemView.discountBeforeTv.paint.isAntiAlias = true
        }

    }
}