package com.sh4rp3r.lab4mobile.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class CityCategory(
    val id: String,
    @StringRes val titleRes: Int,
    @StringRes val descriptionRes: Int,
    @DrawableRes val iconRes: Int
)
