package com.example.expensetracker.navigation

import android.app.Activity
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.expensetracker.screens.AddExpenseAndIncome
import com.example.expensetracker.screens.AccountScreen
import com.example.expensetracker.screens.AddAccountScreen
import com.example.expensetracker.screens.CategoriesScreen
import com.example.expensetracker.screens.HomeScreen
import com.example.expensetracker.screens.NotificationScreen
import com.example.expensetracker.viewModel.AddAccountViewModel

@Composable
fun BottomBarNavigation(
    navHostController: NavHostController,
    padding: PaddingValues,
    context: Activity
) {
    NavHost(
        navController = navHostController,
        startDestination = ScreenRoutes.BottomBar.route,
        modifier = Modifier.padding(padding)
    ) {
        navigation(
            route = ScreenRoutes.BottomBar.route,
            startDestination = BottomBarRoutes.SPENDING_SCREEN.routes
        ) {
            composable(BottomBarRoutes.SPENDING_SCREEN.routes) {
                HomeScreen(navHostController = navHostController, context)
            }
            composable(BottomBarRoutes.TRANSACTIONS_SCREEN.routes) {
                NotificationScreen(navHostController = navHostController)
            }
            composable(BottomBarRoutes.CATEGORIES_SCREEN.routes) {
                CategoriesScreen(navHostController = navHostController)
            }
            composable(BottomBarRoutes.ACCOUNTS_SCREEN.routes) {
                val addAccountViewModel = hiltViewModel<AddAccountViewModel>()

                AccountScreen(navHostController = navHostController,addAccountViewModel)
            }
        }

        composable(ScreenRoutes.AddTransactionScreen.route) {
            AddExpenseAndIncome(navHostController = navHostController)
        }


        composable(
            route = "${ScreenRoutes.AddAccountScreen.route}/{accountId}",
            arguments = listOf(navArgument("accountId") { type = NavType.StringType })
        ) { backStackEntry ->
            val addAccountViewModel = hiltViewModel<AddAccountViewModel>()
            val userId = backStackEntry.arguments?.getString("accountId")?:"0"


                AddAccountScreen(accountId = userId, navHostController = navHostController, addAccountViewModel)



        }


        composable(ScreenRoutes.CategoriesScreen.route) {
            CategoriesScreen(navHostController = navHostController)
        }
    }
}
