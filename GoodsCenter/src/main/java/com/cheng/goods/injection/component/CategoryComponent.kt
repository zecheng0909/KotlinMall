package com.cheng.goods.injection.component

import com.cheng.baselibrary.injection.PerComponentScope
import com.cheng.baselibrary.injection.component.ActivityComponent
import com.cheng.goods.injection.module.CategoryModule
import com.cheng.goods.ui.fragment.CategoryFragment
import dagger.Component

/**
 * User: Cheng
 * Date: 2018-01-12
 * Time: 10:13
 * Describe: 业务层的Component,依赖于ActivityComponent
 */

@PerComponentScope
@Component(dependencies = [(ActivityComponent::class)], modules = [(CategoryModule::class)])
interface CategoryComponent {

    fun inject(categoryFragment: CategoryFragment)
}
