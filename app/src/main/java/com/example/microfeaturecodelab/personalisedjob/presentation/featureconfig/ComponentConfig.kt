package com.example.microfeaturecodelab.personalisedjob.presentation.featureconfig

import androidx.compose.runtime.Stable
import com.example.microfeaturecodelab.base.AbstractMicroFeatureComposer
import com.example.microfeaturecodelab.base.MicroFeatureViewModel

@Stable
data class ComponentConfig(
    val id: String,
    val type: String,
    val itemType: ItemType = ItemType.ITEM
)

enum class ItemType {
    ITEM,
    LIST,
    STICKY
}

@Stable
data class ComponentDependencies(
    val componentVM: MicroFeatureViewModel,
    val componentComposer: AbstractMicroFeatureComposer<out MicroFeatureViewModel>
)