package com.cheng.baselibrary.common

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import java.util.*

/**
 * User: Cheng
 * Date: 2018-01-06
 * Time: 19:50
 * Describe: App管理器,管理所有的Activity
 */

class AppManager private constructor() {

    /**
     * 管理activity的栈
     */
    private var activityStack = Stack<Activity>()

    companion object {
        val appManager: AppManager by lazy { AppManager() }
    }

    /**
     * 当activity创建时调用此方法,将该activity入栈
     */
    fun addActivity(activity: Activity) {
        activityStack.add(activity)
    }

    /**
     * 当activity销毁时调用此方法,将该activity移出栈
     */
    fun finishActivity(activity: Activity) {
        activity.finish()
        activityStack.remove(activity)
    }

    /**
     * 获得当前栈顶的activity
     */
    fun currentActivity(): Activity {
        return activityStack.lastElement()
    }

    /**
     * 将所有activity移出栈
     */
    fun finishAllActivity() {
        activityStack.forEach { activity ->
            activity.finish()
        }
    }

    /**
     * 清理栈,并退出app
     */
    @SuppressLint("MissingPermission")
    fun exitApp(context: Context) {
        finishAllActivity()
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        activityManager.killBackgroundProcesses(context.packageName)
        System.exit(0)
    }
}