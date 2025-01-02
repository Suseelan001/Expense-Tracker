package com.example.expensetracker.navigation

sealed class ScreenRoutes(val route: String) {

    data object Splash : ScreenRoutes("SPLASH_SCREEN")
    data object BottomBar : ScreenRoutes("BOTTOM_BAR")
    data object AddTransactionScreen : ScreenRoutes("ADD_TRANSACTION_SCREEN")
    data object AddAccountScreen : ScreenRoutes("ADD_ACCOUNT_SCREEN")
    data object CategoriesScreen : ScreenRoutes("CATEGORIES_SCREEN")

}