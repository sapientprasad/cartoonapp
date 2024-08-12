package com.prasad.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.prasad.ui.R
import com.prasad.ui.base.BaseScreen
import com.prasad.ui.features.detailsscreens.CartoonDetailsScreen
import com.prasad.ui.features.listscreen.CartoonListScreen

@Composable
fun NavGraph(navHostController: NavHostController) {

    NavHost(
        navController = navHostController,
        startDestination = NavScreens.CartoonListScreen.route
    ) {
        composable(route = NavScreens.CartoonListScreen.route) {
            BaseScreen(title = stringResource(id = R.string.cartoon_list_screen)) {
                CartoonListScreen() {
                    navHostController.navigate("${NavScreens.CartoonListScreen.route}/$it")
                }
            }
        }

        composable(
            route = "${NavScreens.CartoonListScreen.route}/{${NavRoutes.CARTOON_ID}}",
            arguments = listOf(navArgument(name = NavRoutes.CARTOON_ID)
            {
                type = NavType.IntType
            })
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.getInt(NavRoutes.CARTOON_ID)?.let {
                BaseScreen(title = stringResource(id = R.string.cartoon_details_screen)) {
                    CartoonDetailsScreen()
                }
            }
        }
    }
}