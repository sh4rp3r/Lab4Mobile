package com.sh4rp3r.lab4mobile.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.sh4rp3r.lab4mobile.R
import com.sh4rp3r.lab4mobile.data.CityRepository
import com.sh4rp3r.lab4mobile.ui.components.RecommendationCard
import com.sh4rp3r.lab4mobile.ui.textUnitResource

@Composable
fun CategoryScreen(
    categoryId: String,
    onRecommendationClick: (String, String) -> Unit
) {
    val category = CityRepository.getCategory(categoryId) ?: return
    val recommendations = CityRepository.getRecommendationsByCategory(categoryId)

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium)),
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding_large))
    ) {
        item {
            Card(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(id = category.descriptionRes),
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large)),
                    fontSize = textUnitResource(id = R.dimen.text_body),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
        items(recommendations) { recommendation ->
            RecommendationCard(
                recommendation = recommendation,
                onClick = {
                    onRecommendationClick(categoryId, recommendation.id)
                }
            )
        }
    }
}
