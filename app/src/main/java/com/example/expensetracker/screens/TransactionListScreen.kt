package com.example.expensetracker.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.expensetracker.R
import com.example.expensetracker.model.TransactionModel
import com.example.expensetracker.navigation.BottomBarRoutes
import com.example.expensetracker.navigation.ScreenRoutes
import com.example.expensetracker.ui.theme.Hex33cc4d
import com.example.expensetracker.ui.theme.Hex3d3a35
import com.example.expensetracker.ui.theme.Hex674b3f
import com.example.expensetracker.ui.theme.Hex9e3d46
import com.example.expensetracker.ui.theme.HexFFFFFFFF
import com.example.expensetracker.ui.theme.Hexc9c6c1
import com.example.expensetracker.ui.theme.Hexdbeed8
import com.example.expensetracker.ui.theme.Hexddd0bf
import com.example.expensetracker.ui.theme.Hexeedad9
import com.example.expensetracker.ui.theme.Hexf1efe3
import com.example.expensetracker.ui.theme.HonchokomonoWithHexe0e0e018sp
import com.example.expensetracker.viewModel.AddAccountViewModel
import com.example.expensetracker.viewModel.AddTransactionViewModel
import java.time.LocalDate
import java.time.Year
import java.time.format.DateTimeFormatter
import java.util.Locale


@Composable
fun TransactionScreen(
    navHostController: NavHostController,
    addTransactionViewModel: AddTransactionViewModel,
    addAccountViewModel: AddAccountViewModel

) {
    val months = listOf(
        "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    )

    val currentMonthIndex = remember { mutableIntStateOf(LocalDate.now().monthValue - 1) }
    val currentYear = remember { mutableIntStateOf(Year.now().value) }

    val monthYear = remember(currentMonthIndex.intValue, currentYear.intValue) {
        "${months[currentMonthIndex.intValue]} ${currentYear.intValue}"
    }

    BackHandler {
        navHostController.navigate(BottomBarRoutes.SPENDING_SCREEN.routes){
            popUpTo(BottomBarRoutes.SPENDING_SCREEN.routes){
                inclusive = true
            }
        }
    }

    val accountType = remember { mutableStateOf("") }

    val getPrimaryAccount by addAccountViewModel.getPrimaryAccount().observeAsState()


    LaunchedEffect(getPrimaryAccount) {
        getPrimaryAccount?.let {
            if (accountType.value != it.accountName) {
                accountType.value = it.accountName
            }
        }
    }

   // val transactionList by addTransactionViewModel.getRecordsByType(accountType.value).observeAsState(emptyList())

    val transactionList by addTransactionViewModel.getRecordsByTypeAndMonth(accountType.value,monthYear).observeAsState(emptyList())


    val totalExpense = transactionList
        .filter { it.type == "expense" }
        .sumOf { it.amount.toDouble() }

    val totalIncome = transactionList
        .filter { it.type == "income" }
        .sumOf { it.amount.toDouble() }

    Column( modifier = Modifier
        .fillMaxSize()
        .background(Hexddd0bf)) {


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Hexf1efe3)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {  },
                colors = ButtonDefaults.buttonColors(Color.Transparent, contentColor = Hex674b3f),
                border = BorderStroke(2.dp, Hex674b3f),
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier
                    .width(120.dp)
                    .wrapContentHeight()

                ) {
                Text(
                    text = if (currentYear.intValue == Year.now().value) {
                        months[currentMonthIndex.intValue]
                    } else {
                        "${(months[currentMonthIndex.intValue]).take(3)} ${currentYear.intValue}"
                    },
                )
            }



            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                    tint = Hex674b3f,
                    modifier = Modifier
                        .size(34.dp)
                        .clickable {
                            navHostController.navigate("${ScreenRoutes.AddTransactionScreen.route}/${"0"}/${"expense"}")                     }
                )

                Spacer(modifier = Modifier.width(16.dp))

/*                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Menu",
                    tint = Hex674b3f,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))*/

            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(HexFFFFFFFF)
                .padding(start = 16.dp, end = 16.dp, bottom = 1.dp),
        ) {
            Icon(
                painter = painterResource(R.drawable.baseline_chevron_left_24),
                contentDescription = "chevron_left",
                tint = Hex3d3a35,
                modifier = Modifier
                    .size(45.dp)
                    .clickable {
                        if (currentMonthIndex.intValue == 0) {
                            currentMonthIndex.intValue = months.size - 1
                            currentYear.value -= 1
                        } else {
                            currentMonthIndex.value -= 1
                        }
                    }
            )

            Text(
                text = " ",
                style = HonchokomonoWithHexe0e0e018sp
            )

            Icon(
                painter = painterResource(R.drawable.baseline_chevron_right_24),
                contentDescription = "chevron_right",
                tint = Hex3d3a35,
                modifier = Modifier
                    .size(45.dp)
                    .clickable {
                        if (currentMonthIndex.intValue == months.size - 1) {
                            currentMonthIndex.intValue = 0
                            currentYear.value += 1
                        } else {
                            currentMonthIndex.value += 1
                        }
                    }
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "person",
                tint = getPrimaryAccount?.color?.takeIf { it.isNotEmpty() && it.startsWith("#") && it.length == 7 }?.let {
                    Color(android.graphics.Color.parseColor(it))
                } ?: Color(android.graphics.Color.parseColor("#674b3f")),
                modifier = Modifier
                    .size(45.dp)
                    .padding(end = 16.dp)
            )
        }


        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
                Text(accountType.value,
                    modifier = Modifier
                    .padding(bottom=16.dp, top = 16.dp),
                    color= HexFFFFFFFF)
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Box(
                modifier = Modifier
                    .weight(0.50f)
                    .height(40.dp)
                    .background(Hexdbeed8),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "₹ $totalIncome",
                    color = Hex33cc4d,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(
                modifier = Modifier
                    .height(34.dp)
                    .width(1.dp)
                    .background(Hexc9c6c1)
            )

            Box(
                modifier = Modifier
                    .weight(0.50f)
                    .height(40.dp)
                    .background(Hexeedad9),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "₹ $totalExpense",
                    color = Hex9e3d46,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
            transactionList.forEach{ item ->
                    TransactionItem(item,
                    onClick = { selectedAccount ->
                        navHostController.navigate("${ScreenRoutes.AddTransactionScreen.route}/${selectedAccount.id}/${""}")

                          }
                    )
            }

    }
}

@Composable
fun TransactionItem(item: TransactionModel, onClick: (TransactionModel) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFDF5E6))
            .clickable { onClick(item) }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.person_icon),
            contentDescription = item.category,
            tint = Color.Black,
            modifier = Modifier
                .size(40.dp)
                .padding(start = 16.dp)
                .align(Alignment.CenterVertically)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = 5.dp, top = 5.dp)

        ) {
            Text(
                text = item.category,
                color = Color.Black,
            )
            Text(
                text =formatDate(item.date),
                color = Color.Gray,
            )
        }

        Text(
            text = "₹ " + item.amount,
            color = if (item.type == "expense")  Color(0xFFB22222) else  Hex33cc4d ,
            textAlign = TextAlign.End,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(end = 16.dp)
        )
    }
    Spacer(modifier = Modifier
        .fillMaxWidth()
        .height(1.dp)
        .background(Hexc9c6c1))

}

fun formatDate(inputDate: String): String {
    val inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH)
    val outputFormatter = DateTimeFormatter.ofPattern("EEEE, dd MMM yyyy", Locale.ENGLISH)

    val date = LocalDate.parse(inputDate, inputFormatter)
    return date.format(outputFormatter)
}


