package com.example.microfeaturecodelab.personalisedjob.presentation.featureconfig

import androidx.compose.runtime.Stable
import com.example.microfeaturecodelab.base.AbstractMicroFeatureComposer
import com.example.microfeaturecodelab.base.MicroFeatureViewModel
import kotlinx.coroutines.flow.SharingStarted

@Stable
data class ComponentConfig(
    val id: String,
    val type: String,
    val itemType: ItemType = ItemType.ITEM,
    val fetchStrategy: SharingStarted = FetchStrategy.Lazy().sharingStarted,
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

sealed class FetchStrategy {
    data class Lazy(
        val sharingStarted: SharingStarted = SharingStarted.WhileSubscribed(
            5000
        )
    ) : FetchStrategy()

    data class Eager(val sharingStarted: SharingStarted = SharingStarted.Eagerly) : FetchStrategy()
}