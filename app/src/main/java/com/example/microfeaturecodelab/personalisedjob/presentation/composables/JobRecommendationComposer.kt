package com.example.microfeaturecodelab.personalisedjob.presentation.composables

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.microfeaturecodelab.base.AbstractMicroFeatureComposer
import com.example.microfeaturecodelab.personalisedjob.presentation.featureconfig.ComponentConfig
import com.example.microfeaturecodelab.personalisedjob.presentation.model.RecommendedJobSection
import com.example.microfeaturecodelab.personalisedjob.presentation.uimodel.PersonalisedJobViewModel
import javax.inject.Inject

class JobRecommendationComposer @Inject constructor() :
    AbstractMicroFeatureComposer<PersonalisedJobViewModel>() {
    @Composable
    override fun Content(
        viewModel: PersonalisedJobViewModel,
        config: ComponentConfig,
        modifier: Modifier,
        onAction: (Any) -> Unit
    ) {
        JobRecommendation(viewmodel = viewModel, id = config.id, modifier = modifier)
    }

    @Composable
    fun JobRecommendation(
        viewmodel: PersonalisedJobViewModel,
        id: String,
        modifier: Modifier = Modifier
    ) {
        Log.d("MicroFeature", "JobRecommendationComposer: Recomposing ${viewmodel.hashCode()}")
        // This collect triggers the flow in the viewmodel to fetch data from use case
        // @TODO Currently its being called for all 20 items in lazy column which is inefficient

        // Uncomment this to see the issue, with sealed state
//        val personalisedJob by viewmodel.uiState.collectAsStateWithLifecycle()
//        StateReducer(id, personalisedJob, modifier = modifier)

        // Working fine when uistate is directly consumed
        val personalisedJob by viewmodel.uiState.collectAsStateWithLifecycle()
        Log.d("MicroFeature", "StateReducer: recomposing $id")
        JobRecommendationSection(id, personalisedJob, modifier = modifier)
    }

    @Composable
    fun JobRecommendationSection(
        id: String,
        state: RecommendedJobSection,
        modifier: Modifier = Modifier
    ) {
        Log.d("JobRecommendationSection", "JobRecommendationSection: ${state.items.size}")
        LaunchedEffect(state) {
            Log.d("JobRecommendationSection", "$id Launched Effect")
        }
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            modifier = modifier
                .padding(32.dp),
        ) {
            Text("Item $id")
            Text(state.sectionTitle)

            state.items.map {
                Text(it.jobTitle)
            }
        }
    }

}