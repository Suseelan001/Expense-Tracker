package com.example.expensetracker.navigation

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
import com.example.expensetracker.screens.PieChartScreen
import com.example.expensetracker.screens.SelectCategoriesScreen
import com.example.expensetracker.screens.settingsScreenPackage.SettingsDetailToggleBoxScreen
import com.example.expensetracker.screens.SettingsScreen
import com.example.expensetracker.screens.TransactionScreen
import com.example.expensetracker.screens.TransferScreen
import com.example.expensetracker.screens.settingsScreenPackage.DropBoxScreen
import com.example.expensetracker.screens.settingsScreenPackage.PasswordScreen
import com.example.expensetracker.screens.settingsScreenPackage.ReminderScreen
import com.example.expensetracker.screens.settingsScreenPackage.TimePeriodScreen
import com.example.expensetracker.viewModel.AddAccountViewModel
import com.example.expensetracker.viewModel.AddCategoryViewModel
import com.example.expensetracker.viewModel.AddTransactionViewModel
import com.example.expensetracker.viewModel.MainViewModel
import com.example.expensetracker.viewModel.SettingsAllRecordViewModel
import com.example.expensetracker.viewModel.SharedPreferenceViewModel

@Composable
fun BottomBarNavigation(
    navHostController: NavHostController,
    padding: PaddingValues,
    mainViewModel: MainViewModel
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
                val addAccountViewModel = hiltViewModel<AddAccountViewModel>()
                val addTransactionViewModel = hiltViewModel<AddTransactionViewModel>()
                val sharedPreferenceViewModel = hiltViewModel<SharedPreferenceViewModel>()
                val settingsAllRecordViewModel = hiltViewModel<SettingsAllRecordViewModel>()

                HomeScreen(navHostController = navHostController,addAccountViewModel,addTransactionViewModel,sharedPreferenceViewModel,settingsAllRecordViewModel)
            }
            composable(BottomBarRoutes.TRANSACTIONS_SCREEN.routes) {
                val addTransactionViewModel = hiltViewModel<AddTransactionViewModel>()
                val addAccountViewModel = hiltViewModel<AddAccountViewModel>()
                val addCategoryViewModel = hiltViewModel<AddCategoryViewModel>()
                TransactionScreen(navHostController = navHostController,addTransactionViewModel,addAccountViewModel,addCategoryViewModel)
            }
            composable(BottomBarRoutes.CATEGORIES_SCREEN.routes) {
                val addCategoryViewModel = hiltViewModel<AddCategoryViewModel>()

                CategoriesScreen(navHostController = navHostController,addCategoryViewModel)
            }
            composable(BottomBarRoutes.ACCOUNTS_SCREEN.routes) {
                val addAccountViewModel = hiltViewModel<AddAccountViewModel>()
                AccountScreen(navHostController = navHostController,addAccountViewModel)
            }
        }



        composable(
            route = "${ScreenRoutes.AddTransactionScreen.route}/{accountId}/{type}",
            arguments = listOf(navArgument("accountId") { type = NavType.StringType })
        ) { backStackEntry ->
            val addTransactionViewModel = hiltViewModel<AddTransactionViewModel>()
            val addAccountViewModel = hiltViewModel<AddAccountViewModel>()
            val accountId = backStackEntry.arguments?.getString("accountId")?:"0"
            val type = backStackEntry.arguments?.getString("type")?:""
            AddExpenseAndIncome(navHostController = navHostController,addTransactionViewModel,mainViewModel,accountId,type,addAccountViewModel)

        }

        composable(
            route = ScreenRoutes.TransferScreen.route) {
            val addTransactionViewModel = hiltViewModel<AddTransactionViewModel>()
            val addAccountViewModel = hiltViewModel<AddAccountViewModel>()
            TransferScreen(navHostController = navHostController,addTransactionViewModel,addAccountViewModel)

        }


        composable(
            route = ScreenRoutes.SettingsScreen.route) {
            val sharedPreferenceViewModel = hiltViewModel<SharedPreferenceViewModel>()
            val settingsAllRecordViewModel = hiltViewModel<SettingsAllRecordViewModel>()

            SettingsScreen(navHostController = navHostController,sharedPreferenceViewModel,settingsAllRecordViewModel)

        }

        composable(
            route = "${ScreenRoutes.SettingsDetailToggleBoxScreen.route}/{screenTitle}/{settingsRecordId}",
            arguments = listOf(navArgument("screenTitle") { type = NavType.StringType })
        ) { backStackEntry ->

            val screenTitle = backStackEntry.arguments?.getString("screenTitle")?:""
            val settingsRecordId = backStackEntry.arguments?.getString("settingsRecordId")?:"0"
            val sharedPreferenceViewModel = hiltViewModel<SharedPreferenceViewModel>()
            val settingsAllRecordViewModel = hiltViewModel<SettingsAllRecordViewModel>()

            SettingsDetailToggleBoxScreen(navHostController = navHostController,screenTitle,settingsRecordId,sharedPreferenceViewModel,settingsAllRecordViewModel)

        }

        composable(
            route = "${ScreenRoutes.TimePeriodScreen.route}/{screenTitle}/{settingsRecordId}",
            arguments = listOf(navArgument("screenTitle") { type = NavType.StringType })
        ) { backStackEntry ->

            val screenTitle = backStackEntry.arguments?.getString("screenTitle")?:""
            val settingsRecordId = backStackEntry.arguments?.getString("settingsRecordId")?:""
            val sharedPreferenceViewModel = hiltViewModel<SharedPreferenceViewModel>()
            val settingsAllRecordViewModel = hiltViewModel<SettingsAllRecordViewModel>()

            TimePeriodScreen(navHostController = navHostController,screenTitle,settingsRecordId,sharedPreferenceViewModel,settingsAllRecordViewModel)

        }

        composable(
            route = "${ScreenRoutes.DropBoxScreen.route}/{screenTitle}",
            arguments = listOf(navArgument("screenTitle") { type = NavType.StringType })
        ) { backStackEntry ->

            val screenTitle = backStackEntry.arguments?.getString("screenTitle")?:""

            DropBoxScreen(navHostController = navHostController,screenTitle)

        }


        composable(
            route = ScreenRoutes.ReminderScreen.route) {
            ReminderScreen(navHostController = navHostController)

        }



        composable(
            route = "${ScreenRoutes.AddAccountScreen.route}/{accountId}/{screenType}/{transactionType}",
            arguments = listOf(navArgument("accountId") { type = NavType.StringType })
        ) { backStackEntry ->
            val addAccountViewModel = hiltViewModel<AddAccountViewModel>()
            val addCategoryViewModel = hiltViewModel<AddCategoryViewModel>()
            val userId = backStackEntry.arguments?.getString("accountId")?:"0"
            val screenType = backStackEntry.arguments?.getString("screenType")?:""
            val transactionType = backStackEntry.arguments?.getString("transactionType")?:""
                AddAccountScreen(screenType,accountId = userId,transactionType, navHostController = navHostController, addAccountViewModel,addCategoryViewModel)

        }



        composable(
            route = "${ScreenRoutes.SelectCategoriesScreen.route}/{transactionType}",
            arguments = listOf(navArgument("transactionType") { type = NavType.StringType })
        ) { backStackEntry ->
            val addCategoryViewModel = hiltViewModel<AddCategoryViewModel>()
            val transactionType = backStackEntry.arguments?.getString("transactionType")?:""
            SelectCategoriesScreen(navHostController = navHostController,addCategoryViewModel,transactionType,mainViewModel)

        }

        composable(
            route = ScreenRoutes.PieChartScreen.route
        ) {
            val addTransactionViewModel = hiltViewModel<AddTransactionViewModel>()
            val addAccountViewModel = hiltViewModel<AddAccountViewModel>()

            PieChartScreen(navHostController = navHostController,addTransactionViewModel,addAccountViewModel)

        }
        composable(
            route = ScreenRoutes.PasswordScreen.route
        ) {

            PasswordScreen(navHostController = navHostController)

        }

    }
}
