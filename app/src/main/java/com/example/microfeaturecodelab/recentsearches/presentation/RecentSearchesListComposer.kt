package com.example.microfeaturecodelab.recentsearches.presentation

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.microfeaturecodelab.base.AbstractMicroFeatureComposer
import com.example.microfeaturecodelab.personalisedjob.presentation.featureconfig.ComponentConfig
import javax.inject.Inject

class RecentSearchesListComposer @Inject constructor() :
    AbstractMicroFeatureComposer<RecentSearchesViewModel>() {
    override fun LazyListScope.content(
        viewModel: RecentSearchesViewModel,
        config: ComponentConfig,
        modifier: Modifier,
        onAction: (Any) -> Unit
    ) {
        // @TODO ISSUE #2
        // How to use viewmodel.uiState.collectAsStateWithLifecycle() in
        // non composable function LazyListScope.content()
        val jobItems by viewModel.uiState
        items(
            items = jobItems.items,
            key = { it.id },
            itemContent = { data ->
                RecentSearch(
                    recentSearchItem = data,
                    modifier = modifier
                )
            }
        )
    }

    @Composable
    fun RecentSearch(
        recentSearchItem: RecentSearchItem,
        modifier: Modifier = Modifier,
    ) {
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