package com.example.microfeaturecodelab.premiumApplicant.presentation

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.microfeaturecodelab.base.AbstractMicroFeatureComposer
import com.example.microfeaturecodelab.personalisedjob.presentation.featureconfig.ComponentConfig
import javax.inject.Inject

class PremiumApplicantComposer @Inject constructor(): AbstractMicroFeatureComposer<PremiumApplicantViewModel>() {
    @Composable
    override fun Content(
        viewModel: PremiumApplicantViewModel,
        config: ComponentConfig,
        modifier: Modifier,
        onAction: (Any) -> Unit
    ) {
        Log.d("PremiumApplicantComposer", "PremiumApplicantComposer: ${config.id}")
        LaunchedEffect(viewModel) {
            Log.d("JobRecommendationSection", "$viewModel Launched Effect")
        }
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            modifier = modifier
                .padding(32.dp),
        ) {
            Text("Item ${config.id}")
            Text("Premium applicant")

        }
    }
}