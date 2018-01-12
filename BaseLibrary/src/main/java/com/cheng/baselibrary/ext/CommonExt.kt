package com.cheng.baselibrary.ext

import android.graphics.drawable.AnimationDrawable
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.cheng.baselibrary.R
import com.cheng.baselibrary.data.protocol.BaseResponse
import com.cheng.baselibrary.rx.BaseFunc
import com.cheng.baselibrary.rx.BaseFuncBoolean
import com.cheng.baselibrary.rx.BaseSubscriber
import com.kennyc.view.MultiStateView
import com.kotlin.base.utils.GlideUtils
import com.kotlin.base.widgets.DefaultTextWatcher
import com.trello.rxlifecycle.LifecycleProvider
import org.jetbrains.anko.find
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * User: Cheng
 * Date: 2018-01-03
 * Time: 17:43
 * Describe: 都是一些公共扩展方法
 */


/**
 * 扩展了Observable,简化了代码
 * 完成了 指定订阅线程在 io
 * 接收订阅在 main
 * */
fun <T> Observable<T>.execute(subscriber: BaseSubscriber<T>, lifecycleProvider: LifecycleProvider<*>) {
    this.observeOn(AndroidSchedulers.mainThread())
            .compose(lifecycleProvider.bindToLifecycle())
            .subscribeOn(Schedulers.io())
            .subscribe(subscriber)
}

/**
 * 扩展了View设置点击事件的方法
 * 使代码简洁
 */
fun View.onClick(action: () -> Unit) {
    this.setOnClickListener { action() }
}

/**
 * 替代了flatMap,传入BaseFunc
 */
fun <T> Observable<BaseResponse<T>>.convert(): Observable<T> {
    return this.flatMap(BaseFunc())
}

/**
 * 替代了flatMap,传入BaseFuncBoolean
 */
fun <T> Observable<BaseResponse<T>>.convertBoolean(): Observable<Boolean> {
    return this.flatMap(BaseFuncBoolean())
}

/**
 * button监听EditText的状态,来改变自身是否可以点击,判断逻辑根据传递进来的Lambda
 */
fun Button.enable(et: EditText, action: () -> Boolean) {

    val btn = this

    et.addTextChangedListener(object : DefaultTextWatcher() {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            super.onTextChanged(s, start, before, count)
            btn.isEnabled = action()
        }
    })
}

/**
 * ImageView加载网络图片
 */
fun ImageView.loadUrl(url: String) {
    GlideUtils.loadUrlImage(context, url, this)
}

/**
 * 控件是否显示
 */
fun View.isVisible(isVisible: Boolean) {
    this.visibility = if (isVisible) View.VISIBLE else View.GONE
}

/**
 * 多状态视图开始显示加载动画
 */
fun MultiStateView.srartLoading() {
    this.viewState = MultiStateView.VIEW_STATE_LOADING
    val loadingView = this.getView(MultiStateView.VIEW_STATE_LOADING)
    val animationBackground = loadingView!!.find<View>(R.id.loading_anim_view).background
    (animationBackground as AnimationDrawable).start()
}