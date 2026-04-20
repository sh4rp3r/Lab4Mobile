package com.sh4rp3r.lab4mobile.navigation

const val CATEGORY_ID_ARG = "categoryId"
const val RECOMMENDATION_ID_ARG = "recommendationId"

const val HOME_ROUTE = "home"
const val CULTURE_ROUTE = "culture"
const val PARKS_ROUTE = "parks"
const val SHOPPING_ROUTE = "shopping"
const val CATEGORY_ROUTE = "category/{$CATEGORY_ID_ARG}"
const val ABOUT_ROUTE = "about"
const val SETTINGS_ROUTE = "settings"
const val DETAIL_ROUTE = "detail/{$CATEGORY_ID_ARG}/{$RECOMMENDATION_ID_ARG}"

fun categoryRoute(categoryId: String): String = "category/$categoryId"

fun detailRoute(categoryId: String, recommendationId: String): String {
    return "detail/$categoryId/$recommendationId"
}
