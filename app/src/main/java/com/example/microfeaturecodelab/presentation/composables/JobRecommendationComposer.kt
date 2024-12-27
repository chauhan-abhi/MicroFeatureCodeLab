package com.example.microfeaturecodelab.presentation.composables

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.microfeaturecodelab.base.AbstractMicroFeatureComposer
import com.example.microfeaturecodelab.presentation.JobRecommendationSection
import com.example.microfeaturecodelab.presentation.StateReducer
import com.example.microfeaturecodelab.presentation.uimodel.PersonalisedJobViewModel
import javax.inject.Inject

class JobRecommendationComposer @Inject constructor(): AbstractMicroFeatureComposer<PersonalisedJobViewModel>() {
    @Composable
    override fun Content(
        viewModel: PersonalisedJobViewModel,
        modifier: Modifier,
        onAction: (Any) -> Unit
    ) {
        JobRecommendation(viewmodel = viewModel, id = "1", modifier = modifier)
    }

    @Composable
    fun JobRecommendation(
        viewmodel: PersonalisedJobViewModel,
        id: String,
        modifier: Modifier = Modifier
    ) {
        Log.d("MicroFeature", "JobRecommendation: Recomposing Before collect item Id: $id")

        // This collect triggers the flow in the viewmodel to fetch data from use case
        // @TODO Currently its being called for all 20 items in lazy column which is inefficient

        // Uncomment this to see the issue, with sealed state
//        val personalisedJob by viewmodel.uiState.collectAsStateWithLifecycle()
//        StateReducer(id, personalisedJob, modifier = modifier)

        // Working fine when uistate is directly consumed
        val personalisedJob by viewmodel.uiState.collectAsStateWithLifecycle()
        Log.d("MicroFeature", "StateReducer: recomposing $id")
        JobRecommendationSection(id , personalisedJob, modifier = modifier)
    }
}