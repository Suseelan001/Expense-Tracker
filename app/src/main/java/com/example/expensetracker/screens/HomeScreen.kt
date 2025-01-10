package com.example.expensetracker.screens


import android.app.Activity
import android.os.Build
import android.view.View
import android.view.WindowInsetsController
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.expensetracker.R
import com.example.expensetracker.model.AddAccount
import com.example.expensetracker.model.TransactionModel
import com.example.expensetracker.navigation.ScreenRoutes
import com.example.expensetracker.ui.theme.Hex2b2b2b
import com.example.expensetracker.ui.theme.Hex5d3724
import com.example.expensetracker.ui.theme.HexFFFFFFFF
import com.example.expensetracker.ui.theme.Hexe0e0e0
import com.example.expensetracker.ui.theme.NotoSerifWithHex33cc4d20sp
import com.example.expensetracker.ui.theme.NotoSerifWithHex39393920sp
import com.example.expensetracker.ui.theme.NotoSerifWithHex5d372418sp
import com.example.expensetracker.ui.theme.NotoSerifWithHex7fcbe920sp
import com.example.expensetracker.ui.theme.NotoSerifWithHex9e3d4620sp
import com.example.expensetracker.ui.theme.NotoSerifWithHexFFFFFFFF18sp
import com.example.expensetracker.ui.theme.NotoSerifWithHexb7efca20sp
import com.example.expensetracker.ui.theme.NotoSerifWithHexc1941020sp
import com.example.expensetracker.ui.theme.NotoSerifWithHexc6787120sp
import com.example.expensetracker.ui.theme.NotoSerifWithHexe0e0e020sp
import com.example.expensetracker.viewModel.AddAccountViewModel
import com.example.expensetracker.viewModel.AddTransactionViewModel
import java.text.DecimalFormat
import java.time.LocalDate
import java.time.Year
import java.time.format.DateTimeFormatter


