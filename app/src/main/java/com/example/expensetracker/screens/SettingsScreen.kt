package com.example.expensetracker.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.expensetracker.R
import com.example.expensetracker.navigation.ScreenRoutes
import com.example.expensetracker.screens.settingsScreenPackage.SelectAlertDialog
import com.example.expensetracker.ui.theme.*
import com.example.expensetracker.viewModel.SharedPreferenceViewModel

@Composable
fun SettingsScreen(navHostController: NavHostController,
                   sharedPreferenceViewModel: SharedPreferenceViewModel ) {

    var showAlertDialog by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf(""  ) }

    val selectedDarkThemeList = remember { mutableStateOf("Off") }
    val selectedCurrencySymbolList = remember { mutableStateOf("₹") }
    val selectedCurrencyCodeList = remember { mutableStateOf("INR") }
    val selectedFontList = remember { mutableStateOf("Noto Sans") }
    val selectedCategoryIconList = remember { mutableStateOf("Filled") }
    val selectedTabsPositionList = remember { mutableStateOf("Bottom") }

    val darkThemeList = listOf("Off", "On", "System Default")
    val currencySymbolList = listOf("$", "€", "£", "₹", "¥", "A$", "C$", "₩", "₽")
    val currencyCodeList = listOf("USD", "EUR", "GBP", "INR", "JPY", "CNY", "AUD")
    val fontList = listOf("Roboto", "Noto Sans", "Open Sans", "Droid Sans", "Lato")
    val categoryIconList = listOf("Filled", "Outline")
    val tabsPositionList = listOf("Top", "Bottom")


    val sections = listOf(
        "Spending (${sharedPreferenceViewModel.getPrimaryAccountName()})" to listOf(
            SettingItemData("Time Period", "Yearly"),
            SettingItemData("Budget Mode", "Off"),
            SettingItemData("Carry Over", "Off"),
            SettingItemData("Hide Future Transactions", "Off")
        ),
        "Automatic Syncing" to listOf(
            SettingItemData("Dropbox Sync", "Off")
        ),
        "User Interface" to listOf(
            SettingItemData("Dark Theme", selectedDarkThemeList.value),
            SettingItemData("Show Transaction Note", "On"),
            SettingItemData("Currency Symbol", selectedCurrencySymbolList.value),
            SettingItemData("Summary Font",selectedFontList.value),
            SettingItemData("Category Icon Style", selectedCategoryIconList.value),
            SettingItemData("Tabs Position", selectedTabsPositionList.value)
        ),
        "General" to listOf(
            SettingItemData("Reminders", "Every Month"),
            SettingItemData("Auto Backup", "Off"),
            SettingItemData("Local Tips", "On"),
            SettingItemData("Passcode", ""),
            SettingItemData("Privacy", ""),
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Hexded1be)
    ) {
        item {
            SettingsHeaderRow(navHostController)
        }
        sections.forEach { (header, items) ->
            item {
                SettingsHeader(title = header)
            }
            items(items) { setting ->
                SettingItem(
                    title = setting.title,
                    value = setting.value,
                    onClick = {
                        when (setting.title) {
                            "Time Period" -> {
                                navHostController.navigate("${ScreenRoutes.TimePeriodScreen.route}/${setting.title}")
                            }
                            "Dropbox Sync" -> {
                                navHostController.navigate(ScreenRoutes.DropBoxScreen.route)
                            }
                            "Dark Theme" -> {
                                selectedItem = "DarkTheme"
                                showAlertDialog=true
                            }
                            "Currency Symbol" -> {
                                selectedItem = "CurrencySymbol"
                                showAlertDialog=true
                            }
                            "Summary Font" -> {
                                selectedItem = "Font"
                                showAlertDialog=true
                            }
                            "Category Icon Style" -> {
                                selectedItem = "CategoryIcon"
                                showAlertDialog=true
                            }
                            "Tabs Position" -> {
                                selectedItem = "TabsPosition"
                                showAlertDialog=true
                            }
                            "Passcode" -> {
                                navHostController.navigate(ScreenRoutes.PasswordScreen.route)

                            }

                            else -> {
                                navHostController.navigate("${ScreenRoutes.SettingsDetailToggleBoxScreen.route}/${setting.title}")

                            }
                        }
                    }
                )
            }
        }
        item { Spacer(modifier = Modifier.height(16.dp)) }
    }


    if (showAlertDialog) {
        val currentList = when (selectedItem) {
            "DarkTheme" -> darkThemeList
            "CurrencySymbol" -> currencySymbolList
            "CurrencyCode" -> currencyCodeList
            "Font" -> fontList
            "CategoryIcon" -> categoryIconList
            "TabsPosition" -> tabsPositionList
            else -> emptyList()
        }

        SelectAlertDialog(
            categoryList = currentList,
            onDismiss = { showAlertDialog = false },
            selectedType = when (selectedItem) {
                "DarkTheme" -> selectedDarkThemeList
                "CurrencySymbol" -> selectedCurrencySymbolList
                "CurrencyCode" -> selectedCurrencyCodeList
                "Font" -> selectedFontList
                "CategoryIcon" -> selectedCategoryIconList
                "TabsPosition" -> selectedTabsPositionList
                else -> remember { mutableStateOf("") }
            }
        )
    }

}

@Composable
fun SettingsHeaderRow(navHostController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Hexf1efe3)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                painter = painterResource(R.drawable.baseline_arrow_back_24),
                contentDescription = "back",
                tint = Hex816f64,
                modifier = Modifier
                    .size(34.dp)
                    .clickable { navHostController.popBackStack() }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Settings",
                style = NotoSerifWithHex5d372418sp,
                modifier = Modifier.padding(vertical = 6.dp, horizontal = 5.dp)
            )
        }
    }
}

@Composable
fun SettingsHeader(title: String) {
    Spacer(modifier = Modifier.height(16.dp))
    HorizontalDivider(thickness = 1.dp, color = Hexc9c6c1)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Hexd8d5cc)
            .padding(vertical = 2.dp)
    ) {
        Text(text = title, modifier = Modifier.padding(start = 16.dp), color = Hex6a6762)
    }
    HorizontalDivider(thickness = 1.dp, color = Hexc9c6c1)
}

@Composable
fun SettingItem(title: String, value: String, onClick: () -> Unit = {}) {
    var isChecked by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Hexf6f3ea)
            .clickable {
                if (title!="Passcode"){
                    onClick()
                }  }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, modifier = Modifier.weight(1f), color = Hexd124a83)
        if (title=="Passcode"){
            androidx.compose.material.Switch(
                checked = isChecked,
                onCheckedChange = { isChecked = it
                    onClick()
                })
        }else{
            Text(text = value, color = Hexd124a83)
        }
    }
    HorizontalDivider(thickness = 1.dp, color = Hexc9c6c1)
}

data class SettingItemData(val title: String, val value: String)
