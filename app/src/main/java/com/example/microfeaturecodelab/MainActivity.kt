package com.example.microfeaturecodelab

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.microfeaturecodelab.personalisedjob.presentation.FeatureScreenViewModel
import com.example.microfeaturecodelab.personalisedjob.presentation.Widgets
import com.example.microfeaturecodelab.personalisedjob.presentation.featureconfig.ComponentConfig
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
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
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
    val components by remember { mutableStateOf(viewModel.getComponents()) }
    ComponentList(
        items = components,
        componentDependencyMap = viewModel.componentDependencyMap,
        modifier = modifier
    )
}

@Composable
fun ComponentList(
    items: Widgets,
    componentDependencyMap: Map<String, ComponentDependencies>,
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
            itemContent = { component ->
                val componentDependency = remember(items) {
                    componentDependencyMap[component.id]
                }
                Log.d("MicroFeature", "ComponentList:index ${component.id}")
                componentDependency?.let {
                    ComponentItem(component, componentDependency)
                }
            }
        )
    }
}

@Composable
fun ComponentItem(
    component: ComponentConfig,
    componentDependency: ComponentDependencies,
) {
    Log.d("MicroFeature", "ComponentItem: ${component.id} vm:${componentDependency.componentVM}")
    componentDependency.componentComposer.ComposerContent(
        viewModel = componentDependency.componentVM,
        config = component,
        modifier = Modifier.fillMaxWidth(),
        onAction = {}
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MicroFeatureCodelabTheme {
        SelfDrivenSingleUiComponentsScreen()
    }
}