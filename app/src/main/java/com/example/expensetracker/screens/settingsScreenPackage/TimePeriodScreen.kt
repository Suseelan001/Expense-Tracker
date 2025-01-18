package com.example.expensetracker.screens.settingsScreenPackage



import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.expensetracker.ui.theme.Hex5d3724
import com.example.expensetracker.ui.theme.Hex6a6762
import com.example.expensetracker.ui.theme.Hex888888
import com.example.expensetracker.ui.theme.Hexc9c6c1
import com.example.expensetracker.ui.theme.Hexd124a83
import com.example.expensetracker.ui.theme.Hexd8d5cc
import com.example.expensetracker.ui.theme.Hexded1c0
import com.example.expensetracker.ui.theme.Hexf6f3ea
import com.example.expensetracker.ui.theme.NotoSerifWithHex39393920sp
import com.example.expensetracker.ui.theme.NotoSerifWithHex5d372418sp
import com.example.expensetracker.viewModel.SharedPreferenceViewModel


@Composable
fun TimePeriodScreen(
    navHostController: NavHostController,
    screenTitle:String,
    sharedPreferenceViewModel: SharedPreferenceViewModel
){
    var showAlertDialog by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf(""  ) }

    val selectedPeriodList = remember { mutableStateOf("Yearly") }
    val selectedDaysList = remember { mutableStateOf("1") }
    val selectedWeekList = remember { mutableStateOf("Monday") }


    val periodList = listOf("Daily", "Weekly", "Monthly", "Yearly")
    val daysList = List(31) { (it + 1).toString() }
    val weekList = listOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")



    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Hexded1c0)
    ) {
    SettingsDetailTSHeaderRow(navHostController, screenTitle)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.wrapContentWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                sharedPreferenceViewModel.getPrimaryAccountName()?.let {
                    Text(
                        text = it,
                        color = Hex888888
                    )
                }
            }
        }


        Spacer(modifier = Modifier.height(16.dp))
    HorizontalDivider(thickness = 1.dp, color = Hexc9c6c1)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Hexd8d5cc)
            .padding(vertical = 2.dp)
    ) {
        Text(text =  "Default Time Period", modifier = Modifier.padding(start = 16.dp), color = Hex6a6762)
    }


    HorizontalDivider(thickness = 1.dp, color = Hexc9c6c1)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Hexf6f3ea)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(text = "Show Spending", modifier = Modifier.weight(1f), color = Hexd124a83)

        Text(text = selectedPeriodList.value,
            color = Hexd124a83, modifier = Modifier
            .clickable {
                showAlertDialog=true
                selectedItem="Period"
            })

    }
    HorizontalDivider(thickness = 1.dp, color = Hexc9c6c1)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Hexf6f3ea)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(text = "Month Start Day", modifier = Modifier.weight(1f), color = Hexd124a83)

        Text(text = selectedDaysList.value, color = Hexd124a83, modifier = Modifier
            .clickable {
                showAlertDialog=true
                selectedItem="Date"
            })

    }

    HorizontalDivider(thickness = 1.dp, color = Hexc9c6c1)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Hexf6f3ea)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(text = "Week Start Day", modifier = Modifier.weight(1f), color = Hexd124a83)
        Text(text = selectedWeekList.value, color = Hexd124a83, modifier = Modifier
            .clickable {
                showAlertDialog=true
                selectedItem="Week"
            })

    }
    HorizontalDivider(thickness = 1.dp, color = Hexc9c6c1)


}

    if (showAlertDialog) {
        val currentList = when (selectedItem) {
            "Date" -> daysList
            "Week" -> weekList
            "Period" -> periodList
            else -> emptyList()
        }
        SelectAlertDialog(
            categoryList = currentList,
            onDismiss = { showAlertDialog = false },
            selectedType = when (selectedItem) {
                "Date" -> selectedDaysList
                "Week" -> selectedWeekList
                "Period" -> selectedPeriodList
                else -> remember { mutableStateOf("") }
            }
        )
    }

}

@Composable
fun SelectAlertDialog(
    categoryList: List<String>,
    onDismiss: () -> Unit,
    selectedType: MutableState<String>
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
                    SelectAlertItem(
                        item = item,
                        selectedType = selectedType,
                        onDismiss = onDismiss
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
fun SelectAlertItem(
    item: String,
    selectedType: MutableState<String>,
    onDismiss: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                selectedType.value = item
                onDismiss()
            }
            .padding(8.dp)
    ) {
        RadioButton(
            selected = selectedType.value == item,
            onClick = {
                selectedType.value = item
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
