package com.sh4rp3r.lab4mobile.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.sh4rp3r.lab4mobile.R
import com.sh4rp3r.lab4mobile.data.CityRepository
import com.sh4rp3r.lab4mobile.ui.textUnitResource

@Composable
fun RecommendationDetailScreen(
    categoryId: String,
    recommendationId: String
) {
    val recommendation = CityRepository.getRecommendation(categoryId, recommendationId) ?: return

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_large)),
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding_large))
    ) {
        item {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = dimensionResource(id = R.dimen.padding_large)),
                    verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
                ) {
                    Image(
                        painter = painterResource(id = recommendation.imageRes),
                        contentDescription = stringResource(id = recommendation.titleRes),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(dimensionResource(id = R.dimen.detail_image_height))
                    )
                    Text(
                        text = stringResource(id = recommendation.titleRes),
                        fontSize = textUnitResource(id = R.dimen.text_hero_title),
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_large))
                    )
                    Text(
                        text = stringResource(id = recommendation.descriptionRes),
                        fontSize = textUnitResource(id = R.dimen.text_body),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_large))
                    )
                }
            }
        }
        item {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(id = R.dimen.padding_large)),
                    verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
                ) {
                    Text(
                        text = stringResource(id = R.string.detail_about_title),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = stringResource(id = recommendation.detailRes),
                        fontSize = textUnitResource(id = R.dimen.text_body),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}
