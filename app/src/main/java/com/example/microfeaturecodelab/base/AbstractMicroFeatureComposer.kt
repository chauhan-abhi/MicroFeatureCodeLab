package com.example.microfeaturecodelab.base

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.LifecycleOwner
import com.example.microfeaturecodelab.personalisedjob.presentation.featureconfig.ComponentConfig

@Suppress("UNCHECKED_CAST")
abstract class AbstractMicroFeatureComposer<V : MicroFeatureViewModel> {

    fun renderItem(
        viewModel: MicroFeatureViewModel,
        config: ComponentConfig,
        scope: LazyListScope,
        lifecycle: LifecycleOwner,
        modifier: Modifier,
        onAction: (Any) -> Unit,
    ) {
        scope.content(
            viewModel = viewModel as V,
            config = config,
            lifecycle = lifecycle,
            modifier = modifier,
            onAction = onAction,
        )
    }

    abstract fun LazyListScope.content(
        viewModel: V,
        config: ComponentConfig,
        lifecycle: LifecycleOwner,
        modifier: Modifier = Modifier,
        onAction: (Any) -> Unit,
    )

}