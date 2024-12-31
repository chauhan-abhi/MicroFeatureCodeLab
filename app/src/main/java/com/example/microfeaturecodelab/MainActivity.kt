package com.example.microfeaturecodelab

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.microfeaturecodelab.personalisedjob.presentation.featureconfig.ComponentDependencies
import com.example.microfeaturecodelab.ui.theme.MicroFeatureCodelabTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MicroFeatureCodelabTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    SelfDrivenSingleUiComponentsScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

/*
* This screen is responsible for rendering the components in the list
* while fetching their own data individually and rendering them in a LazyList
 */
@Composable
fun SelfDrivenSingleUiComponentsScreen(
    modifier: Modifier = Modifier,
    viewModel: FeatureScreenViewModel = hiltViewModel()
) {
    val components by viewModel.state.collectAsStateWithLifecycle()
    ComponentList(
        widgets = components,
        componentDependencyMap = viewModel.componentDependencyMap,
        modifier = modifier
    )
}

@Composable
fun ComponentList(
    widgets: Widgets,
    componentDependencyMap: Map<String, ComponentDependencies>,
    modifier: Modifier = Modifier
) {
    if (widgets.isLoading) {
        // Show loading state
        LoadingIndicator(modifier = modifier)
        return
    }
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Dynamically generate items for components
        widgets.items.forEach { component ->
            val componentDependency = componentDependencyMap[component.id]
            Log.d("MicroFeature", "ComponentList:index ${component.id}")
            componentDependency?.let {
                componentDependency.componentComposer.renderItem(
                    viewModel = componentDependency.componentVM,
                    config = component,
                    scope = this,
                    modifier = Modifier.fillMaxWidth(),
                    onAction = {}
                )
            }
        }
    }
}

@Composable
fun LoadingIndicator(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize(), // Fills the entire screen
        contentAlignment = Alignment.Center // Centers the content
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(80.dp), // Size of the loader
            color = MaterialTheme.colorScheme.tertiary // Loader color
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MicroFeatureCodelabTheme {
        SelfDrivenSingleUiComponentsScreen()
    }
}