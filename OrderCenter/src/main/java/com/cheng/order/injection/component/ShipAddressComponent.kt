package com.cheng.order.injection.component

import com.cheng.baselibrary.injection.PerComponentScope
import com.cheng.baselibrary.injection.component.ActivityComponent
import com.cheng.order.injection.module.OrderModule
import com.cheng.order.injection.module.ShipAddressModule
import com.cheng.order.ui.activity.ShipAddressActivity
import com.cheng.order.ui.activity.ShipAddressEditActivity
import dagger.Component

/**
 * User: Cheng
 * Date: 2018-02-02
 * Time: 13:00
 * Describe:
 */

@Component(dependencies = [(ActivityComponent::class)], modules = [(ShipAddressModule::class)])
@PerComponentScope
interface ShipAddressComponent {

    fun inject(activity: ShipAddressEditActivity)

    fun inject(activity: ShipAddressActivity)

}