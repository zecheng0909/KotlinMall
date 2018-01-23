package com.kotlin.goods.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.cheng.goods.R
import com.cheng.goods.common.GoodsConstant
import com.cheng.goods.event.GoodsDetailImageEvent
import com.cheng.goods.event.GoodsSkuChangedEvent
import com.eightbitlab.rxbus.Bus
import com.kotlin.goods.data.protocol.GoodsSkuInfo
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import kotlinx.android.synthetic.main.layout_sku_view.view.*

/**
 * User: Cheng
 * Date: 2018-01-22
 * Time: 16:31
 * Describe: 单个SKU
 */

class SkuView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : FrameLayout(context, attrs, defStyle) {

    private lateinit var mGoodsSkuInfo: GoodsSkuInfo

    init {
        View.inflate(context, R.layout.layout_sku_view, this)
    }


    fun setSkuData(goodsSkuInfo: GoodsSkuInfo) {
        mGoodsSkuInfo = goodsSkuInfo
        mSkuTitleTv.text = goodsSkuInfo.skuTitle

        skuContentView.adapter = object : TagAdapter<String>(goodsSkuInfo.skuContent) {
            override fun getView(parent: FlowLayout?, position: Int, t: String?): View {
                val view = LayoutInflater.from(context).inflate(R.layout.layout_sku_item, parent, false) as TextView
                view.text = t
                return view
            }
        }

        skuContentView.adapter.setSelectedList(0)

        skuContentView.setOnTagClickListener { _, _, _ ->
            Bus.send(GoodsSkuChangedEvent())
            true
        }

    }

    /**
     * 获取选中的SKU
     */
    fun getSkuInfo(): String {
        return mSkuTitleTv.text.toString() + GoodsConstant.SKU_SEPARATOR +
                mGoodsSkuInfo.skuContent[skuContentView.selectedList.first()]
    }
}
