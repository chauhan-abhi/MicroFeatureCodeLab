package com.example.microfeaturecodelab.personalisedjob.presentation

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
import com.example.microfeaturecodelab.base.AbstractMicroFeatureComposer
import com.example.microfeaturecodelab.base.MicroFeatureViewModel
import com.example.microfeaturecodelab.personalisedjob.presentation.featureconfig.ComponentConfig
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
    ComponentList(
        items = components,
        vmMap = viewModel.componentViewModels,
        composerMap = viewModel.componentComposers,
        modifier = modifier
    )
}

@Composable
fun ComponentList(
    items: Widgets,
    vmMap: Map<String, MicroFeatureViewModel>,
    composerMap: Map<String, AbstractMicroFeatureComposer<out MicroFeatureViewModel>>,
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
                val (componentViewModel, componentComposer) = remember(items) {
                    vmMap[it.id] to composerMap[it.id]
                }
                Log.d("MicroFeature", "ComponentList:index ${it.id}")
                ComponentItem(it, componentViewModel, componentComposer)
            }
        )
    }
}

@Composable
fun ComponentItem(
    component: ComponentConfig,
    componentViewModel: MicroFeatureViewModel?,
    componentComposer: AbstractMicroFeatureComposer<out MicroFeatureViewModel>?
) {
    componentComposer?.ComposerContent(
        config = component,
        viewModel = componentViewModel!!,
        modifier = Modifier.fillMaxWidth(),
        onAction = {}
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MicroFeatureCodelabTheme {
        JobScreen()
    }
}