@Composable
fun HomeScreen(
    navHostController: NavHostController,
    addAccountViewModel: AddAccountViewModel,
    addTransactionViewModel: AddTransactionViewModel
) {

    SetStatusBarColor()
    val getPrimaryAccount by addAccountViewModel.getPrimaryAccount().observeAsState()
    val accountName = remember { mutableStateOf("") }
    val getAccountList by addAccountViewModel.getAllRecord().observeAsState(emptyList())
    var showAccountSelector by remember { mutableStateOf(false) }
    var showDateSelector by remember { mutableStateOf(false) }

    val selectedType = remember { mutableStateOf("Monthly") }
    val dateList = listOf("Daily", "Weekly", "Monthly", "Yearly")
    val currentDate = remember { mutableStateOf(LocalDate.now()) }
    val currentYear = remember { mutableIntStateOf(Year.now().value) }
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")


    LaunchedEffect(selectedType.value) {
        when (selectedType.value) {
            "Daily" -> currentDate.value = LocalDate.now()
            "Weekly" -> currentDate.value = LocalDate.now()
            "Monthly" -> currentDate.value = LocalDate.now()
            "Yearly" -> currentYear.intValue = Year.now().value
        }
    }

    val (startDate, endDate, displayText) = when (selectedType.value) {
        "Daily" -> {
            val startDate = currentDate.value
            val endDate = currentDate.value
            Triple(
                startDate.format(formatter),
                endDate.format(formatter),
                startDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
            )
        }
        "Weekly" -> {
            val startDate = currentDate.value
            val endDate = currentDate.value.plusDays(6)
            Triple(
                startDate.format(formatter),
                endDate.format(formatter),
                "${startDate.format(DateTimeFormatter.ofPattern("MMM dd"))} - ${endDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))}"
            )
        }
        "Monthly" -> {
            val startDate = currentDate.value.withDayOfMonth(1)
            val endDate = currentDate.value.withDayOfMonth(currentDate.value.lengthOfMonth())
            val displayText = if (currentDate.value.year == Year.now().value) {
                currentDate.value.month.name.lowercase().replaceFirstChar { it.uppercase() }
            } else {
                "${currentDate.value.month.name.lowercase().replaceFirstChar { it.uppercase() }.take(3)} ${currentDate.value.year}"
            }
            Triple(
                startDate.format(formatter),
                endDate.format(formatter),
                displayText
            )
        }
        "Yearly" -> {
            val startDate = LocalDate.of(currentYear.intValue, 1, 1)
            val endDate = LocalDate.of(currentYear.intValue, 12, 31)

            Triple(
                startDate.format(formatter),
                endDate.format(formatter),
                "${currentYear.intValue}"
            )


        }
        else -> Triple("", "", "")
    }

    LaunchedEffect(getPrimaryAccount) {
        getPrimaryAccount?.let {
            if (accountName.value != it.accountName) {
                accountName.value = it.accountName
            }
        }
    }

    val transactionList by addTransactionViewModel
        .getRecordsByDateRange(accountName.value,startDate,endDate)
        .observeAsState(emptyList())

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        val primaryAccount by remember(getAccountList) {
            derivedStateOf {
                getAccountList.firstOrNull { it.primaryAccount } ?: getAccountList.getOrNull(0)
            }
        }
        if (showAccountSelector) {
            SelectAccountDialog(
                addAccountViewModel = addAccountViewModel,
                getAccountList = getAccountList,
                onAccountSelected = { selectedAccount ->
                    addAccountViewModel.updateAccountTypeRecord(primaryAccount?.id ?: -1, false)
                    addAccountViewModel.updateAccountTypeRecord(selectedAccount.id, true)
                    showAccountSelector = false
                },
                onDismiss = { showAccountSelector = false }
            )
        }

        if (showDateSelector) {
            SelectDateDialog(
                dateList = dateList,
                onDismiss = { showDateSelector = false },
                selectedType = selectedType
            )
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(android.graphics.Color.parseColor("#f1efe3")))
                .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .widthIn(min = 100.dp)
                    .wrapContentHeight()
                    .border(2.dp, Hex5d3724, RoundedCornerShape(4.dp))
                    .background(Color.Transparent)
                    .clickable {
                        showDateSelector = true
                    }

            ) {
                Text(
                    text = displayText,
                    style = NotoSerifWithHex5d372418sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 6.dp, bottom = 6.dp, start = 5.dp, end = 5.dp)
                        .align(Alignment.Center)
                )
            }


            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile",
                    tint = primaryAccount?.color?.takeIf { it.isNotEmpty() && it.startsWith("#") && it.length == 7 }?.let {
                        Color(android.graphics.Color.parseColor(it))
                    } ?: Hex5d3724,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { showAccountSelector = true }
                )

                Spacer(modifier = Modifier.width(16.dp))

                     Icon(
                         imageVector = Icons.Default.MoreVert,
                         contentDescription = "Menu",
                         tint = Hex5d3724,
                         modifier = Modifier.size(24.dp)
                     )
            }
        }

        Spacer(modifier = Modifier.width(16.dp))


        val incomeAmount = transactionList
            .filter { it.type == "income" }
            .sumOf { it.amount.toDouble() }

        val expenseAmount = transactionList
            .filter { it.type == "expense" }
            .sumOf { it.amount.toDouble() }

        val plusTransferAmount = transactionList
            .filter { it.type == "transfer" && it.transferType == "plus" }
            .sumOf { it.amount.toDouble() }

        val minusTransferAmount = transactionList
            .filter { it.type == "transfer" && it.transferType == "minus" }
            .sumOf { it.amount.toDouble() }

        val transferAmount = plusTransferAmount-minusTransferAmount


        Column(
            modifier = Modifier
                .wrapContentWidth()
                .background(color = Hex2b2b2b)
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_chevron_left_24),
                    contentDescription = "chevron_left",
                    tint = Hexe0e0e0,
                    modifier = Modifier
                        .size(34.dp)
                        .padding(end = 10.dp, bottom = 1.dp)
                        .clickable {
                            when (selectedType.value) {
                                "Daily" -> currentDate.value = currentDate.value.minusDays(1)
                                "Weekly" -> currentDate.value = currentDate.value.minusDays(7)
                                "Monthly" -> currentDate.value = currentDate.value.minusMonths(1)
                                "Yearly" -> currentYear.value -= 1
                            }
                        }
                )

                Text(
                    text = accountName.value,
                    style = NotoSerifWithHexFFFFFFFF18sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 1.dp)
                )

                Icon(
                    painter = painterResource(R.drawable.baseline_chevron_right_24),
                    contentDescription = "chevron_right",
                    tint = Hexe0e0e0,
                    modifier = Modifier
                        .size(34.dp)
                        .padding(start = 10.dp, bottom = 1.dp)
                        .clickable {

                            when (selectedType.value) {
                                "Daily" -> currentDate.value = currentDate.value.plusDays(1)
                                "Weekly" -> currentDate.value = currentDate.value.plusDays(7)
                                "Monthly" -> currentDate.value = currentDate.value.plusMonths(1)
                                "Yearly" -> currentYear.value += 1
                            }

                        }
                )
            }

            TwoHorizontalBoxes()

            LazyColumn(modifier = Modifier
                .wrapContentWidth()
                .padding(start = 32.dp, end = 32.dp, top = 16.dp)) {
                item {
                    FinancialItem(incomeAmount,expenseAmount,transferAmount,transactionList)
                }

            }

            Spacer(modifier = Modifier.weight(1f))
            BottomButtons(navHostController)

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
        mutableStateOf(
            getAccountList.firstOrNull { it.primaryAccount }
                ?: getAccountList.getOrNull(0)
        )
    }

    AlertDialog(
        containerColor = Color(0xFFFFFFFF),
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Switch Account",
                style = NotoSerifWithHex39393920sp
            )
        },
        text = {
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
                                selectedAccount = account
                                onAccountSelected(account)
                                onDismiss()
                            }
                        )
                    }
                }

        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel",
                    style = NotoSerifWithHex5d372418sp
                )
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
                    selectedColor =Hex5d3724,
                    unselectedColor = Hex5d3724
                )
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = addAccount.accountName,
                style = NotoSerifWithHex5d372418sp
            )
        }
    }
}




