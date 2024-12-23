package com.example.microfeaturecodelab.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
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
    Column(modifier.fillMaxSize()) {
        Text("Job Screen 1", Modifier.align(Alignment.CenterHorizontally))
        Text("Job Widget 2", Modifier.align(Alignment.CenterHorizontally))
        JobRecommendation(viewModel.feature, Modifier.align(Alignment.CenterHorizontally))
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
        modifier = modifier.fillMaxWidth(),
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