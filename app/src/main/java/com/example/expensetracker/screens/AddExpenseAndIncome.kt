package com.example.expensetracker.screens

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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.expensetracker.R
import com.example.expensetracker.commonText.CommonText
import com.example.expensetracker.navigation.ScreenRoutes
import com.example.expensetracker.ui.theme.Hex164872
import com.example.expensetracker.ui.theme.Hex3d3a35
import com.example.expensetracker.ui.theme.Hex674b3f
import com.example.expensetracker.ui.theme.Hex6a6762
import com.example.expensetracker.ui.theme.Hexc9c6c1
import com.example.expensetracker.ui.theme.Hexd8d5cc
import com.example.expensetracker.ui.theme.Hexddd0bf
import com.example.expensetracker.ui.theme.Hexf1efe3
import com.example.expensetracker.ui.theme.Hexf6f3ea
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


@Composable
fun AddExpenseAndIncome(
    navHostController: NavHostController
) {
Column( modifier = Modifier
    .fillMaxSize()
    .background(Hexddd0bf)) {

    TopAppBarAddExpense(navHostController)
    AddDetail( navHostController)


    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarAddExpense(navHostController: NavHostController){
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Hexf1efe3,
            titleContentColor = Color.White
        ),
        title = { Text("") },
        navigationIcon = {
            IconButton(onClick = { navHostController.popBackStack()}) {
                Icon(
                    painter = painterResource(R.drawable.close),
                    contentDescription = "close",
                    tint = Hex674b3f,
                    modifier = Modifier.size(35.dp)
                )                }
        },
        actions = {
            TextButton(onClick = { /* Handle done action */ }) {
                Text("Done", color = Hex674b3f)
            }
        },
    )

}

@Composable
fun AddDetail( navHostController: NavHostController) {
    val clickedButton = remember { mutableStateOf("expense") }
    var showDatePicker by remember { mutableStateOf(false) }
     val mContext= LocalContext.current
    val currentDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
    var selectedDatefortask by remember { mutableStateOf(currentDate) }
    var amount by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = {
                clickedButton.value = "expense"
            },
            colors = ButtonDefaults.buttonColors(if (clickedButton.value == "expense") Hex674b3f else Color.Transparent,
                contentColor = if (clickedButton.value == "expense") Color.White else Hex674b3f
            ),
            border = BorderStroke(1.dp, Hex674b3f),
            shape = RoundedCornerShape(
                topStart = 4.dp,
                topEnd = 0.dp,
                bottomStart = 4.dp,
                bottomEnd = 0.dp
            ),
        ) {
            Text("EXPENSE", letterSpacing = 2.sp)
        }

        Button(
            onClick = {
                clickedButton.value = "income"
            },
            colors = ButtonDefaults.buttonColors(if (clickedButton.value == "income") Hex674b3f else Color.Transparent,
                contentColor = if (clickedButton.value == "income") Color.White else Hex674b3f
            ),
            border = BorderStroke(1.dp, Hex674b3f),
            shape = RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 4.dp,
                bottomStart = 0.dp,
                bottomEnd = 4.dp
            ),
        ) {
            Text("INCOME", letterSpacing = 2.sp)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Hexd8d5cc)
    ){
        Text("Transaction Details", modifier = Modifier.padding(start = 16.dp, top = 5.dp, bottom = 5.dp), color = Hex6a6762)
    }


    Column(
        modifier = Modifier
            .background(Hexf6f3ea)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Text(
                text = "Date",
                color = Hex164872,
                modifier = Modifier
                    .padding(5.dp)
                    .weight(0.30f),
                textAlign = TextAlign.End
            )

            Spacer(
                modifier = Modifier
                    .height(34.dp)
                    .width(1.dp)
                    .background(Hexc9c6c1)
            )

            Text(
                text = selectedDatefortask,
                color = Hex3d3a35,
                modifier = Modifier
                    .padding(5.dp)
                    .weight(0.70f)
                    .clickable {
                        showDatePicker = true
                    }
            )
        }

        if (showDatePicker) {
            val currentCalendar = Calendar.getInstance()
            val year = currentCalendar.get(Calendar.YEAR)
            val month = currentCalendar.get(Calendar.MONTH)
            val dayOfMonth = currentCalendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = android.app.DatePickerDialog(
                mContext,
                { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                    val formattedDate = String.format("%02d/%02d/%04d", selectedDayOfMonth, selectedMonth + 1, selectedYear)
                    selectedDatefortask = formattedDate
                    showDatePicker = false
                },
                year, month, dayOfMonth
            )


            datePickerDialog.setOnCancelListener {
                showDatePicker = false
            }

            datePickerDialog.show()
        }


        Spacer(
            modifier = Modifier
                .height(1.dp)
                .background(Hexc9c6c1)
                .fillMaxWidth()
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Text(
                text = "Category",
                color = Hex164872,
                modifier = Modifier
                    .padding(5.dp)
                    .weight(0.30f),
                textAlign = TextAlign.End
            )

            Spacer(
                modifier = Modifier
                    .height(34.dp)
                    .width(1.dp)
                    .background(Hexc9c6c1)
            )

            Text(
                text = "Not Selected",
                color = Hex3d3a35,
                modifier = Modifier
                    .padding(5.dp)
                    .weight(0.70f)
                    .clickable {
                        navHostController.navigate(ScreenRoutes.CategoriesScreen.route)                     }
            )
        }

        Spacer(
            modifier = Modifier
                .height(1.dp)
                .background(Hexc9c6c1)
                .fillMaxWidth()
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Text(
                text = "Amount",
                color = Hex164872,
                modifier = Modifier
                    .padding(5.dp)
                    .weight(0.30f),
                textAlign = TextAlign.End
            )

            Spacer(
                modifier = Modifier
                    .height(34.dp)
                    .width(1.dp)
                    .background(Hexc9c6c1)
            )

            BasicTextField(
                modifier = Modifier
                    .weight(0.70f)
                    .padding(5.dp),
                value = amount,
                onValueChange = { newAmount ->
                    amount = newAmount
                },
                textStyle = TextStyle(color = Hex3d3a35),
                singleLine = true,
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier
                            .background(Color.Transparent),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (amount.isEmpty()) {
                            Text(
                                text = "Enter Amount",
                                color = Hexc9c6c1,
                            )
                        }
                        innerTextField()
                    }
                }
            )
        }

        Spacer(
            modifier = Modifier
                .height(1.dp)
                .background(Hexc9c6c1)
                .fillMaxWidth()
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Text(
                text = "Account",
                color = Hex164872,
                modifier = Modifier
                    .padding(5.dp)
                    .weight(0.30f),
                textAlign = TextAlign.End
            )

            Spacer(
                modifier = Modifier
                    .height(34.dp)
                    .width(1.dp)
                    .background(Hexc9c6c1)
            )

            Text(
                text = "Personal",
                color = Hex3d3a35,
                modifier = Modifier
                    .padding(5.dp)
                    .weight(0.70f)
            )
        }

        Spacer(
            modifier = Modifier
                .height(1.dp)
                .background(Hexc9c6c1)
                .fillMaxWidth()
        )
    }


}








