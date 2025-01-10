package com.example.expensetracker.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.expensetracker.R
import com.example.expensetracker.model.AddAccount
import com.example.expensetracker.model.TransactionModel
import com.example.expensetracker.navigation.ScreenRoutes
import com.example.expensetracker.ui.theme.Hex164872
import com.example.expensetracker.ui.theme.Hex3d3a35
import com.example.expensetracker.ui.theme.Hex5d3724
import com.example.expensetracker.ui.theme.Hex674b3f
import com.example.expensetracker.ui.theme.Hex6a6762
import com.example.expensetracker.ui.theme.Hex9e3d46
import com.example.expensetracker.ui.theme.HexFFFFFFFF
import com.example.expensetracker.ui.theme.Hexc9c6c1
import com.example.expensetracker.ui.theme.Hexd8d5cc
import com.example.expensetracker.ui.theme.Hexddd0bf
import com.example.expensetracker.ui.theme.Hexe2cdb8
import com.example.expensetracker.ui.theme.Hexf6f3ea
import com.example.expensetracker.viewModel.AddAccountViewModel
import com.example.expensetracker.viewModel.AddTransactionViewModel
import com.example.expensetracker.viewModel.MainViewModel
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale


@Composable
fun AddExpenseAndIncome(
    navHostController: NavHostController,
    addTransactionViewModel:AddTransactionViewModel,
    mainViewModel: MainViewModel,
    accountId:String,
    type:String,
    addAccountViewModel: AddAccountViewModel
) {
    val context= LocalContext.current

    val getAccountList by addAccountViewModel.getAllRecord().observeAsState(emptyList())


    Column( modifier = Modifier
        .fillMaxSize()
        .background(Hexddd0bf)) {

    AddDetail( navHostController,addTransactionViewModel,mainViewModel,accountId,type,getAccountList,addAccountViewModel)
    Spacer(modifier = Modifier
        .padding(top = 16.dp))

    if (accountId.toInt()>0) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Button(
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 16.dp),
                onClick = {
                    addTransactionViewModel.deleteSingleRecord(accountId.toInt())
                    Toast.makeText(context, "Your account has been deleted", Toast.LENGTH_SHORT).show()
                    navHostController.popBackStack()
                },
                colors = ButtonDefaults.buttonColors(Hex9e3d46, contentColor = HexFFFFFFFF),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text("Delete")
            }
        }
    }



}


}








