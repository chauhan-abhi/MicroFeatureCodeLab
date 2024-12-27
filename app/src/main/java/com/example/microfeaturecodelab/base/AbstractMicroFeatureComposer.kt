package com.example.microfeaturecodelab.base

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


abstract class AbstractMicroFeatureComposer<V : MicroFeatureViewModel> {

    @Composable
    fun ComposerContent(
        viewModel: MicroFeatureViewModel,
        modifier: Modifier,
        onAction: (Any) -> Unit
    ) {
        Content(viewModel = viewModel as V, modifier = modifier, onAction = onAction)
    }

    @Composable
    abstract fun Content(
        viewModel: V,
        modifier: Modifier = Modifier,
        onAction: (Any) -> Unit,
    )

}