package com.example.expensetracker.navigation

sealed class ScreenRoutes(val route: String) {

    data object Splash : ScreenRoutes("SPLASH_SCREEN")
    data object BottomBar : ScreenRoutes("BOTTOM_BAR")
    data object AddTransactionScreen : ScreenRoutes("ADD_TRANSACTION_SCREEN")
    data object AddAccountScreen : ScreenRoutes("ADD_ACCOUNT_SCREEN")
    data object SelectCategoriesScreen : ScreenRoutes("SELECT_CATEGORIES_SCREEN")
    data object PieChartScreen : ScreenRoutes("PIE_CHART_SCREEN")
    data object TransferScreen : ScreenRoutes("TRANSFER_SCREEN")

}