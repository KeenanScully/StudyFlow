package com.studyflow.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.studyflow.app.ui.screens.DashboardScreen
import com.studyflow.app.ui.screens.LoginScreen
import com.studyflow.app.ui.screens.ModulesScreen
import com.studyflow.app.ui.screens.RegisterScreen
import com.studyflow.app.ui.screens.SettingsScreen
import com.studyflow.app.ui.theme.screens.AddEditModuleScreen
import com.studyflow.app.ui.theme.screens.SplashScreen

@Composable
fun StudyFlowNavGraph() {

    // Creates the controller responsible for moving between screens.
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH
    ) {

        composable(Routes.SPLASH) {
            SplashScreen(navController)
        }

        composable(Routes.LOGIN) {
            LoginScreen(navController)
        }

        composable(Routes.REGISTER) {
            RegisterScreen(navController)
        }

        composable(Routes.DASHBOARD) {
            DashboardScreen(navController)
        }

        composable(Routes.MODULES) {
            ModulesScreen(navController)
        }

        // Opens the form for creating a new module.
        composable(Routes.ADD_MODULE) {
            AddEditModuleScreen(
                navController = navController,
                moduleId = null
            )
        }

        // Opens the form for editing a module.
        composable("${Routes.EDIT_MODULE}/{moduleId}") { backStackEntry ->

            val moduleIdString =
                backStackEntry.arguments?.getString("moduleId")

            val moduleId =
                moduleIdString?.toIntOrNull()

            AddEditModuleScreen(
                navController = navController,
                moduleId = moduleId
            )
        }

        composable(Routes.SETTINGS) {
            SettingsScreen(navController)
        }
    }
}