package com.poly.lab1234_ph51025.Lab78.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.poly.lab1234_ph51025.ui.screen.LoginScreen
import com.poly.lab1234_ph51025.ui.screen.MovieFormScreen
import com.poly.lab1234_ph51025.Lab78.ui.screen.MovieScreen
import com.poly.lab1234_ph51025.viewmodel.MovieViewModel

enum class Screen(val route: String) {
    LOGIN("Login"),
    MOVIE_SCREEN("MovieScreen"),
    ADD("Add"),
    EDIT("Edit"),
}

@Composable
fun ScreenNavigation() {
    val navController = rememberNavController()
    val movieViewModel = MovieViewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.LOGIN.route,
    ) {
        composable(Screen.LOGIN.route) { LoginScreen(navController) }
        composable(Screen.MOVIE_SCREEN.route) { MovieScreen(navController, movieViewModel) }
        composable(Screen.ADD.route) { MovieFormScreen(navController, movieViewModel, null) }
        composable(
            "${Screen.EDIT.route}/{filmsId}",
            arguments = listOf(navArgument("filmsId") { type = NavType.StringType }),
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("filmsId")?.let { filmId ->
                MovieFormScreen(navController, movieViewModel, filmId)
            }
        }
    }
}