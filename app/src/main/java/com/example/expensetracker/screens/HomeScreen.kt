package com.example.expensetracker.screens


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.expensetracker.R
import com.example.expensetracker.model.AddAccount
import com.example.expensetracker.model.TransactionModel
import com.example.expensetracker.navigation.ScreenRoutes
import com.example.expensetracker.ui.theme.Hex674b3f
import com.example.expensetracker.ui.theme.HexFFFFFFFF
import com.example.expensetracker.ui.theme.Hexe0e0e0
import com.example.expensetracker.ui.theme.Hexf1efe3
import com.example.expensetracker.ui.theme.HonchokomonoWithHexe0e0e018sp
import com.example.expensetracker.ui.theme.HonchokomonoWithHexe0e0e020sp
import com.example.expensetracker.viewModel.AddAccountViewModel
import com.example.expensetracker.viewModel.AddTransactionViewModel
import com.google.gson.Gson


@Composable
fun HomeScreen(
    navHostController: NavHostController,
    addAccountViewModel: AddAccountViewModel,
    addTransactionViewModel: AddTransactionViewModel
) {
    val getPrimaryAccount by addAccountViewModel.getPrimaryAccount().observeAsState()
    val accountType = remember { mutableStateOf("") }
    val getAccountList by addAccountViewModel.getAllRecord().observeAsState(emptyList())


    LaunchedEffect(getPrimaryAccount) {
        getPrimaryAccount?.let {
            if (accountType.value != it.accountName) {
                accountType.value = it.accountName
            }
        }
    }

    val transactionList by addTransactionViewModel.getRecordsbyType(accountType.value).observeAsState(emptyList())


    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        TopBar(getAccountList, addAccountViewModel)
        Spacer(modifier = Modifier.width(16.dp))
        DashboardDetails(navHostController, transactionList)
    }
}



@Composable
fun TopBar(getAccountList: List<AddAccount>, addAccountViewModel: AddAccountViewModel) {
    var showColorPicker by remember { mutableStateOf(false) }

    val primaryAccount by remember(getAccountList) {
        derivedStateOf {
            getAccountList.firstOrNull { it.primaryAccount } ?: getAccountList.getOrNull(0)
        }
    }

    println("CHECK_TAG_primaryAccount__  " + Gson().toJson(primaryAccount))
    println("CHECK_TAG_PRIMARY_ACCOUNT_SIZE__  " + getAccountList.size)

    if (showColorPicker) {
        SelectAccountDialog(
            addAccountViewModel = addAccountViewModel,
            getAccountList = getAccountList,
            onAccountSelected = { selectedAccount ->
                addAccountViewModel.updateAccountTypeRecord(primaryAccount?.id ?: -1, false)
                addAccountViewModel.updateAccountTypeRecord(selectedAccount.id, true)
                showColorPicker = false
            },
            onDismiss = { showColorPicker = false }
        )
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(android.graphics.Color.parseColor("#f1efe3")))
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = { /* Handle button click */ },
            colors = ButtonDefaults.buttonColors(Color.Transparent, contentColor = Color(android.graphics.Color.parseColor("#674b3f"))),
            border = BorderStroke(2.dp, Color(android.graphics.Color.parseColor("#674b3f"))),
            shape = RoundedCornerShape(4.dp)
        ) {
            Text("January")
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Profile",
                tint = Color(android.graphics.Color.parseColor(primaryAccount?.color ?: "#674b3f")),
                modifier = Modifier
                    .size(24.dp)
                    .clickable { showColorPicker = true }
            )

            Spacer(modifier = Modifier.width(16.dp))

            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "Menu",
                tint = Color(android.graphics.Color.parseColor("#674b3f")),
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
@Composable
fun SelectAccountDialog(
    addAccountViewModel:AddAccountViewModel,
    getAccountList: List<AddAccount>,
    onAccountSelected: (AddAccount) -> Unit,
    onDismiss: () -> Unit
) {
    var selectedAccount by remember {
        mutableStateOf<AddAccount?>(
            getAccountList.firstOrNull { it.primaryAccount }
                ?: getAccountList.getOrNull(0)
        )
    }

    AlertDialog(
        containerColor = Color(0xFFFFFFFF),
        titleContentColor = Color(0xFF3D3A35),
        onDismissRequest = onDismiss,
        title = { Text(text = "Select Account") },
        text = {
            if (getAccountList.isEmpty()) {
                Text(text = "No accounts available.")
            } else {
                LazyColumn {
                    items(getAccountList) { item ->
                        SelectAccountItem(
                            addAccount = item,
                            selectedAccount = selectedAccount,
                            onClick = { account ->

                                selectedAccount?.let {
                                    addAccountViewModel.updateAccountTypeRecord(
                                        it.id,false)
                                }
                                account.let {
                                    addAccountViewModel.updateAccountTypeRecord(
                                        it.id,true)
                                }
                                println("CHECK_TAG_SELECTED_ACCOUNT_ " + Gson().toJson(selectedAccount) )

                                println("CHECK_TAG_CLICKED_ACCOUNT_ " + Gson().toJson(account) )

                                selectedAccount = account
                                onAccountSelected(account)
                                onDismiss()
                            }
                        )
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("CANCEL", color = Color(0xFF3D3A35))
            }
        }
    )
}


@Composable
fun SelectAccountItem(addAccount: AddAccount, onClick: (AddAccount) -> Unit, selectedAccount: AddAccount?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            val isSelected = addAccount == selectedAccount

            RadioButton(
                selected = isSelected,
                onClick = { onClick(addAccount) },
                modifier = Modifier.size(24.dp),
                colors = RadioButtonDefaults.colors(
                    selectedColor = Color.Green,
                    unselectedColor = Color.Gray
                )
            )

            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = addAccount.accountName,
                color = Hex674b3f,
                style = TextStyle(fontSize = 16.sp)
            )
        }
    }
}

