package com.example.jetcomposedemo.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.jetcomposedemo.HomeViewModel

@Composable
fun SetNavGraph(navController: NavHostController, vm: HomeViewModel) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(
            route = Screen.Home.route,
        ) {
            HomeScreen(vm = vm, navController = navController)
        }

        composable(
            route = "${Screen.Detail.route}/{year}",
            arguments = listOf(navArgument("year") { type = NavType.StringType })
        ) {
            it.arguments?.getString("year")
                ?.let { it1 -> DetailScreen(vm = vm,navController = navController, it1) }
        }
    }
}