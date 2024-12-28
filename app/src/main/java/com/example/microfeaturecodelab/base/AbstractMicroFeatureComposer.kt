package com.example.microfeaturecodelab.base

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.microfeaturecodelab.personalisedjob.presentation.featureconfig.ComponentConfig

abstract class AbstractMicroFeatureComposer<V : MicroFeatureViewModel> {

    @Composable
    fun ComposerContent(
        viewModel: MicroFeatureViewModel,
        config: ComponentConfig,
        modifier: Modifier,
        onAction: (Any) -> Unit,
    ) {
        Content(
            viewModel = viewModel as V,
            config = config,
            modifier = modifier,
            onAction = onAction
        )
    }

    @Composable
    abstract fun Content(
        viewModel: V,
        config: ComponentConfig,
        modifier: Modifier = Modifier,
        onAction: (Any) -> Unit,
    )

}