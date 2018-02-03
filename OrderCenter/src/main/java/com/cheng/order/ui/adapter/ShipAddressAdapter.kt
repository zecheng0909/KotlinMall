package com.kotlin.order.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cheng.baselibrary.ext.onClick
import com.cheng.order.R
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.kotlin.order.data.protocol.ShipAddressInfo
import kotlinx.android.synthetic.main.layout_address_item.view.*

/**
 * User: Cheng
 * Date: 2018-02-03
 * Time: 10:05
 * Describe: 收货地址数据适配
 */

class ShipAddressAdapter(context: Context) : BaseRecyclerViewAdapter<ShipAddressInfo,
        ShipAddressAdapter.ViewHolder>(context) {

    private var onOptClickListener: OnOptClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_address_item,
                        parent,
                        false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]

        holder.itemView.setDefaultTv.isSelected = (model.shipIsDefault == 0)
        holder.itemView.mShipNameTv.text = model.shipUserName + "    " + model.shipUserMobile
        holder.itemView.mShipAddressTv.text = model.shipAddress

        holder.itemView.setDefaultTv.onClick {
            onOptClickListener?.let {
                if (holder.itemView.setDefaultTv.isSelected) {
                    return@onClick
                }
                model.shipIsDefault = 0
                it.onSetDefault(model)
            }
        }

        holder.itemView.editTv.onClick {
            onOptClickListener?.let {
                it.onEdit(model)
            }
        }

        holder.itemView.deleteTv.onClick {
            onOptClickListener?.let {
                it.onDelete(model.id)
            }
        }

    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    fun setOptClickListener(onOptClickListener: OnOptClickListener) {
        this.onOptClickListener = onOptClickListener
    }

    /**
     * 设置默认,编辑,删除的回调接口
     */
    interface OnOptClickListener {
        fun onSetDefault(item: ShipAddressInfo)
        fun onEdit(item: ShipAddressInfo)
        fun onDelete(id: Int)
    }
}
