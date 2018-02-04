package com.cheng.order.injection.component

import com.cheng.baselibrary.injection.PerComponentScope
import com.cheng.baselibrary.injection.component.ActivityComponent
import com.cheng.order.injection.module.OrderModule
import com.cheng.order.ui.activity.OrderConfirmActivity
import com.cheng.order.ui.fragment.OrderFragment
import dagger.Component

/**
 * User: Cheng
 * Date: 2018-02-02
 * Time: 13:00
 * Describe:
 */

@Component(dependencies = [(ActivityComponent::class)], modules = [(OrderModule::class)])
@PerComponentScope
interface OrderComponent {

    fun inject(activity: OrderConfirmActivity)

    fun inject(fragment: OrderFragment)

}