package com.cheng.goods.injection.component

import com.cheng.baselibrary.injection.PerComponentScope
import com.cheng.baselibrary.injection.component.ActivityComponent
import com.cheng.goods.injection.module.GoodsModule
import com.cheng.goods.ui.activity.GoodsListActivity
import dagger.Component

/**
 * User: Cheng
 * Date: 2018-01-16
 * Time: 13:52
 * Describe:
 */

@Component(dependencies = [(ActivityComponent::class)], modules = [(GoodsModule::class)])
@PerComponentScope
interface GoodsCompoent {

    fun inject(goodsListActivity: GoodsListActivity)
}