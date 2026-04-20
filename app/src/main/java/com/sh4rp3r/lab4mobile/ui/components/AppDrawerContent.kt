package com.sh4rp3r.lab4mobile.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.sh4rp3r.lab4mobile.R
import com.sh4rp3r.lab4mobile.data.CityRepository

@Composable
fun AppDrawerContent(
    selectedCategoryId: String?,
    isAboutSelected: Boolean,
    isSettingsSelected: Boolean,
    onCategoryClick: (String) -> Unit,
    onAboutClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_medium))
    ) {
        Text(
            text = stringResource(id = R.string.city_name),
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = stringResource(id = R.string.city_tagline),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_small))
        )
        Text(
            text = stringResource(id = R.string.drawer_categories),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_large))
        )
        CityRepository.categories.forEach { category ->
            NavigationDrawerItem(
                label = { Text(text = stringResource(id = category.titleRes)) },
                selected = selectedCategoryId == category.id,
                onClick = { onCategoryClick(category.id) },
                icon = {
                    Icon(
                        painter = painterResource(id = category.iconRes),
                        contentDescription = stringResource(id = category.titleRes)
                    )
                },
                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_small))
            )
        }
        HorizontalDivider(modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.padding_medium)))
        Text(
            text = stringResource(id = R.string.drawer_info),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary
        )
        NavigationDrawerItem(
            label = { Text(text = stringResource(id = R.string.nav_about)) },
            selected = isAboutSelected,
            onClick = onAboutClick,
            icon = {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_menu_info_details),
                    contentDescription = stringResource(id = R.string.nav_about)
                )
            },
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_small))
        )
        NavigationDrawerItem(
            label = { Text(text = stringResource(id = R.string.nav_settings)) },
            selected = isSettingsSelected,
            onClick = onSettingsClick,
            icon = {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_menu_preferences),
                    contentDescription = stringResource(id = R.string.nav_settings)
                )
            },
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_small))
        )
    }
}
