package com.kotlin.goods.ui.adapter


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cheng.baselibrary.ext.loadUrl
import com.cheng.goods.R
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.kotlin.base.utils.YuanFenConverter
import com.kotlin.goods.data.protocol.GoodsInfo
import kotlinx.android.synthetic.main.layout_goods_item.view.*


/**
 * User: Cheng
 * Date: 2018-01-15
 * Time: 16:39
 * Describe: 商品列表的适配器
 */
class GoodsListAdapter(context: Context) : BaseRecyclerViewAdapter<GoodsInfo, GoodsListAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_goods_item,
                        parent,
                        false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]
        //商品图标
        holder.itemView.mGoodsIconIv.loadUrl(model.goodsDefaultIcon)
        //商品描述
        holder.itemView.mGoodsDescTv.text = model.goodsDesc
        //商品价格
        holder.itemView.mGoodsPriceTv.text = YuanFenConverter.changeF2YWithUnit(model.goodsDefaultPrice)
        //商品销量及库存
        holder.itemView.mGoodsSalesStockTv.text = "销量${model.goodsSalesCount}件     库存${model.goodsStockCount}"
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
