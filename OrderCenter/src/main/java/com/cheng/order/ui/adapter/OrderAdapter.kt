package com.cheng.order.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.cheng.baselibrary.ext.loadUrl
import com.cheng.baselibrary.ext.onClick
import com.cheng.baselibrary.ext.setVisible
import com.cheng.order.R
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.kotlin.base.utils.YuanFenConverter
import com.kotlin.order.common.OrderConstant
import com.kotlin.order.common.OrderStatus
import com.kotlin.order.data.protocol.OrderInfo
import kotlinx.android.synthetic.main.layout_order_item.view.*
import org.jetbrains.anko.dip

/**
 * User: Cheng
 * Date: 2018-02-04
 * Time: 19:44
 * Describe: 订单列表数据适配器
 */

class OrderAdapter(context: Context) : BaseRecyclerViewAdapter<OrderInfo, OrderAdapter.ViewHolder>(context) {

    private var onOptClickListener: OnOptClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_order_item,
                        parent,
                        false)
        return ViewHolder(view)
    }

    /**
     * 根据订单商品数量绑定不同的数据
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]
        if (model.orderGoodsList.size == 1) {
            holder.itemView.singleGoodsView.setVisible(true)
            holder.itemView.multiGoodsView.setVisible(false)
            val orderGoods = model.orderGoodsList[0]
            holder.itemView.goodsIconIv.loadUrl(orderGoods.goodsIcon)
            holder.itemView.goodsDescTv.text = orderGoods.goodsDesc
            holder.itemView.goodsPriceTv.text = "$${YuanFenConverter.changeF2Y(orderGoods.goodsPrice)}"
            holder.itemView.goodsCountTv.text = "x${orderGoods.goodsCount}"
        } else {
            holder.itemView.singleGoodsView.setVisible(false)
            holder.itemView.multiGoodsView.setVisible(true)
            holder.itemView.multiGoodsView.removeAllViews()
            model.orderGoodsList.forEach {
                val imageView = ImageView(mContext)
                val pl = ViewGroup.MarginLayoutParams(mContext.dip(60F), mContext.dip(60F))
                pl.rightMargin = mContext.dip(15F)
                imageView.layoutParams = pl
                imageView.loadUrl(it.goodsIcon)
                holder.itemView.multiGoodsView.addView(imageView)
            }

        }

        when (model.orderStatus) {
            OrderStatus.ORDER_WAIT_PAY -> {
                holder.itemView.orderStatusNameTv.text = "待支付"
                holder.itemView.orderStatusNameTv.setTextColor(mContext.resources.getColor(R.color.common_red))
                setOptVisible(false, true, true, holder)
            }

            OrderStatus.ORDER_WAIT_CONFIRM -> {
                holder.itemView.orderStatusNameTv.text = "待收货"
                holder.itemView.orderStatusNameTv.setTextColor(mContext.resources.getColor(R.color.common_blue))
                setOptVisible(true, false, true, holder)
            }

            OrderStatus.ORDER_COMPLETED -> {
                holder.itemView.orderStatusNameTv.text = "已完成"
                holder.itemView.orderStatusNameTv.setTextColor(mContext.resources.getColor(R.color.common_yellow))
                setOptVisible(false, false, false, holder)
            }

            OrderStatus.ORDER_CANCELED -> {
                holder.itemView.orderStatusNameTv.text = "已取消"
                holder.itemView.orderStatusNameTv.setTextColor(mContext.resources.getColor(R.color.common_gray))
                setOptVisible(false, false, false, holder)
            }
        }

        holder.itemView.orderInfoTv.text = "合计${model.orderGoodsList.size}件商品," +
                "总价$${YuanFenConverter.changeF2Y(model.totalPrice)}"

        //确认收货
        holder.itemView.confirmBtn.onClick {
            onOptClickListener?.onOptClick(OrderConstant.OPT_ORDER_CONFIRM, model)
        }

        //去支付
        holder.itemView.payBtn.onClick {
            onOptClickListener?.onOptClick(OrderConstant.OPT_ORDER_PAY, model)
        }

        //取消订单
        holder.itemView.cancelBtn.onClick {
            onOptClickListener?.onOptClick(OrderConstant.OPT_ORDER_CANCEL, model)
        }

    }

    private fun setOptVisible(confirmViewVisible: Boolean, payViewVisible: Boolean,
                              cancelViewVisible: Boolean, holder: ViewHolder) {
        holder.itemView.confirmBtn.setVisible(confirmViewVisible)
        holder.itemView.payBtn.setVisible(payViewVisible)
        holder.itemView.cancelBtn.setVisible(cancelViewVisible)

        if (confirmViewVisible or payViewVisible or cancelViewVisible) {
            holder.itemView.bottomView.setVisible(true)
        } else {
            holder.itemView.bottomView.setVisible(false)
        }
    }

    fun setOnOptClickListener(onOptClickListener: OnOptClickListener) {
        this.onOptClickListener = onOptClickListener
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    interface OnOptClickListener {
        fun onOptClick(optType: Int, orderInfo: OrderInfo)
    }
}