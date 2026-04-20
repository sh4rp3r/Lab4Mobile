package com.sh4rp3r.lab4mobile.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sh4rp3r.lab4mobile.R
import com.sh4rp3r.lab4mobile.data.CULTURE_CATEGORY_ID
import com.sh4rp3r.lab4mobile.data.CityRepository
import com.sh4rp3r.lab4mobile.data.PARKS_CATEGORY_ID
import com.sh4rp3r.lab4mobile.data.SHOPPING_CATEGORY_ID
import com.sh4rp3r.lab4mobile.model.BottomDestination
import com.sh4rp3r.lab4mobile.navigation.ABOUT_ROUTE
import com.sh4rp3r.lab4mobile.navigation.CATEGORY_ID_ARG
import com.sh4rp3r.lab4mobile.navigation.CATEGORY_ROUTE
import com.sh4rp3r.lab4mobile.navigation.CULTURE_ROUTE
import com.sh4rp3r.lab4mobile.navigation.DETAIL_ROUTE
import com.sh4rp3r.lab4mobile.navigation.HOME_ROUTE
import com.sh4rp3r.lab4mobile.navigation.PARKS_ROUTE
import com.sh4rp3r.lab4mobile.navigation.RECOMMENDATION_ID_ARG
import com.sh4rp3r.lab4mobile.navigation.SETTINGS_ROUTE
import com.sh4rp3r.lab4mobile.navigation.SHOPPING_ROUTE
import com.sh4rp3r.lab4mobile.navigation.categoryRoute
import com.sh4rp3r.lab4mobile.navigation.detailRoute
import com.sh4rp3r.lab4mobile.ui.components.AppDrawerContent
import com.sh4rp3r.lab4mobile.ui.components.CityTopBar
import com.sh4rp3r.lab4mobile.ui.screens.AboutScreen
import com.sh4rp3r.lab4mobile.ui.screens.CategoryScreen
import com.sh4rp3r.lab4mobile.ui.screens.HomeScreen
import com.sh4rp3r.lab4mobile.ui.screens.RecommendationDetailScreen
import com.sh4rp3r.lab4mobile.ui.screens.SettingsScreen