@Composable
fun DashboardDetails( navHostController: NavHostController,transactionList:List<TransactionModel>) {
    val incomeAmount = transactionList
        .filter { it.type == "income" }
        .sumOf { it.amount.toDouble() }

    val expenseAmount = transactionList
        .filter { it.type == "expense" }
        .sumOf { it.amount.toDouble() }
    Column(
        modifier = Modifier
            .wrapContentWidth()
            .background(color = Color.Black)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.baseline_chevron_left_24),
                contentDescription = "chevron_left",
                tint = Hexe0e0e0,
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 5.dp)
            )

            Text(
                text = "Personal",
                style = HonchokomonoWithHexe0e0e018sp
            )

            Icon(
                painter = painterResource(R.drawable.baseline_chevron_right_24),
                contentDescription = "chevron_right",
                tint = Hexe0e0e0,
                modifier = Modifier
                    .size(24.dp)
                    .padding(start = 5.dp)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp)
                .height(10.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color.Red,
                            Color.Blue
                        ),
                        startX = 0f,
                        endX = 1000f
                    ),
                    shape = RoundedCornerShape(4.dp)
                )
        )





        LazyColumn(modifier = Modifier
            .wrapContentWidth()
            .padding(start = 32.dp, end = 32.dp, top = 16.dp)) {
            item {
                FinancialItem(incomeAmount,expenseAmount)
            }

        }

        Spacer(modifier = Modifier.weight(1f))
        BottomButtons(navHostController)

    }
}

@Composable
fun FinancialItem(incomeAmount: Double,expenseAmount:Double) {


    val balanceAmount = incomeAmount - expenseAmount

    Column(modifier = Modifier.fillMaxWidth()) {

        // Display Income
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Income",
                style = HonchokomonoWithHexe0e0e020sp
            )
            Text(
                text = "$${"%.2f".format(incomeAmount)}",
                style = HonchokomonoWithHexe0e0e020sp
            )
        }

        // Display Expense
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Expense",
                style = HonchokomonoWithHexe0e0e020sp
            )
            Text(
                text = "$${"%.2f".format(expenseAmount)}",
                style = HonchokomonoWithHexe0e0e020sp
            )
        }

        // Optional: Horizontal line to separate items
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
        ) {
            val dotSize = 4.dp.toPx()
            val spaceSize = 4.dp.toPx()
            val totalWidth = size.width
            var currentX = 0f
            while (currentX < totalWidth) {
                drawCircle(
                    color = Color.Gray,
                    radius = dotSize / 2,
                    center = androidx.compose.ui.geometry.Offset(currentX + dotSize / 2, size.height / 2)
                )
                currentX += dotSize + spaceSize
            }
        }
        // Display Balance
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Balance",
                style = HonchokomonoWithHexe0e0e020sp
            )
            Text(
                text = "$${"%.2f".format(balanceAmount)}",
                style = HonchokomonoWithHexe0e0e020sp
            )
        }


    }
}




@Composable
fun BottomButtons(navHostController:NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = {
                navHostController.navigate("${ScreenRoutes.AddTransactionScreen.route}/${"0"}/${"expense"}") },
            colors = ButtonDefaults.buttonColors(Color.Transparent, contentColor = HexFFFFFFFF),
            border = BorderStroke(2.dp, HexFFFFFFFF),
            shape = RoundedCornerShape(4.dp),

            ) {
            Text("+ Expense")
        }

        Button(
            onClick = {
                navHostController.navigate("${ScreenRoutes.AddTransactionScreen.route}/${"0"}/${"income"}") },
            colors = ButtonDefaults.buttonColors(Color.Transparent, contentColor = HexFFFFFFFF),
            border = BorderStroke(2.dp, HexFFFFFFFF),
            shape = RoundedCornerShape(4.dp),

            ) {
            Text("+ Income")
        }

    }
}
