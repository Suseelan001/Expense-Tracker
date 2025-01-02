package com.example.expensetracker.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.expensetracker.R

enum class BottomBarRoutes(
    val id: Int,
    @StringRes val title: Int,
    val routes: String,
    @DrawableRes val icon: Int
) {

    SPENDING_SCREEN(1, R.string.spending, "SPENDING_SCREEN", R.drawable.price_tag),
    TRANSACTIONS_SCREEN(2, R.string.transactions, "TRANSACTIONS_SCREEN", R.drawable.transaction_icon),
    CATEGORIES_SCREEN(3, R.string.categories, "CATEGORIES_SCREEN", R.drawable.category_icon),
    ACCOUNTS_SCREEN(4, R.string.accounts, "ACCOUNTS_SCREEN", R.drawable.accounts_icon)

}