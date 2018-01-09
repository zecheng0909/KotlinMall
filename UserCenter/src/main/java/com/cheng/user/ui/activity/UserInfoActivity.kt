package com.cheng.user.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.cheng.baselibrary.common.BaseConstant
import com.cheng.baselibrary.ext.onClick
import com.cheng.baselibrary.ui.activity.BaseMvpActivity
import com.cheng.user.R
import com.cheng.user.data.protocol.UserInfo
import com.cheng.user.injection.component.DaggerUserComponent
import com.cheng.user.injection.module.UserModule
import com.cheng.user.presenter.UserInfoPresenter
import com.cheng.user.presenter.view.UserInfoView
import com.jph.takephoto.app.TakePhoto
import com.jph.takephoto.app.TakePhotoImpl
import com.jph.takephoto.compress.CompressConfig
import com.jph.takephoto.model.InvokeParam
import com.jph.takephoto.model.TContextWrap
import com.jph.takephoto.model.TResult
import com.jph.takephoto.permission.InvokeListener
import com.jph.takephoto.permission.PermissionManager
import com.jph.takephoto.permission.PermissionManager.TPermissionType
import com.kotlin.base.utils.AppPrefsUtils
import com.kotlin.base.utils.DateUtils
import com.kotlin.base.utils.GlideUtils
import com.kotlin.provider.common.ProviderConstant
import com.kotlin.user.utils.UserPrefsUtils
import com.qiniu.android.http.ResponseInfo
import com.qiniu.android.storage.UpCompletionHandler
import com.qiniu.android.storage.UploadManager
import kotlinx.android.synthetic.main.activity_user_info.*
import org.json.JSONObject
import java.io.File


/**
 * User: Cheng
 * Date: 2018-01-09
 * Time: 14:51
 * Describe: 个人信息页面
 */

class UserInfoActivity : BaseMvpActivity<UserInfoPresenter>(), UserInfoView,
        TakePhoto.TakeResultListener, InvokeListener {

    private lateinit var invokeParam: InvokeParam
    private lateinit var takePhoto: TakePhotoImpl

    private lateinit var tempFile: File
    private var localFilePath: String? = null
    private lateinit var remoteFileUrl: String

    private var userIcon: String? = null
    private var userName: String? = null
    private var userMobile: String? = null
    private var userGender: String? = null
    private var userSign: String? = null

    private val uploadManager: UploadManager by lazy { UploadManager() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        takePhoto = TakePhotoImpl(this, this)
        takePhoto.onCreate(savedInstanceState)

        initView()
        initData()
    }

    /**
     * 初始化数据
     */
    private fun initData() {
        userIcon = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ICON)
        userName = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_NAME)
        userMobile = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_MOBILE)
        userGender = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_GENDER)
        userSign = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_SIGN)
        remoteFileUrl = localFilePath!!
        if (userIcon != "") GlideUtils.loadUrlImage(this, userIcon!!, userIconIv)
        userNameEt.setText(userName)
        userMobileTv.text = userMobile
        if (userGender == "0") {
            genderMaleRb.isChecked = true
        } else {
            genderFemaleRb.isChecked = true
        }
        userNameEt.setText(userName)
        userSignEt.setText(userSign)
    }

    /**
     * 初始化视图
     */
    private fun initView() {
        userIconView.onClick {
            showAlertView()
        }

        headerBar.getRightText().onClick {
            mPresenter.editUser(userIcon = remoteFileUrl,
                    userName = userNameEt.text.toString(),
                    gender = if (genderMaleRb.isChecked) "0" else "1",
                    sign = userSignEt.text.toString())
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

    /**
     * 服务器返回上传凭证的回调
     */
    override fun onGetUploadTokenResult(result: String) {
        if (localFilePath == null) return
        showLoading()
        uploadManager.put(localFilePath, null, result, object : UpCompletionHandler {
            override fun complete(key: String?, info: ResponseInfo?, response: JSONObject?) {
                remoteFileUrl = "${BaseConstant.IMAGE_SERVER_ADDRESS}${response?.get("hash")}"
                hideLoading()
                GlideUtils.loadUrlImage(this@UserInfoActivity, remoteFileUrl, userIconIv)
            }

        }, null)
    }

    /**
     * 保存用户信息成功
     */
    override fun onEditUserTokenResult(userInfo: UserInfo) {
        UserPrefsUtils.putUserInfo(userInfo)
    }

    /**
     * 获取图片成功的回调
     */
    override fun takeSuccess(result: TResult?) {
        Log.e("Cheng", result?.image?.originalPath)
        Log.e("Cheng", result?.image?.compressPath)
        localFilePath = result?.image?.compressPath
        mPresenter.getUploadToken()
    }

    /**
     * 取消获取图片的回调
     */
    override fun takeCancel() {
        onError("取消选择")
    }

    /**
     * 获取图片失败的回调
     */
    override fun takeFail(result: TResult?, msg: String?) {
        Log.e("Cheng", msg)
    }

    /**
     * 创建临时文件,拍照时使用
     */
    fun createTempFile() {
        val tempFileName = "${DateUtils.curTime}.png"
        tempFile = if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            File(Environment.getExternalStorageDirectory(), tempFileName)
        } else {
            File(filesDir, tempFileName)
        }
    }

    /**
     * 绑定TakePhoto的声明周期
     */
    override fun onSaveInstanceState(outState: Bundle?) {
        takePhoto.onSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    /**
     * 绑定TakePhoto的声明周期
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        takePhoto.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    /**
     * 授权管理回调
     */
    override fun invoke(invokeParam: InvokeParam?): PermissionManager.TPermissionType {
        val type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam!!.method)
        if (TPermissionType.WAIT == type) {
            this.invokeParam = invokeParam
        }
        return type
    }

    /**
     * 处理Android6.0、7.0动态权限
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,
                                            grantResults: IntArray) {
        val type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults)
        PermissionManager.handlePermissionsResult(this, type, invokeParam, this)
    }
}