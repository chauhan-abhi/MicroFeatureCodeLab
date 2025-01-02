package com.example.microfeaturecodelab.premiumApplicant.presentation

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import com.example.microfeaturecodelab.base.AbstractMicroFeatureComposer
import com.example.microfeaturecodelab.personalisedjob.presentation.featureconfig.ComponentConfig
import javax.inject.Inject

class PremiumApplicantComposer @Inject constructor() :
    AbstractMicroFeatureComposer<PremiumApplicantViewModel>() {
    override fun LazyListScope.content(
        viewModel: PremiumApplicantViewModel,
        config: ComponentConfig,
        lifecycle: LifecycleOwner,
        modifier: Modifier,
        onAction: (Any) -> Unit,
    ) {
        item {
            Log.d("PremiumApplicantComposer", "PremiumApplicantComposer: ${config.id}")
            LaunchedEffect(viewModel) {
                Log.d("JobRecommendationSection", "$viewModel Launched Effect")
            }
            Card(
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                modifier = modifier.padding(vertical = 4.dp),
            ) {
                Column(Modifier.padding(12.dp)) {
                    Text("Item ${config.id}", style = MaterialTheme.typography.headlineSmall)
                    Text("Premium applicant", style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}