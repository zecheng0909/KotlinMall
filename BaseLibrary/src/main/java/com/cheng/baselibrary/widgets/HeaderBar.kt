package com.cheng.baselibrary.widgets

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.cheng.baselibrary.R
import com.cheng.baselibrary.ext.onClick
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

    var titleTextView: String? = null

    var rightTextView: String? = null

    init {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.HeaderBar)

        isShowBack = attributes.getBoolean(R.styleable.HeaderBar_isShowBack, true)
        titleTextView = attributes.getString(R.styleable.HeaderBar_titleText)
        rightTextView = attributes.getString(R.styleable.HeaderBar_rightText)

        attributes.recycle()

        initView()

        backIv.onClick {
            if (context is Activity) {
                context.finish()
            }
        }
    }

    /**
     * 根据xml配置的属性,初始化对应的控件
     */
    private fun initView() {
        View.inflate(context, R.layout.layout_header_bar, this)

        backIv.visibility = if (isShowBack) View.VISIBLE else View.GONE

        titleTextView?.let { titleTv.text = it }

        rightTextView?.let {
            rightTv.text = it
            rightTv.visibility = View.VISIBLE
        }

    }

    fun getRightView(): TextView {
        return rightTv
    }

    fun getRightText(): String {
        return rightTv.text.toString()
    }
}