package com.kotlin.goods.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cheng.baselibrary.ext.loadUrl
import com.cheng.baselibrary.ext.onClick
import com.cheng.goods.R
import com.cheng.goods.event.CartCheckedEvent
import com.cheng.goods.event.UpDateTotalPriceEvent
import com.cheng.goods.getEditText
import com.eightbitlab.rxbus.Bus
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.kotlin.base.utils.YuanFenConverter
import com.kotlin.base.widgets.DefaultTextWatcher
import com.kotlin.goods.data.protocol.CartGoodsInfo
import kotlinx.android.synthetic.main.layout_cart_goods_item.view.*

/**
 * User: Cheng
 * Date: 2018-01-27
 * Time: 9:35
 * Describe: 购物车数据适配器
 */

class CartGoodsAdapter(context: Context) : BaseRecyclerViewAdapter<CartGoodsInfo,
        CartGoodsAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_cart_goods_item,
                        parent,
                        false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]
        //是否选中
        holder.itemView.checkedCb.isChecked = model.isSelected
        //加载商品图片
        holder.itemView.mGoodsIconIv.loadUrl(model.goodsIcon)
        //商品描述
        holder.itemView.mGoodsDescTv.text = model.goodsDesc
        //商品SKU
        holder.itemView.mGoodsSkuTv.text = model.goodsSku
        //商品价格
        holder.itemView.mGoodsPriceTv.text = YuanFenConverter.changeF2YWithUnit(model.goodsPrice)
        //商品数量
        holder.itemView.goodsCountBtn.setCurrentNumber(model.goodsCount)

        //是否全选
        holder.itemView.checkedCb.onClick {
            model.isSelected = holder.itemView.checkedCb.isChecked
            var isAllSelected = dataList.all {
                it.isSelected
            }
            Bus.send(CartCheckedEvent(isAllSelected))
            Bus.send(UpDateTotalPriceEvent())
        }

        //数量改变
        holder.itemView.goodsCountBtn.getEditText()
                .addTextChangedListener(object : DefaultTextWatcher() {
                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                        model.goodsCount = s.toString().toInt()
                        Bus.send(UpDateTotalPriceEvent())
                    }
                })

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
