package com.cheng.user.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.cheng.baselibrary.ext.onClick
import com.cheng.baselibrary.ui.activity.BaseMvpActivity
import com.cheng.user.R
import com.cheng.user.injection.component.DaggerUserComponent
import com.cheng.user.injection.module.UserModule
import com.cheng.user.presenter.UserInfoPresenter
import com.cheng.user.presenter.view.UserInfoView
import com.jph.takephoto.app.TakePhoto
import com.jph.takephoto.app.TakePhotoImpl
import com.jph.takephoto.compress.CompressConfig
import com.jph.takephoto.model.TResult
import com.kotlin.base.utils.DateUtils
import kotlinx.android.synthetic.main.activity_user_info.*
import org.jetbrains.anko.defaultSharedPreferences
import java.io.File


/**
 * User: Cheng
 * Date: 2018-01-09
 * Time: 14:51
 * Describe: 个人信息页面
 */

class UserInfoActivity : BaseMvpActivity<UserInfoPresenter>(), UserInfoView,
        TakePhoto.TakeResultListener {

    private lateinit var takePhoto: TakePhotoImpl

    private lateinit var tempFile: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        takePhoto = TakePhotoImpl(this, this)
        takePhoto.onCreate(savedInstanceState)

        initView()
    }

    /**
     * 初始化视图
     */
    private fun initView() {
        userIconView.onClick {
            showAlertView()
        }
    }

    /**
     * 初始化依赖对象
     */
    override fun injectComponent() {
        DaggerUserComponent.builder()
                .activityComponent(activityComponent)
                .userModule(UserModule())
                .build()
                .inject(this)

        mPresenter.mView = this
    }

    /**
     * 弹出选择图片Dialog
     */
    private fun showAlertView() {
        AlertView("选择图片", null, "取消", null,
                arrayOf("拍照", "相册"),
                this, AlertView.Style.ActionSheet, object : OnItemClickListener {
            override fun onItemClick(o: Any?, position: Int) {
                takePhoto.onEnableCompress(CompressConfig.ofDefaultConfig(), false)
                when (position) {
                    0 -> {
                        createTempFile()
                        takePhoto.onPickFromCapture(Uri.fromFile(tempFile))
                    }
                    1 -> takePhoto.onPickFromGallery()
                }
            }
        }).show()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        takePhoto.onSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        takePhoto.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun takeSuccess(result: TResult?) {
        Log.e("Cheng", result?.image?.originalPath)
        Log.e("Cheng", result?.image?.compressPath)
    }

    override fun takeCancel() {
        onError("取消选择")
    }

    override fun takeFail(result: TResult?, msg: String?) {
        Log.e("Cheng", msg)
    }

    fun createTempFile() {
        val tempFileName = "${DateUtils.curTime}.png"
        tempFile = if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            File(Environment.getExternalStorageDirectory(), tempFileName)
        } else {
            File(filesDir, tempFileName)
        }
    }
}