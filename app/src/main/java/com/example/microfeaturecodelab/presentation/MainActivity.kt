package com.example.microfeaturecodelab.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.microfeaturecodelab.presentation.model.RecommendedJobSection
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
    viewModel.hashCode()
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState(0)),
    ) {
        Text("Job Screen 1", Modifier.align(Alignment.CenterHorizontally))
        Text("Job Widget 2", Modifier.align(Alignment.CenterHorizontally))
        viewModel.getComponents().map { component ->
            val componentViewModel = viewModel.componentViewModels[component.id]
            Log.d("MicroFeature", "JobScreen: ${componentViewModel.hashCode()}")

            when (component.type) {
                "personalisedJob" -> {
                    JobRecommendation(
                        componentViewModel as PersonalisedJobViewModel,
                        Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
            Spacer(Modifier.padding(16.dp))
        }
        // Filter
        // Job picks for you
        // Enable Job updates
        // Recent Searches
        // Explore company carousel
        // Job list type1
        // Discover linkedin
        // More job paginated list
    }
}

@Composable
fun JobRecommendation(
    viewmodel: PersonalisedJobViewModel,
    modifier: Modifier = Modifier
) {
    val personalisedJob by viewmodel.uiState.collectAsStateWithLifecycle()
    Log.d("MicroFeature", "JobScreen: $personalisedJob")

    when (personalisedJob) {
        is PersonalisedJobViewModel.UiState.Loading -> {
            // Loading
        }

        is PersonalisedJobViewModel.UiState.Error -> {
            // Error
        }

        is PersonalisedJobViewModel.UiState.Success -> {
            JobRecommendationSection(
                (personalisedJob as PersonalisedJobViewModel.UiState.Success).jobSection,
                modifier = modifier
            ) // No cast required
        }
    }
}

@Composable
fun JobRecommendationSection(
    state: RecommendedJobSection,
    modifier: Modifier = Modifier
) {
    Log.d("JobRecommendationSection", "JobRecommendationSection: ${state.items.size}")
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = modifier
            .padding(32.dp),
    ) {
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