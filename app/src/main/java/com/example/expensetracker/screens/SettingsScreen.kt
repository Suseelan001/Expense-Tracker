package com.example.expensetracker.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.expensetracker.R
import com.example.expensetracker.navigation.ScreenRoutes
import com.example.expensetracker.ui.theme.*
import com.example.expensetracker.viewModel.SettingsAllRecordViewModel
import com.example.expensetracker.viewModel.SharedPreferenceViewModel

@Composable
fun SettingsScreen(navHostController: NavHostController,
                   sharedPreferenceViewModel: SharedPreferenceViewModel,
                   settingsAllRecordViewModel: SettingsAllRecordViewModel) {

    val settingsRecordId = remember { mutableStateOf("") }
    var showAlertDialog by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf(""  ) }

    val selectedDarkThemeList = remember { mutableStateOf(" ") }
    val selectedCurrencySymbolList = remember { mutableStateOf("") }
    val selectedFont = remember { mutableStateOf("") }
    val selectedCategoryIconList = remember { mutableStateOf("") }
    val selectedTabsPositionList = remember { mutableStateOf("") }
    val timePeriod = remember { mutableStateOf("") }
    val hideFutureTransaction = remember { mutableStateOf(false) }
    val transactionNote = remember { mutableStateOf(false) }

    val primaryAccountName = sharedPreferenceViewModel.getPrimaryAccountName()

    if (!primaryAccountName.isNullOrEmpty()) {
        val getAccountRecord by settingsAllRecordViewModel
            .getSingleRecord(primaryAccountName)
            .observeAsState()

        LaunchedEffect(getAccountRecord) {
            getAccountRecord?.let { record ->
                selectedDarkThemeList.value = record.darkTheme
                selectedCurrencySymbolList.value = record.currencySymbol
                selectedFont.value = record.fontStyle
                selectedCategoryIconList.value = record.iconType
                selectedTabsPositionList.value = record.tabPosition
                timePeriod.value = record.timePeriod.spending
                settingsRecordId.value= record.id.toString()
                hideFutureTransaction.value= record.hideFuture
                transactionNote.value= record.showTransactionNote
            }
        }
    }








    val darkThemeList = listOf("Off", "On", "System Default")
    val currencySymbolList = listOf("Default","$", "€", "£", "₹", "¥", "A$", "C$", "₩", "₽")
    val fontList = listOf("Roboto", "Open Sans", "Droid Sans")
    val categoryIconList = listOf("Filled", "Outline")
    val tabsPositionList = listOf("Top", "Bottom")


    val sections = listOf(
        "Spending (${sharedPreferenceViewModel.getPrimaryAccountName()})" to listOf(
            SettingItemData("Time Period", timePeriod.value),
            SettingItemData("Budget Mode", "Off"),
            SettingItemData("Carry Over", "Off"),
            SettingItemData("Hide Future Transactions", if (hideFutureTransaction.value) "On" else "Off")
        ),
        "Automatic Syncing" to listOf(
            SettingItemData("Dropbox Sync", "Off")
        ),
        "User Interface" to listOf(
            SettingItemData("Dark Theme", selectedDarkThemeList.value),
            SettingItemData("Show Transaction Note", if (transactionNote.value) "On" else "Off"),
            SettingItemData("Currency Symbol", selectedCurrencySymbolList.value),
            SettingItemData("Summary Font",selectedFont.value),
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
                                navHostController.navigate("${ScreenRoutes.TimePeriodScreen.route}/${setting.title}/${settingsRecordId.value}")
                            }
                            "Dropbox Sync"  -> {
                                navHostController.navigate("${ScreenRoutes.DropBoxScreen.route}/${"Automatic Syncing"}")
                            }
                            "Auto Backup"  -> {
                                navHostController.navigate("${ScreenRoutes.DropBoxScreen.route}/${"Automatic Backup"}")
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
                                selectedItem = "Category Icon"
                                showAlertDialog=true
                            }
                            "Tabs Position" -> {
                                selectedItem = "Tabs Position"
                                showAlertDialog=true
                            }
                            "Passcode" -> {
                                navHostController.navigate(ScreenRoutes.PasswordScreen.route)
                            }
                            "Reminders" -> {
                                navHostController.navigate(ScreenRoutes.ReminderScreen.route)
                            }

                            else -> {
                                println("CHECK_TAG_SETTING_RECORD_Id     $settingsRecordId")
                                navHostController.navigate("${ScreenRoutes.SettingsDetailToggleBoxScreen.route}/${setting.title}/${settingsRecordId.value}")

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
            "Font" -> fontList
            "Category Icon" -> categoryIconList
            "Tabs Position" -> tabsPositionList
            else -> emptyList()
        }

        SelectAlertDialogSettingsScreen(
            categoryList = currentList,
            onDismiss = { showAlertDialog = false },
            selectedType = when (selectedItem) {
                "DarkTheme" -> {
                    selectedDarkThemeList
                }
                "CurrencySymbol" -> selectedCurrencySymbolList
                "Font" -> selectedFont
                "Category Icon" -> selectedCategoryIconList
                "Tabs Position" -> selectedTabsPositionList
                else -> remember { mutableStateOf("") }
            },
            onItemSelected = { selectedValue ->
                if (selectedItem == "DarkTheme") {
                    settingsAllRecordViewModel.updateDarkTheme( settingsRecordId.value.toInt(), selectedValue)
                }
                if (selectedItem == "CurrencySymbol") {
                    settingsAllRecordViewModel.updateSymbol( settingsRecordId.value.toInt(), selectedCurrencySymbolList.value)
                }
                if (selectedItem == "Font") {
                    settingsAllRecordViewModel.updateFont( settingsRecordId.value.toInt(), selectedFont.value)
                }
                if (selectedItem == "Category Icon") {
                    settingsAllRecordViewModel.updateCategoryIcon( settingsRecordId.value.toInt(), selectedCategoryIconList.value)
                }
                if (selectedItem == "Tabs Position") {
                    settingsAllRecordViewModel.updateTabPosition( settingsRecordId.value.toInt(), selectedTabsPositionList.value)
                }
                showAlertDialog = false
            }
        )

    }

}
@Composable
fun SelectAlertDialogSettingsScreen(
    categoryList: List<String>,
    onDismiss: () -> Unit,
    selectedType: MutableState<String>,
    onItemSelected: (String) -> Unit
) {
    AlertDialog(
        containerColor = Color(0xFFFFFFFF),
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Select an Option",
                style = NotoSerifWithHex39393920sp
            )
        },
        text = {
            LazyColumn {
                items(categoryList) { item ->
                    SelectAlertItemSettingsScreen(
                        item = item,
                        selectedType = selectedType,
                        onDismiss = onDismiss,
                        onItemSelected
                    )
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel", style = NotoSerifWithHex5d372418sp)
            }
        }
    )
}

@Composable
fun SelectAlertItemSettingsScreen(
    item: String,
    selectedType: MutableState<String>,
    onDismiss: () -> Unit,
    onItemSelected: (String) -> Unit

) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                selectedType.value = item
                onItemSelected(item)
                onDismiss()
            }
            .padding(8.dp)
    ) {
        RadioButton(
            selected = selectedType.value == item,
            onClick = {
                selectedType.value = item
                onItemSelected(item)
                onDismiss()
            },
            colors = RadioButtonDefaults.colors(
                selectedColor = Hex5d3724,
                unselectedColor = Hex5d3724
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = item, style = NotoSerifWithHex5d372418sp)
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
                if (title != "Passcode") {
                    onClick()
                }
            }
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