@Composable
fun SelectDateDialog(
    dateList: List<String>,
    onDismiss: () -> Unit,
    selectedType: MutableState<String>
) {

    AlertDialog(
        containerColor = Color(0xFFFFFFFF),
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Show Spending",
                style = NotoSerifWithHex39393920sp
            )
        },
        text = {
            LazyColumn {
                items(dateList) { item ->
                    SelectDateItem(
                        date = item,
                        selectedType = selectedType,
                        onDismiss
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
fun SelectDateItem(date: String, selectedType: MutableState<String>, onDismiss: () -> Unit) {
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

            RadioButton(
                selected = selectedType.value == date,
                onClick = {
                    selectedType.value = date
                    onDismiss() },
                modifier = Modifier.size(24.dp),
                colors = RadioButtonDefaults.colors(
                    selectedColor = Hex5d3724,
                    unselectedColor = Hex5d3724
                )
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = date,
                style = NotoSerifWithHex5d372418sp
            )
        }
    }
}


@Composable
fun FinancialItem(incomeAmount: Double,expenseAmount:Double,transferAmount:Double,transactionList:List<TransactionModel>) {

    val indianFormat = DecimalFormat("#,##,##0.00")

    val balanceAmount = incomeAmount - expenseAmount + transferAmount

    Column(modifier = Modifier.fillMaxWidth()) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Income",
                style = NotoSerifWithHexe0e0e020sp
            )
            Text(
                text = "₹${indianFormat.format(incomeAmount)}",
                style =NotoSerifWithHex33cc4d20sp
            )
        }

        CategorySummary(transactionList,"income")


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Expense",
                style = NotoSerifWithHexe0e0e020sp
            )
            Text(
                text = "₹${indianFormat.format(expenseAmount)}",
                style = NotoSerifWithHex9e3d4620sp
            )
        }

        if (transferAmount!=0.0){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Transfer",
                    style = NotoSerifWithHexe0e0e020sp
                )
                Text(
                    text = if (transferAmount.toString().contains("-")) {
                "- ₹" + transferAmount.toString().replace("-", "")
            } else {
                "+ ₹$transferAmount"
            },
                style = NotoSerifWithHexc1941020sp
                )
            }
        }


        CategorySummary(transactionList,"expense")

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, bottom = 5.dp)
                .height(1.dp)
        ) {
            val boxWidth = 5.dp.toPx()
            val boxHeight = size.height
            val spaceSize = 4.dp.toPx()
            val cornerRadius = 2.dp.toPx()
            val totalWidth = size.width
            var currentX = 0f

            while (currentX < totalWidth) {
                drawRoundRect(
                    color = Color.Gray,
                    topLeft = androidx.compose.ui.geometry.Offset(currentX, 0f),
                    size = androidx.compose.ui.geometry.Size(boxWidth, boxHeight),
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(cornerRadius, cornerRadius)
                )
                currentX += boxWidth + spaceSize
            }
        }



        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Balance",
                style = NotoSerifWithHexe0e0e020sp
            )
            Text(
                text = if (balanceAmount < 0) {
                    "-₹${indianFormat.format(kotlin.math.abs(balanceAmount))}"
                } else {
                    "₹${indianFormat.format(balanceAmount)}"
                },
                style = NotoSerifWithHex7fcbe920sp
            )
        }


    }
}


