package com.sh4rp3r.lab4mobile.data

import com.sh4rp3r.lab4mobile.R
import com.sh4rp3r.lab4mobile.model.CityCategory
import com.sh4rp3r.lab4mobile.model.CityRecommendation

object CityRepository {
    val categories = listOf(
        CityCategory(
            id = CULTURE_CATEGORY_ID,
            titleRes = R.string.category_culture,
            descriptionRes = R.string.category_culture_description,
            iconRes = android.R.drawable.ic_menu_gallery
        ),
        CityCategory(
            id = PARKS_CATEGORY_ID,
            titleRes = R.string.category_parks,
            descriptionRes = R.string.category_parks_description,
            iconRes = android.R.drawable.ic_menu_compass
        ),
        CityCategory(
            id = SHOPPING_CATEGORY_ID,
            titleRes = R.string.category_shopping,
            descriptionRes = R.string.category_shopping_description,
            iconRes = android.R.drawable.ic_menu_agenda
        )
    )

    val recommendations = listOf(
        CityRecommendation(
            id = "kalashnikov_museum",
            categoryId = CULTURE_CATEGORY_ID,
            titleRes = R.string.culture_1_title,
            descriptionRes = R.string.culture_1_description,
            detailRes = R.string.culture_1_detail,
            imageRes = R.drawable.kalashnikov_museum
        ),
        CityRecommendation(
            id = "national_museum",
            categoryId = CULTURE_CATEGORY_ID,
            titleRes = R.string.culture_2_title,
            descriptionRes = R.string.culture_2_description,
            detailRes = R.string.culture_2_detail,
            imageRes = R.drawable.national_museum
        ),
        CityRecommendation(
            id = "fine_arts_museum",
            categoryId = CULTURE_CATEGORY_ID,
            titleRes = R.string.culture_3_title,
            descriptionRes = R.string.culture_3_description,
            detailRes = R.string.culture_3_detail,
            imageRes = R.drawable.fine_arts_museum
        ),
        CityRecommendation(
            id = "drama_theatre",
            categoryId = CULTURE_CATEGORY_ID,
            titleRes = R.string.culture_4_title,
            descriptionRes = R.string.culture_4_description,
            detailRes = R.string.culture_4_detail,
            imageRes = R.drawable.drama_theatre
        ),
        CityRecommendation(
            id = "opera_theatre",
            categoryId = CULTURE_CATEGORY_ID,
            titleRes = R.string.culture_5_title,
            descriptionRes = R.string.culture_5_description,
            detailRes = R.string.culture_5_detail,
            imageRes = R.drawable.opera_theatre
        ),
        CityRecommendation(
            id = "dragunov_park",
            categoryId = PARKS_CATEGORY_ID,
            titleRes = R.string.parks_1_title,
            descriptionRes = R.string.parks_1_description,
            detailRes = R.string.parks_1_detail,
            imageRes = R.drawable.dragunov_park
        ),
        CityRecommendation(
            id = "kirov_park",
            categoryId = PARKS_CATEGORY_ID,
            titleRes = R.string.parks_2_title,
            descriptionRes = R.string.parks_2_description,
            detailRes = R.string.parks_2_detail,
            imageRes = R.drawable.kirov_park
        ),
        CityRecommendation(
            id = "summer_garden",
            categoryId = PARKS_CATEGORY_ID,
            titleRes = R.string.parks_3_title,
            descriptionRes = R.string.parks_3_description,
            detailRes = R.string.parks_3_detail,
            imageRes = R.drawable.summer_garden
        ),
        CityRecommendation(
            id = "tishino_park",
            categoryId = PARKS_CATEGORY_ID,
            titleRes = R.string.parks_4_title,
            descriptionRes = R.string.parks_4_description,
            detailRes = R.string.parks_4_detail,
            imageRes = R.drawable.tishino_park
        ),
        CityRecommendation(
            id = "cosmonauts_park",
            categoryId = PARKS_CATEGORY_ID,
            titleRes = R.string.parks_5_title,
            descriptionRes = R.string.parks_5_description,
            detailRes = R.string.parks_5_detail,
            imageRes = R.drawable.cosmonauts_park
        ),
        CityRecommendation(
            id = "talisman",
            categoryId = SHOPPING_CATEGORY_ID,
            titleRes = R.string.shopping_1_title,
            descriptionRes = R.string.shopping_1_description,
            detailRes = R.string.shopping_1_detail,
            imageRes = R.drawable.talisman
        ),
        CityRecommendation(
            id = "petrovsky",
            categoryId = SHOPPING_CATEGORY_ID,
            titleRes = R.string.shopping_2_title,
            descriptionRes = R.string.shopping_2_description,
            detailRes = R.string.shopping_2_detail,
            imageRes = R.drawable.petrovsky
        ),
        CityRecommendation(
            id = "flagman",
            categoryId = SHOPPING_CATEGORY_ID,
            titleRes = R.string.shopping_3_title,
            descriptionRes = R.string.shopping_3_description,
            detailRes = R.string.shopping_3_detail,
            imageRes = R.drawable.flagman
        ),
        CityRecommendation(
            id = "aksion",
            categoryId = SHOPPING_CATEGORY_ID,
            titleRes = R.string.shopping_4_title,
            descriptionRes = R.string.shopping_4_description,
            detailRes = R.string.shopping_4_detail,
            imageRes = R.drawable.aksion
        ),
        CityRecommendation(
            id = "matrix",
            categoryId = SHOPPING_CATEGORY_ID,
            titleRes = R.string.shopping_5_title,
            descriptionRes = R.string.shopping_5_description,
            detailRes = R.string.shopping_5_detail,
            imageRes = R.drawable.matrix
        )
    )

    fun getCategory(categoryId: String): CityCategory? {
        return categories.firstOrNull { it.id == categoryId }
    }

    fun getRecommendationsByCategory(categoryId: String): List<CityRecommendation> {
        return recommendations.filter { it.categoryId == categoryId }
    }

    fun getRecommendation(categoryId: String, recommendationId: String): CityRecommendation? {
        return recommendations.firstOrNull {
            it.categoryId == categoryId && it.id == recommendationId
        }
    }
}
