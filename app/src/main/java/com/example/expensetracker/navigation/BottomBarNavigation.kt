package com.example.expensetracker.navigation

import android.app.Activity
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.expensetracker.screens.AddExpenseAndIncome
import com.example.expensetracker.screens.AccountScreen
import com.example.expensetracker.screens.AddAccountScreen
import com.example.expensetracker.screens.CategoriesScreen
import com.example.expensetracker.screens.HomeScreen
import com.example.expensetracker.screens.NotificationScreen

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
                AccountScreen(navHostController = navHostController)
            }
        }

        composable(ScreenRoutes.Detail.route) {
            AddExpenseAndIncome(navHostController = navHostController)
        }
        composable(ScreenRoutes.AddAccountScreen.route) {
            AddAccountScreen(navHostController = navHostController)
        }
        composable(ScreenRoutes.CategoriesScreen.route) {
            CategoriesScreen(navHostController = navHostController)
        }
    }
}
