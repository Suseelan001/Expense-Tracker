package com.example.expensetracker.screens.settingsScreenPackage



import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.expensetracker.R
import com.example.expensetracker.ui.theme.Hex3d3a35
import com.example.expensetracker.ui.theme.Hex6a6762
import com.example.expensetracker.ui.theme.Hex816f64
import com.example.expensetracker.ui.theme.Hex888888
import com.example.expensetracker.ui.theme.Hexc9c6c1
import com.example.expensetracker.ui.theme.Hexd124a83
import com.example.expensetracker.ui.theme.Hexd8d5cc
import com.example.expensetracker.ui.theme.Hexded1c0
import com.example.expensetracker.ui.theme.Hexf1efe3
import com.example.expensetracker.ui.theme.Hexf6f3ea
import com.example.expensetracker.ui.theme.NotoSerifWithHex5d372418sp
import com.example.expensetracker.viewModel.MainViewModel
import com.example.expensetracker.viewModel.SharedPreferenceViewModel


@Composable
fun SettingsDetailToggleBoxScreen(
    navHostController: NavHostController,
    screenTitle:String,
    sharedPreferenceViewModel: SharedPreferenceViewModel
){
    val newTitle = remember { mutableStateOf("") }
    var budgetAmount by remember { mutableStateOf("") }
    var isChecked by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Hexded1c0)
    ) {
    SettingsDetailTSHeaderRow(navHostController,screenTitle)

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
        Text(text = screenTitle, modifier = Modifier.padding(start = 16.dp), color = Hex6a6762)
    }
    HorizontalDivider(thickness = 1.dp, color = Hexc9c6c1)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Hexf6f3ea)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        if (screenTitle.contains("hide future", ignoreCase = true)) {
            newTitle.value = "Hide Future"
        }else {
            newTitle.value=screenTitle
        }
        Text(text = newTitle.value, modifier = Modifier.weight(1f), color = Hexd124a83)
        androidx.compose.material.Switch(checked = isChecked, onCheckedChange = {isChecked = it})

    }
    HorizontalDivider(thickness = 1.dp, color = Hexc9c6c1)

if (isChecked && screenTitle=="Budget Mode"){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Hexf6f3ea)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Budget Amount",
            modifier = Modifier.weight(1f),
            color = Hexd124a83,
            style = TextStyle(
                fontSize = 16.sp,
                textAlign = TextAlign.Start
            )
        )


            BasicTextField(
                value = budgetAmount,
                onValueChange = { newAmount -> budgetAmount = newAmount },
                textStyle = TextStyle(
                    color = Hex3d3a35,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                decorationBox = { innerTextField ->
                    if (budgetAmount.isEmpty()) {
                        Text(
                            text = "Amount",
                            color = Hexc9c6c1,
                            style = TextStyle(fontSize = 16.sp)
                        )
                    }
                    innerTextField()
                }
            )

    }

    HorizontalDivider(thickness = 1.dp, color = Hexc9c6c1)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Hexf6f3ea)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {


        Text(text = "Include Income", modifier = Modifier.weight(1f), color = Hexd124a83)

        androidx.compose.material.Switch(checked = isChecked, onCheckedChange = {isChecked = it})

    }
    HorizontalDivider(thickness = 1.dp, color = Hexc9c6c1)

}


}}

@Composable
fun SettingsDetailTSHeaderRow(navHostController: NavHostController,title:String) {
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
                text = title,
                style = NotoSerifWithHex5d372418sp,
                modifier = Modifier.padding(vertical = 6.dp, horizontal = 5.dp)
            )
        }
    }
}
