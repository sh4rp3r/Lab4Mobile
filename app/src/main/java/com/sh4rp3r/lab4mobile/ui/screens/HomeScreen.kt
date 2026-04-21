package com.sh4rp3r.lab4mobile.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.sh4rp3r.lab4mobile.R
import com.sh4rp3r.lab4mobile.data.CityRepository
import com.sh4rp3r.lab4mobile.model.CityCategory
import com.sh4rp3r.lab4mobile.ui.textUnitResource

@Composable
fun HomeScreen(
    onCategoryClick: (String) -> Unit
) {
    val categories = CityRepository.categories

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_large)),
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding_large))
    ) {
        item {
            ResponsiveCategorySection(
                categories = categories,
                onCategoryClick = onCategoryClick
            )
        }
    }
}

@Composable
private fun ResponsiveCategorySection(
    categories: List<CityCategory>,
    onCategoryClick: (String) -> Unit
) {
    BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
        val isWide = maxWidth >= dimensionResource(id = R.dimen.breakpoint_two_columns)
        val chunks = if (isWide) {
            categories.chunked(2)
        } else {
            categories.map { listOf(it) }
        }

        Column(verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))) {
            chunks.forEach { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
                ) {
                    rowItems.forEach { category ->
                        Card(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth(),
                            onClick = { onCategoryClick(category.id) }
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(dimensionResource(id = R.dimen.padding_large)),
                                verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
                            ) {
                                Text(
                                    text = androidx.compose.ui.res.stringResource(id = category.titleRes),
                                    fontSize = textUnitResource(id = R.dimen.text_card_title),
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = androidx.compose.ui.res.stringResource(id = category.descriptionRes),
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                    if (rowItems.size == 1 && isWide) {
                        Column(modifier = Modifier.weight(1f)) {}
                    }
                }
            }
        }
    }
}