@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDetail( navHostController: NavHostController,
               addTransactionViewModel:AddTransactionViewModel,
               mainViewModel:MainViewModel,
               accountId:String,type:String,
               getAccountList:List<AddAccount>,addAccountViewModel:AddAccountViewModel) {


    val clickedButton = remember { mutableStateOf(type) }





    var showDatePicker by remember { mutableStateOf(false) }
     val mContext= LocalContext.current
    val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
    var selectedDate by remember { mutableStateOf(currentDate) }
    var amount by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }
    val taskLoaded = remember { mutableStateOf(false) }
    var category by remember { mutableStateOf("") }
    var accountName by remember { mutableStateOf("") }

    if (mainViewModel.selectedCategory!=""){
        category=  mainViewModel.selectedCategory

    }

    var showColorPicker by remember { mutableStateOf(false) }
    val getPrimaryAccount by addAccountViewModel.getPrimaryAccount().observeAsState()

    LaunchedEffect(getPrimaryAccount) {
        getPrimaryAccount?.let {
            if (accountName != it.accountName) {
                accountName = it.accountName
            }
        }
    }

    if (accountId.toInt()>0){
        val getTransactionRecord by addTransactionViewModel.getSingleRecord(accountId.toInt()).observeAsState()
        if (getTransactionRecord != null && !taskLoaded.value) {
            selectedDate = getTransactionRecord?.date ?: ""
            if(mainViewModel.enteredAmount.isEmpty()){
                amount = getTransactionRecord?.amount ?: "0.00"
            }
            category = getTransactionRecord?.category ?: ""
            clickedButton.value = getTransactionRecord?.type ?: ""
            taskLoaded.value = true
        }
    }

    if(mainViewModel.enteredAmount.isNotEmpty()){
        amount = mainViewModel.enteredAmount
    }
    if (mainViewModel.selectedTransactionType.isNotEmpty()){
        clickedButton.value=mainViewModel.selectedTransactionType
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(android.graphics.Color.parseColor("#f1efe3")))
            .padding(start = 14.dp, end = 16.dp, top = 5.dp, bottom = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { navHostController.popBackStack()}) {
            Icon(
                painter = painterResource(R.drawable.close),
                contentDescription = "close",
                tint = Hex674b3f,
                modifier = Modifier.size(24.dp)
            )
        }


        TextButton(onClick = {
            if (amount.isEmpty()){
                Toast.makeText(mContext, "Enter amount", Toast.LENGTH_SHORT).show()
            }else
                if (category.isEmpty()){
                    Toast.makeText(mContext, "select category", Toast.LENGTH_SHORT).show()
                }else
                    if (!isAmountInt(amount)){
                        Toast.makeText(mContext, "Please enter amount in number", Toast.LENGTH_SHORT).show()
                    } else {
                        val addTransactionModel = TransactionModel(date=selectedDate,category=category,
                            amount = amount, account = accountName,
                            type = clickedButton.value,
                            note = note)

                        if (accountId.toInt()>0){
                            val updatedTransaction = addTransactionModel.copy(id = accountId.toInt())
                            addTransactionViewModel.updateRecord(updatedTransaction)
                            Toast.makeText(mContext, "Your transaction has been updated", Toast.LENGTH_SHORT).show()
                        }else{

                            addTransactionViewModel.insertAccount(addTransactionModel)
                            Toast.makeText(mContext, "Your transaction has been added", Toast.LENGTH_SHORT).show()
                        }
                        navHostController.popBackStack()
                    }

        }) {
            Text(
                text = "Done",
                color = Hex5d3724
            )
        }

    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = {
                clickedButton.value = "expense"
                mainViewModel.selectedTransactionType = "expense"
            },
            colors = ButtonDefaults.buttonColors(if (clickedButton.value == "expense") Hex674b3f else Color.Transparent,
                contentColor = if (clickedButton.value == "expense") Hexe2cdb8 else Hex674b3f
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
                mainViewModel.selectedTransactionType = "income"
            },
            colors = ButtonDefaults.buttonColors(if (clickedButton.value == "income") Hex674b3f else Color.Transparent,
                contentColor = if (clickedButton.value == "income") Hexe2cdb8 else Hex674b3f
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

    Spacer(
        modifier = Modifier
            .height(1.dp)
            .background(Hexc9c6c1)
            .fillMaxWidth()
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Hexd8d5cc)
    ){
        Text("Transaction Details",
            modifier = Modifier
                .padding(start = 16.dp, top = 2.dp, bottom = 2.dp),
            color = Hex6a6762)
    }
    Spacer(
        modifier = Modifier
            .height(1.dp)
            .background(Hexc9c6c1)
            .fillMaxWidth()
    )

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
                    .padding(top = 8.dp, bottom = 8.dp, end = 8.dp)
                    .weight(0.30f),
                textAlign = TextAlign.End
            )

            Spacer(
                modifier = Modifier
                    .height(40.dp)
                    .width(1.dp)
                    .background(Hexc9c6c1)
            )

            Text(
                text = selectedDate,
                color = Hex3d3a35,
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp, start = 5.dp)
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
                    selectedDate = formattedDate
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
                    .padding(top = 8.dp, bottom = 8.dp, end = 8.dp)
                    .weight(0.30f),
                textAlign = TextAlign.End
            )

            Spacer(
                modifier = Modifier
                    .height(40.dp)
                    .width(1.dp)
                    .background(Hexc9c6c1)
            )

            Text(
                text = (category.ifEmpty { "Not Selected" }),
                color =  if (category.isEmpty())Hexc9c6c1  else  Hex3d3a35   ,
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp, start = 8.dp)
                    .weight(0.70f)
                    .clickable {
                        navHostController.navigate("${ScreenRoutes.SelectCategoriesScreen.route}/${clickedButton.value}")
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
                text = "Amount",
                color = Hex164872,
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp, end = 8.dp)
                    .weight(0.30f),
                textAlign = TextAlign.End
            )

            Spacer(
                modifier = Modifier
                    .height(40.dp)
                    .width(1.dp)
                    .background(Hexc9c6c1)
            )




            BasicTextField(
                modifier = Modifier
                    .weight(0.70f)
                    .padding(top = 8.dp, bottom = 8.dp, start = 8.dp)
                    .fillMaxWidth(),
                value =amount,
                onValueChange = { newAmount ->
                    amount = newAmount.filter { it.isDigit()
                    }
                    mainViewModel.enteredAmount=newAmount

                },
                textStyle = TextStyle(
                    color = Hex3d3a35,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(IntrinsicSize.Min)
                            .padding(vertical = 1.dp)
                    ) {
                        if (amount.isEmpty()) {
                            Text(
                                text = "Enter Amount",
                                color = Hexc9c6c1,
                                modifier = Modifier.align(Alignment.CenterStart),
                                style = TextStyle(fontSize = 16.sp)
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
                    .padding(top = 8.dp, bottom = 8.dp, end = 8.dp)
                    .weight(0.30f),
                textAlign = TextAlign.End
            )

            Spacer(
                modifier = Modifier
                    .height(40.dp)
                    .width(1.dp)
                    .background(Hexc9c6c1)
            )

            Text(
                text = accountName,
                color = Hex3d3a35,
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp, start = 8.dp)
                    .weight(0.70f)
                    .clickable {
                        showColorPicker = true
                    }
            )
        }

        Spacer(
            modifier = Modifier
                .height(1.dp)
                .background(Hexc9c6c1)
                .fillMaxWidth()
        )




        if (showColorPicker) {
            SelectAccount(
                addAccountViewModel = addAccountViewModel,
                getAccountList = getAccountList,
                onAccountSelected = { selectedAccount ->
                    accountName=selectedAccount.accountName
                    showColorPicker = false
                },
                onDismiss = { showColorPicker = false }
            )
        }

    }




        Spacer(
            modifier = Modifier
                .padding(top = 16.dp)
                .height(1.dp)
                .background(Color.Transparent)
                .fillMaxWidth()
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Hexf6f3ea)

        ) {
            Text(
                text = "Note",
                color = Hex164872,
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp, end = 8.dp)
                    .weight(0.30f),
                textAlign = TextAlign.End
            )

            Spacer(
                modifier = Modifier
                    .height(40.dp)
                    .width(1.dp)
                    .background(Hexc9c6c1)
            )

            BasicTextField(
                modifier = Modifier
                    .weight(0.70f)
                    .padding(top = 8.dp, bottom = 8.dp, start = 8.dp)
                    .fillMaxWidth(),
                value = note,
                onValueChange = { newAmount ->
                    note = newAmount
                },
                textStyle = TextStyle(
                    color = Hex3d3a35,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(IntrinsicSize.Min)
                            .padding(vertical = 1.dp)
                    ) {
                        if (note.isEmpty()) {
                            Text(
                                text = "No Note Entered",
                                color = Hexc9c6c1,
                                modifier = Modifier.align(Alignment.CenterStart),
                                style = TextStyle(fontSize = 16.sp)
                            )
                        }
                        innerTextField()
                    }
                }
            )

        }


    }






fun formatToMonthYear(dateString: String): String {
    val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val date = LocalDate.parse(dateString, dateFormat)
    val month = date.month.getDisplayName(java.time.format.TextStyle.FULL, Locale.getDefault())
    val year = date.year
    return "$month $year"
}

fun isAmountInt(amount: String): Boolean {
    return amount.toIntOrNull() != null
}
@Composable
fun SelectAccount(
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
        titleContentColor = Color(0xFF3D3A35),
        onDismissRequest = onDismiss,
        title = { Text(text = "Select Account") },
        text = {
            if (getAccountList.isEmpty()) {
                Text(text = "No accounts available.")
            } else {
                LazyColumn {
                    items(getAccountList) { item ->
                        SelectAccountItemForExpense(
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
fun SelectAccountItemForExpense(addAccount: AddAccount, onClick: (AddAccount) -> Unit, selectedAccount: AddAccount?) {
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






