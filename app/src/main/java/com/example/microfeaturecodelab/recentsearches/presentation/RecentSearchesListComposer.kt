package com.example.microfeaturecodelab.recentsearches.presentation

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.microfeaturecodelab.base.AbstractMicroFeatureComposer
import com.example.microfeaturecodelab.personalisedjob.presentation.featureconfig.ComponentConfig
import kotlinx.coroutines.launch
import javax.inject.Inject

class RecentSearchesListComposer @Inject constructor() :
    AbstractMicroFeatureComposer<RecentSearchesViewModel>() {
    override fun LazyListScope.content(
        viewModel: RecentSearchesViewModel,
        config: ComponentConfig,
        lifecycle: LifecycleOwner,
        modifier: Modifier,
        onAction: (Any) -> Unit,
    ) {
        // Since we cannot use viewmodel.uiState.collectAsStateWithLifecycle() in
        // non composable function LazyListScope.content()
        lifecycle.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { data ->
                    items(
                        items = data.items,
                        key = { it.id },
                        itemContent = { recentSearches ->
                            RecentSearch(
                                recentSearchItem = recentSearches,
                                modifier = modifier
                            )
                        }
                    )
                }
            }
        }
    }

    @Composable
    fun RecentSearch(
        recentSearchItem: RecentSearchItem,
        modifier: Modifier = Modifier,
    ) {
        LaunchedEffect(recentSearchItem) {
            Log.d("RecentSearchesListComposer", "Launched effect: ${recentSearchItem.id}")
        }
        DisposableEffect(recentSearchItem) {
            onDispose {
                Log.d("RecentSearchesListComposer", "onDispose: ${recentSearchItem.id}")
            }
        }
        Card(
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
            modifier = modifier
                .padding(vertical = 4.dp),
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Text(
                    text = recentSearchItem.positionName,
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(text = recentSearchItem.location, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}