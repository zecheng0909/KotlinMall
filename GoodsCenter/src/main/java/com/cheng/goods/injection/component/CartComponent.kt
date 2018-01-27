package com.cheng.goods.injection.component

import com.cheng.baselibrary.injection.PerComponentScope
import com.cheng.baselibrary.injection.component.ActivityComponent
import com.cheng.goods.injection.module.CartModule
import com.cheng.goods.injection.module.GoodsModule
import com.cheng.goods.ui.activity.GoodsListActivity
import com.cheng.goods.ui.fragment.CartListFragment
import com.cheng.goods.ui.fragment.GoodsDetailTabOneFragment
import dagger.Component

/**
 * User: Cheng
 * Date: 2018-01-26
 * Time: 9:52
 * Describe:
 */

@Component(dependencies = [(ActivityComponent::class)], modules = [(CartModule::class)])
@PerComponentScope
interface CartComponent {
    fun inject(cartListFragment: CartListFragment)
}