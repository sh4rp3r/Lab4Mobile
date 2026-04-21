package com.sh4rp3r.lab4mobile.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class CityRecommendation(
    val id: String,
    val categoryId: String,
    @StringRes val titleRes: Int,
    @StringRes val descriptionRes: Int,
    @StringRes val detailRes: Int,
    @DrawableRes val imageRes: Int
)
