package com.example.microfeaturecodelab.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.microfeaturecodelab.base.MicroFeatureViewModel
import com.example.microfeaturecodelab.presentation.featureconfig.ComponentConfig
import com.example.microfeaturecodelab.presentation.model.JobItem
import com.example.microfeaturecodelab.presentation.model.RecommendedJobSection
import com.example.microfeaturecodelab.presentation.model.fakeJob
import com.example.microfeaturecodelab.presentation.uimodel.PersonalisedJobViewModel
import com.example.microfeaturecodelab.ui.theme.MicroFeatureCodelabTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MicroFeatureCodelabTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    JobScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun JobScreen(
    modifier: Modifier = Modifier,
    viewModel: JobsViewModel = hiltViewModel()
) {
    val components by remember { mutableStateOf(viewModel.getComponents()) }
    StableWidget(components, viewModel.componentViewModels, modifier = modifier)
}

@Composable
fun StableWidget(
    items: Widgets,
    map: Map<String, MicroFeatureViewModel>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Dynamically generate items for components
        items(
            items = items.items,
            key = { it.id },
            itemContent = {
                val componentViewModel = remember(items) {
                    map[it.id]
                }
                Log.d("MicroFeature", "JobScreen:index ${it.id}")
                ComponentItem(it, componentViewModel)
            }
        )
    }
}

@Composable
fun ComponentItem(
    component: ComponentConfig,
    componentViewModel: MicroFeatureViewModel?
) {
    when (component.type) {
        "personalisedJob" -> {

            JobRecommendation(
                componentViewModel as PersonalisedJobViewModel,
                component.id,
                Modifier.fillMaxWidth()
            )
        }
    }
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
//     val personalisedJob by viewmodel.uiState.collectAsStateWithLifecycle()
//     StateReducer(id, personalisedJob, modifier = modifier)

    // Working fine when uistate is directly consumed
    val personalisedJob by viewmodel.uiState.collectAsStateWithLifecycle()
    Log.d("MicroFeature", "StateReducer: recomposing $id")
    JobRecommendationSection(id, personalisedJob, modifier = modifier)
}

@Composable
fun StateReducer(id: String, state: PersonalisedJobViewModel.UiState, modifier: Modifier = Modifier) {
    Log.d("MicroFeature", "StateReducer: recomposing $id")

    when (state) {
        is PersonalisedJobViewModel.UiState.Loading -> {
            // Loading
        }

        is PersonalisedJobViewModel.UiState.Error -> {
            // Error
        }

        is PersonalisedJobViewModel.UiState.Success -> {
          Text("Item $id ${state.jobSection.sectionTitle}", modifier = Modifier.padding(16.dp))
//            JobRecommendationSection(
//                (personalisedJob as PersonalisedJobViewModel.UiState.Success).jobSection,
//                modifier = modifier
//            ) // No cast required
        }
    }
}

@Composable
fun JobRecommendationSection(
    id: String,
    state: RecommendedJobSection,
    modifier: Modifier = Modifier
) {
    Log.d("JobRecommendationSection", "JobRecommendationSection: ${state.items.size}")
    LaunchedEffect(state) {
        Log.d("JobRecommendationSection", "Launched Effect")
    }
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = modifier
            .padding(32.dp),
    ) {
        Text("Item $id ${state.sectionTitle}")

        state.items.map {
            Text(it.jobTitle)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MicroFeatureCodelabTheme {
        JobScreen()
    }
}