package com.nmbs.husqvarna.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nmbs.husqvarna.ui.Destinations.HOME_ROUTE
import com.nmbs.husqvarna.ui.Destinations.MOVIE_ROUTE
import com.nmbs.husqvarna.ui.home.HomeRoute
import com.nmbs.husqvarna.ui.details.DetailsRoute
@Preview
@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = HOME_ROUTE.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(
            route = HOME_ROUTE.route,
            arguments = HOME_ROUTE.arguments
        ) {
            HomeRoute(
                viewModel = hiltViewModel(),
                onSelectMovie = {
                    navController.navigate(MOVIE_ROUTE.build(it))
                }
            )
        }
        composable(
            route = MOVIE_ROUTE.route,
            arguments = MOVIE_ROUTE.arguments
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt(MOVIE_ROUTE.id.name)
            check(movieId != null)

            DetailsRoute(
                viewModel = hiltViewModel(),
                movieId = movieId,
                onTopBarBackPressed = {
                    navController.popBackStack()
                }
            )
        }
    }
}
