package com.example.microfeaturecodelab.base

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import com.example.microfeaturecodelab.personalisedjob.presentation.featureconfig.ComponentConfig

abstract class AbstractMicroFeatureComposer<V : MicroFeatureViewModel> {

    fun renderItem(
        viewModel: MicroFeatureViewModel,
        config: ComponentConfig,
        scope: LazyListScope,
        modifier: Modifier,
        onAction: (Any) -> Unit,
    ) {
        scope.content(
            viewModel = viewModel as V,
            config = config,
            modifier = modifier,
            onAction = onAction
        )
    }

    abstract fun LazyListScope.content(
        viewModel: V,
        config: ComponentConfig,
        modifier: Modifier = Modifier,
        onAction: (Any) -> Unit,
    )

}