@Composable
fun CityApp(
    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit
) {
    val navController = rememberNavController()
    var savedBottomRoute by rememberSaveable { mutableStateOf(BottomDestination.HOME.route) }
    var isDrawerOpen by rememberSaveable { mutableStateOf(false) }
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    val currentCategoryId = resolveCurrentCategoryId(
        route = currentRoute,
        categoryId = backStackEntry?.arguments?.getString(CATEGORY_ID_ARG)
    )
    val currentRecommendationId = backStackEntry?.arguments?.getString(RECOMMENDATION_ID_ARG)
    val drawerState = rememberDrawerState(
        initialValue = if (isDrawerOpen) DrawerValue.Open else DrawerValue.Closed,
        confirmStateChange = { value ->
            isDrawerOpen = value == DrawerValue.Open
            true
        }
    )
    val screenTitle = screenTitle(
        route = currentRoute,
        categoryId = currentCategoryId,
        recommendationId = currentRecommendationId
    )

    LaunchedEffect(currentRoute, currentCategoryId) {
        resolveBottomRoute(currentRoute, currentCategoryId)?.let { bottomRoute ->
            savedBottomRoute = bottomRoute
        }
    }

    AdaptiveDrawer(
        isDrawerOpen = isDrawerOpen,
        drawerState = drawerState,
        currentCategoryId = currentCategoryId,
        isAboutSelected = currentRoute == ABOUT_ROUTE,
        isSettingsSelected = currentRoute == SETTINGS_ROUTE,
        onCategoryClick = { categoryId ->
            isDrawerOpen = false
            savedBottomRoute = topLevelRoute(categoryId)
            navController.navigate(topLevelRoute(categoryId)) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        },
        onAboutClick = {
            isDrawerOpen = false
            navController.navigate(ABOUT_ROUTE) {
                launchSingleTop = true
            }
        },
        onSettingsClick = {
            isDrawerOpen = false
            navController.navigate(SETTINGS_ROUTE) {
                launchSingleTop = true
            }
        }
    ) { isPermanentDrawer ->
        LaunchedEffect(isDrawerOpen, isPermanentDrawer) {
            if (!isPermanentDrawer) {
                if (isDrawerOpen) {
                    drawerState.open()
                } else {
                    drawerState.close()
                }
            }
        }

        Scaffold(
            topBar = {
                CityTopBar(
                    title = screenTitle,
                    showMenuIcon = !isPermanentDrawer && currentRoute != DETAIL_ROUTE,
                    showBackIcon = currentRoute == DETAIL_ROUTE,
                    onMenuClick = { isDrawerOpen = true },
                    onBackClick = { navController.navigateUp() }
                )
            },
            bottomBar = {
                BottomAppBar(containerColor = MaterialTheme.colorScheme.surface) {
                    BottomDestination.entries.forEach { destination ->
                        NavigationBarItem(
                            selected = savedBottomRoute == destination.route,
                            onClick = {
                                savedBottomRoute = destination.route
                                navController.navigate(destination.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                Icon(
                                    painter = painterResource(id = destination.iconRes),
                                    contentDescription = stringResource(id = destination.titleRes)
                                )
                            },
                            label = {
                                Text(text = stringResource(id = destination.titleRes))
                            }
                        )
                    }
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = HOME_ROUTE,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                composable(route = HOME_ROUTE) {
                    HomeScreen(
                        onCategoryClick = { categoryId ->
                            savedBottomRoute = topLevelRoute(categoryId)
                            navController.navigate(topLevelRoute(categoryId)) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
                composable(route = CULTURE_ROUTE) {
                    CategoryScreen(
                        categoryId = CULTURE_CATEGORY_ID,
                        onRecommendationClick = { categoryId, recommendationId ->
                            navController.navigate(detailRoute(categoryId, recommendationId))
                        }
                    )
                }
                composable(route = PARKS_ROUTE) {
                    CategoryScreen(
                        categoryId = PARKS_CATEGORY_ID,
                        onRecommendationClick = { categoryId, recommendationId ->
                            navController.navigate(detailRoute(categoryId, recommendationId))
                        }
                    )
                }
                composable(route = SHOPPING_ROUTE) {
                    CategoryScreen(
                        categoryId = SHOPPING_CATEGORY_ID,
                        onRecommendationClick = { categoryId, recommendationId ->
                            navController.navigate(detailRoute(categoryId, recommendationId))
                        }
                    )
                }
                composable(
                    route = CATEGORY_ROUTE,
                    arguments = listOf(
                        navArgument(CATEGORY_ID_ARG) { type = NavType.StringType }
                    )
                ) { entry ->
                    CategoryScreen(
                        categoryId = entry.arguments?.getString(CATEGORY_ID_ARG).orEmpty(),
                        onRecommendationClick = { categoryId, recommendationId ->
                            navController.navigate(detailRoute(categoryId, recommendationId))
                        }
                    )
                }
                composable(route = ABOUT_ROUTE) {
                    AboutScreen()
                }
                composable(route = SETTINGS_ROUTE) {
                    SettingsScreen(
                        isDarkTheme = isDarkTheme,
                        onThemeChange = onThemeChange
                    )
                }
                composable(
                    route = DETAIL_ROUTE,
                    arguments = listOf(
                        navArgument(CATEGORY_ID_ARG) { type = NavType.StringType },
                        navArgument(RECOMMENDATION_ID_ARG) { type = NavType.StringType }
                    )
                ) { entry ->
                    RecommendationDetailScreen(
                        categoryId = entry.arguments?.getString(CATEGORY_ID_ARG).orEmpty(),
                        recommendationId = entry.arguments?.getString(RECOMMENDATION_ID_ARG).orEmpty()
                    )
                }
            }
        }
    }
}

@Composable
private fun AdaptiveDrawer(
    isDrawerOpen: Boolean,
    drawerState: androidx.compose.material3.DrawerState,
    currentCategoryId: String?,
    isAboutSelected: Boolean,
    isSettingsSelected: Boolean,
    onCategoryClick: (String) -> Unit,
    onAboutClick: () -> Unit,
    onSettingsClick: () -> Unit,
    content: @Composable (Boolean) -> Unit
) {
    androidx.compose.foundation.layout.BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val isPermanentDrawer = maxWidth >= dimensionResource(id = R.dimen.breakpoint_drawer)
        val drawerContent: @Composable () -> Unit = {
            AppDrawerContent(
                selectedCategoryId = currentCategoryId,
                isAboutSelected = isAboutSelected,
                isSettingsSelected = isSettingsSelected,
                onCategoryClick = onCategoryClick,
                onAboutClick = onAboutClick,
                onSettingsClick = onSettingsClick
            )
        }

        if (isPermanentDrawer) {
            PermanentNavigationDrawer(
                drawerContent = {
                    PermanentDrawerSheet(
                        modifier = Modifier.width(dimensionResource(id = R.dimen.drawer_width))
                    ) {
                        drawerContent()
                    }
                }
            ) {
                content(true)
            }
        } else {
            ModalNavigationDrawer(
                drawerState = drawerState,
                gesturesEnabled = isDrawerOpen,
                drawerContent = {
                    ModalDrawerSheet(
                        modifier = Modifier.width(dimensionResource(id = R.dimen.drawer_width))
                    ) {
                        drawerContent()
                    }
                }
            ) {
                content(false)
            }
        }
    }
}

@Composable
private fun screenTitle(
    route: String?,
    categoryId: String?,
    recommendationId: String?
): String {
    return when (route) {
        HOME_ROUTE -> stringResource(id = R.string.app_name)
        CULTURE_ROUTE -> stringResource(id = R.string.category_culture)
        PARKS_ROUTE -> stringResource(id = R.string.category_parks)
        SHOPPING_ROUTE -> stringResource(id = R.string.category_shopping)
        CATEGORY_ROUTE -> {
            val category = CityRepository.getCategory(categoryId.orEmpty())
            stringResource(id = category?.titleRes ?: R.string.app_name)
        }

        ABOUT_ROUTE -> stringResource(id = R.string.nav_about)
        SETTINGS_ROUTE -> stringResource(id = R.string.nav_settings)
        DETAIL_ROUTE -> {
            val recommendation = CityRepository.getRecommendation(
                categoryId = categoryId.orEmpty(),
                recommendationId = recommendationId.orEmpty()
            )
            stringResource(id = recommendation?.titleRes ?: R.string.app_name)
        }

        else -> stringResource(id = R.string.app_name)
    }
}

private fun resolveBottomRoute(route: String?, categoryId: String?): String? {
    return when {
        route == HOME_ROUTE -> BottomDestination.HOME.route
        route == CULTURE_ROUTE -> BottomDestination.CULTURE.route
        route == PARKS_ROUTE -> BottomDestination.PARKS.route
        route == SHOPPING_ROUTE -> BottomDestination.SHOPPING.route
        categoryId == BottomDestination.CULTURE.categoryId -> BottomDestination.CULTURE.route
        categoryId == BottomDestination.PARKS.categoryId -> BottomDestination.PARKS.route
        categoryId == BottomDestination.SHOPPING.categoryId -> BottomDestination.SHOPPING.route
        else -> null
    }
}

private fun resolveCurrentCategoryId(route: String?, categoryId: String?): String? {
    return when (route) {
        CULTURE_ROUTE -> CULTURE_CATEGORY_ID
        PARKS_ROUTE -> PARKS_CATEGORY_ID
        SHOPPING_ROUTE -> SHOPPING_CATEGORY_ID
        else -> categoryId
    }
}

private fun topLevelRoute(categoryId: String): String {
    return when (categoryId) {
        CULTURE_CATEGORY_ID -> CULTURE_ROUTE
        PARKS_CATEGORY_ID -> PARKS_ROUTE
        SHOPPING_CATEGORY_ID -> SHOPPING_ROUTE
        else -> categoryRoute(categoryId)
    }
}