@Composable
fun CategorySummary(transactionList: List<TransactionModel>,type: String) {
    val filteredTransactionList = transactionList.filter { it.type == type }

    val categorySums = filteredTransactionList
        .groupBy { it.category }
        .mapValues { (_, records) ->
            records.sumOf { it.amount.toDouble() }
        }

    val indianFormat = DecimalFormat("#,##,##0.00")

    Column(modifier = Modifier.fillMaxWidth()) {
        categorySums.forEach { (category, total) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = category,
                    style = NotoSerifWithHexe0e0e020sp,
                    modifier = Modifier
                        .padding(start = 16.dp)
                )
                Text(
                    text = "₹${indianFormat.format(total)}",
                    style = if (type=="income") NotoSerifWithHexb7efca20sp else NotoSerifWithHexc6787120sp
                )
            }


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
            Text("+ Expense", style = NotoSerifWithHexe0e0e020sp)
        }

        Button(
            onClick = {
                navHostController.navigate("${ScreenRoutes.AddTransactionScreen.route}/${"0"}/${"income"}") },
            colors = ButtonDefaults.buttonColors(Color.Transparent, contentColor = HexFFFFFFFF),
            border = BorderStroke(2.dp, HexFFFFFFFF),
            shape = RoundedCornerShape(4.dp),

            ) {
            Text("+ Income", style = NotoSerifWithHexe0e0e020sp)
        }

    }
}




@Composable
fun SetStatusBarColor() {
    val context = LocalContext.current
    val activity = context as Activity
    val window = activity.window
    @Suppress("DEPRECATION")
    window.statusBarColor = Hex5d3724.toArgb()

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val insetsController = window.insetsController
        insetsController?.setSystemBarsAppearance(
            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
        )
    } else {
        @Suppress("DEPRECATION")
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }
}


@Composable
fun TwoHorizontalBoxes() {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(10.dp)
            .border(1.dp, Color.White, RoundedCornerShape(4.dp))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Color.Green,
                    shape = RoundedCornerShape(4.dp)
                )
        ) {
            Box(
                modifier = Modifier
                    .width(23.dp)
                    .height(10.dp)
                    .background(
                        color = Color.Red,
                        shape = RoundedCornerShape(4.dp)
                    )
            )
        }
    }
}


