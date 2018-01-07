package com.cheng.baselibrary.widgets

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.view.Gravity
import android.widget.ImageView
import com.cheng.baselibrary.R
import org.jetbrains.anko.find

/**
 * User: Cheng
 * Date: 2018-01-06
 * Time: 22:56
 * Describe: 自定义加载弹窗
 */

class ProgressLoading private constructor(context: Context, themeId: Int) : Dialog(context, themeId) {

    companion object {

        private var animDrawable: AnimationDrawable? = null

        lateinit var progressDialog: ProgressLoading

        /**
         * 加载了预设的xml布局
         * 设置了点击外部不可关闭dialog
         * 获得背景animation,通过 显示/隐藏 的方式来控制动画开启/停止
         */
        fun create(context: Context): ProgressLoading {
            progressDialog = ProgressLoading(context, R.style.LightProgressDialog)
            progressDialog.setContentView(R.layout.progress_dialog)
            progressDialog.setCancelable(true)
            progressDialog.setCanceledOnTouchOutside(false)


            val pl = progressDialog.window.attributes
            pl.gravity = Gravity.CENTER
            pl.dimAmount = 0.2F
            progressDialog.window.attributes = pl

            val loadingView = progressDialog.find<ImageView>(R.id.iv_loading)

            animDrawable = loadingView.background as AnimationDrawable

            return progressDialog
        }
    }

    /**
     * 显示Dialog,开始动画效果
     */
    fun showLoading() {
        super.show()
        animDrawable?.start()
    }

    /**
     * 隐藏Dialog,停止动画效果
     */
    fun hideLoading() {
        super.dismiss()
        animDrawable?.stop()
    }
}