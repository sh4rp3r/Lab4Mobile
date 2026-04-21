package com.sh4rp3r.lab4mobile.ui.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.sh4rp3r.lab4mobile.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityTopBar(
    title: String,
    showMenuIcon: Boolean,
    showBackIcon: Boolean,
    onMenuClick: () -> Unit,
    onBackClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            when {
                showBackIcon -> {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            painter = painterResource(id = android.R.drawable.ic_media_previous),
                            contentDescription = stringResource(id = R.string.action_back)
                        )
                    }
                }

                showMenuIcon -> {
                    IconButton(onClick = onMenuClick) {
                        Icon(
                            painter = painterResource(id = android.R.drawable.ic_menu_sort_by_size),
                            contentDescription = stringResource(id = R.string.action_open_menu)
                        )
                    }
                }
            }
        }
    )
}
