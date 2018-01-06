package com.cheng.baselibrary.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.cheng.baselibrary.R
import kotlinx.android.synthetic.main.layout_header_bar.view.*

/**
 * User: Cheng
 * Date: 2018-01-06
 * Time: 21:47
 * Describe: 自定义HeaderBar
 */

class HeaderBar @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    var isShowBack: Boolean = true

    var titleText: String? = null

    var rightText: String? = null

    init {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.HeaderBar)

        isShowBack = attributes.getBoolean(R.styleable.HeaderBar_isShowBack, true)
        titleText = attributes.getString(R.styleable.HeaderBar_titleText)
        rightText = attributes.getString(R.styleable.HeaderBar_rightText)

        attributes.recycle()

        initView()
    }

    private fun initView() {
        View.inflate(context, R.layout.layout_header_bar, this)

        backIv.visibility = if (isShowBack) View.VISIBLE else View.GONE

        titleText?.let { titleTv.text = it }

        rightText?.let {
            rightTv.text = it
            rightTv.visibility = View.VISIBLE
        }

    }
}