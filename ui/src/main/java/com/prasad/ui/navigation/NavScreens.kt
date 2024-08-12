package com.prasad.ui.navigation

sealed class NavScreens(val route: String) {

    data object CartoonListScreen : NavScreens(route = NavRoutes.CARTOON_LIST_SCREEN)
    data object CartoonDetailsScreen : NavScreens(route = NavRoutes.CARTOON_DETAILS_SCREEN)
}