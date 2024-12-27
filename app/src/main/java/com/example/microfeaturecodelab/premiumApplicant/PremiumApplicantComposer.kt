package com.example.microfeaturecodelab.premiumApplicant

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.microfeaturecodelab.base.AbstractMicroFeatureComposer
import javax.inject.Inject

class PremiumApplicantComposer @Inject constructor(): AbstractMicroFeatureComposer<PremiumApplicantViewModel>() {
    @Composable
    override fun Content(
        viewModel: PremiumApplicantViewModel,
        modifier: Modifier,
        onAction: (Any) -> Unit
    ) {
        TODO("Not yet implemented")
    }
}