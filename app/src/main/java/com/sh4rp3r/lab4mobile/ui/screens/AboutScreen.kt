package com.sh4rp3r.lab4mobile.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.res.stringResource
import com.sh4rp3r.lab4mobile.R
import com.sh4rp3r.lab4mobile.ui.textUnitResource

@Composable
fun AboutScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium)),
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding_large))
    ) {
        item {
            Card(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(id = R.string.about_summary),
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large)),
                    fontSize = textUnitResource(id = R.dimen.text_body),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
