package com.sh4rp3r.lab4mobile.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.sh4rp3r.lab4mobile.R
import com.sh4rp3r.lab4mobile.data.CULTURE_CATEGORY_ID
import com.sh4rp3r.lab4mobile.data.PARKS_CATEGORY_ID
import com.sh4rp3r.lab4mobile.data.SHOPPING_CATEGORY_ID
import com.sh4rp3r.lab4mobile.navigation.CULTURE_ROUTE
import com.sh4rp3r.lab4mobile.navigation.HOME_ROUTE
import com.sh4rp3r.lab4mobile.navigation.PARKS_ROUTE
import com.sh4rp3r.lab4mobile.navigation.SHOPPING_ROUTE

enum class BottomDestination(
    val route: String,
    val categoryId: String?,
    @StringRes val titleRes: Int,
    @DrawableRes val iconRes: Int
) {
    HOME(
        route = HOME_ROUTE,
        categoryId = null,
        titleRes = R.string.nav_home,
        iconRes = android.R.drawable.ic_menu_view
    ),
    CULTURE(
        route = CULTURE_ROUTE,
        categoryId = CULTURE_CATEGORY_ID,
        titleRes = R.string.category_culture,
        iconRes = android.R.drawable.ic_menu_gallery
    ),
    PARKS(
        route = PARKS_ROUTE,
        categoryId = PARKS_CATEGORY_ID,
        titleRes = R.string.category_parks,
        iconRes = android.R.drawable.ic_menu_compass
    ),
    SHOPPING(
        route = SHOPPING_ROUTE,
        categoryId = SHOPPING_CATEGORY_ID,
        titleRes = R.string.category_shopping,
        iconRes = android.R.drawable.ic_menu_agenda
    )
}
