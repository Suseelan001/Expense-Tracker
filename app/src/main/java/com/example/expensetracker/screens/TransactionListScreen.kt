package com.example.expensetracker.screens

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
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
import com.example.expensetracker.model.AddCategory
import com.example.expensetracker.model.TransactionModel
import com.example.expensetracker.navigation.BottomBarRoutes
import com.example.expensetracker.navigation.ScreenRoutes
import com.example.expensetracker.ui.theme.Hex33cc4d
import com.example.expensetracker.ui.theme.Hex5d3724
import com.example.expensetracker.ui.theme.Hex816f64
import com.example.expensetracker.ui.theme.Hex898277
import com.example.expensetracker.ui.theme.Hex9e3d46
import com.example.expensetracker.ui.theme.Hexa7a49b
import com.example.expensetracker.ui.theme.Hexc19410
import com.example.expensetracker.ui.theme.Hexc9c6c1
import com.example.expensetracker.ui.theme.Hexd8eeec
import com.example.expensetracker.ui.theme.Hexdbeed8
import com.example.expensetracker.ui.theme.Hexddd0bf
import com.example.expensetracker.ui.theme.Hexeedad9
import com.example.expensetracker.ui.theme.Hexf1efe3
import com.example.expensetracker.ui.theme.Hexf6e0c8
import com.example.expensetracker.ui.theme.Hexf6f3ea
import com.example.expensetracker.ui.theme.NotoSerifWithHex39393920sp
import com.example.expensetracker.ui.theme.NotoSerifWithHex5d372418sp
import com.example.expensetracker.ui.theme.NotoSerifWithHexe0e0e018sp
import com.example.expensetracker.viewModel.AddAccountViewModel
import com.example.expensetracker.viewModel.AddCategoryViewModel
import com.example.expensetracker.viewModel.AddTransactionViewModel
import java.time.LocalDate
import java.time.Year
import java.time.format.DateTimeFormatter
import java.util.Locale


@Composable
fun TransactionScreen(
    navHostController: NavHostController,
    addTransactionViewModel: AddTransactionViewModel,
    addAccountViewModel: AddAccountViewModel,
    addCategoryViewModel: AddCategoryViewModel

) {



    var showDateSelector by remember { mutableStateOf(false) }
    var showCategorySelector by remember { mutableStateOf(false) }


    val selectedType = remember { mutableStateOf("Monthly") }
    val dateList = listOf("Daily", "Weekly", "Monthly", "Yearly")
    val currentDate = remember { mutableStateOf(LocalDate.now()) }
    val currentYear = remember { mutableIntStateOf(Year.now().value) }
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val accountName = remember { mutableStateOf("") }
    val selectedCategory = remember { mutableStateOf("All Categories") }
    val selectedItems = remember { mutableStateListOf<TransactionModel>() }
    var isSelectionMode by remember { mutableStateOf(false) }



    val getCategoryAllList by addCategoryViewModel.getAll().observeAsState(emptyList())

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


    BackHandler {
        navHostController.navigate(BottomBarRoutes.SPENDING_SCREEN.routes){
            popUpTo(BottomBarRoutes.SPENDING_SCREEN.routes){
                inclusive = true
            }
        }
    }


    val getPrimaryAccount by addAccountViewModel.getPrimaryAccount().observeAsState()


    LaunchedEffect(getPrimaryAccount) {
        getPrimaryAccount?.let {
            if (accountName.value != it.accountName) {
                accountName.value = it.accountName
            }
        }
    }


    if (showDateSelector) {
        SelectDateDialog(
            dateList = dateList,
            onDismiss = { showDateSelector = false },
            selectedType = selectedType
        )
    }

    if (showCategorySelector) {
        SelectCategoryDialog(
            categoryList = getCategoryAllList,
            onDismiss = { showCategorySelector = false },
            clearFilter = {
                selectedCategory.value=""
                showCategorySelector = false
            },
            selectedType = selectedCategory
        )
    }

    val transactionList = when {
        selectedCategory.value == "All Categories" || selectedCategory.value.isEmpty() -> {
            addTransactionViewModel
                .getRecordsByDateRange(accountName.value, startDate, endDate)
                .observeAsState(emptyList()).value
        }
        selectedCategory.value == "All Income" -> {
            addTransactionViewModel
                .getRecordsByDateRangeAndTransactionType(accountName.value, startDate, endDate, "income")
                .observeAsState(emptyList()).value
        }
        selectedCategory.value == "All Expense" -> {
            addTransactionViewModel
                .getRecordsByDateRangeAndTransactionType(accountName.value, startDate, endDate, "expense")
                .observeAsState(emptyList()).value
        }
        else -> {
            addTransactionViewModel
                .getRecordsByDateRangeAndCategoryType(accountName.value, startDate, endDate, selectedCategory.value)
                .observeAsState(emptyList()).value
        }
    }


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
                .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically // Align items vertically in the center
        ) {
            if (selectedItems.isEmpty()) {
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
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add",
                        tint = Hex816f64,
                        modifier = Modifier
                            .size(34.dp)
                            .clickable {
                                navHostController.navigate("${ScreenRoutes.AddTransactionScreen.route}/${"0"}/${"expense"}")
                            }
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "Menu",
                        tint = Hex816f64,
                        modifier = Modifier.size(24.dp)
                    )
                }
            } else

            {
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
                            .clickable {
                                navHostController.popBackStack()
                            }
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "${selectedItems.size} selected",
                        style = NotoSerifWithHex5d372418sp,
                        modifier = Modifier.padding(top = 6.dp, bottom = 6.dp, start = 5.dp)
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (selectedItems.size == 1) {
                        Icon(
                            painter = painterResource(R.drawable.edit),
                            contentDescription = "edit",
                            tint = Hex816f64,
                            modifier = Modifier
                                .size(34.dp)
                                .clickable {
                                    if (selectedItems.size == 1) {
                                        val selectedItem = selectedItems.first()
                                        navHostController.navigate("${ScreenRoutes.AddTransactionScreen.route}/${selectedItem.id}/${""}")
                                    }
                                }
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = Hex816f64,
                        modifier = Modifier.size(24.dp)
                            .clickable {
                                selectedItems.forEach{ item->
                                addTransactionViewModel.deleteSingleRecord(item.id)
                                    selectedItems.remove(item)

                                    if (selectedItems.isEmpty()) {
                                        isSelectionMode = false
                                    }
                                }
                            }
                    )
                }
            }
        }




        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Hexa7a49b))


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Hexf1efe3)
                .padding(start = 8.dp, end = 8.dp, bottom = 5.dp, top = 5.dp),
        ) {
            Icon(
                painter = painterResource(R.drawable.baseline_chevron_left_24),
                contentDescription = "chevron_left",
                tint = Hex816f64,
                modifier = Modifier
                    .size(38.dp)
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
                text = " ",
                style = NotoSerifWithHexe0e0e018sp
            )

            Icon(
                painter = painterResource(R.drawable.baseline_chevron_right_24),
                contentDescription = "chevron_right",
                tint = Hex816f64,
                modifier = Modifier
                    .size(38.dp)
                    .clickable {
                        when (selectedType.value) {
                            "Daily" -> currentDate.value = currentDate.value.plusDays(1)
                            "Weekly" -> currentDate.value = currentDate.value.plusDays(7)
                            "Monthly" -> currentDate.value = currentDate.value.plusMonths(1)
                            "Yearly" -> currentYear.value += 1
                        }

                    }
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                painter = painterResource(R.drawable.monitoring),
                contentDescription = "monitoring",
                tint = Hex816f64,
                modifier = Modifier
                    .size(38.dp)
                    .padding(end = 16.dp)
                    .clickable {
                        navHostController.navigate(ScreenRoutes.PieChartScreen.route)

                    }
            )

            Icon(
                painter = painterResource(R.drawable.filter),
                contentDescription = "filter",
                tint = Hex816f64,
                modifier = Modifier
                    .size(38.dp)
                    .padding(end = 16.dp)
                    .clickable {
                        showCategorySelector = true
                    }
            )


            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "person",
                tint = getPrimaryAccount?.color?.takeIf { it.isNotEmpty() && it.startsWith("#") && it.length == 7 }?.let {
                    Color(android.graphics.Color.parseColor(it))
                } ?: Hex816f64,
                modifier = Modifier
                    .size(38.dp)
            )
        }


        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            if (selectedCategory.value=="All Categories" || selectedCategory.value.isEmpty()){
                Text(accountName.value,
                    modifier = Modifier
                        .padding(bottom=8.dp, top = 8.dp),
                    color= Hex898277)
            }else{
                Text(accountName.value + " , " + selectedCategory.value,
                    modifier = Modifier
                        .padding(bottom=8.dp, top = 8.dp),
                    color= Hex898277)
            }

        }

        Spacer(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(Hexc9c6c1)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Box(
                modifier = Modifier
                    .weight(0.50f)
                    .height(25.dp)
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
                    .height(25.dp)
                    .width(1.dp)
                    .background(Hexc9c6c1)
            )

            Box(
                modifier = Modifier
                    .weight(0.50f)
                    .height(25.dp)
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

        Spacer(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(Hexc9c6c1)
        )


        transactionList.forEach { item ->
            TransactionItem(
                item = item,
                isSelected = selectedItems.contains(item),
                isSelectionMode = isSelectionMode,
                onClick = { clickedItem ->
                    if (!isSelectionMode) {
                        navHostController.navigate("${ScreenRoutes.AddTransactionScreen.route}/${clickedItem.id}/${""}")
                    }
                },
                onLongPress = { longPressedItem ->
                    if (!isSelectionMode) {
                        isSelectionMode = true
                    }
                    if (selectedItems.contains(longPressedItem)) {
                        selectedItems.remove(longPressedItem)
                        if (selectedItems.size==0){
                            isSelectionMode=false
                        }
                    } else {
                        selectedItems.add(longPressedItem)
                    }
                }
            )
        }


    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TransactionItem(
    item: TransactionModel,
    isSelected: Boolean,
    isSelectionMode: Boolean,
    onClick: (TransactionModel) -> Unit,
    onLongPress: (TransactionModel) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(if (isSelected) Hexd8eeec else if (item.type == "transfer") Hexf6e0c8 else Hexf6f3ea)
            .combinedClickable(
                onClick = {
                    if (isSelectionMode) {
                        onLongPress(item)
                    } else {
                        onClick(item)
                    }
                },
                onLongClick = { onLongPress(item) }
            )
    ) {
        Icon(
            painter = painterResource(id = R.drawable.person_icon),
            contentDescription = item.category,
            tint = Color.Black,
            modifier = Modifier
                .padding(start = 16.dp)
                .align(Alignment.CenterVertically)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = item.note.ifEmpty { item.category },
                color = Color.Black,
                style = TextStyle(fontSize = 18.sp),
                modifier = Modifier.padding(top = 2.dp)
            )
            Text(
                text = formatDate(item.date),
                color = Color.Gray,
            )
        }

        Text(
            text = if (item.transferType == "minus") " - ₹" + item.amount else "₹" + item.amount,
            color = when (item.type) {
                "expense" -> Color(0xFFB22222)
                "income" -> Hex33cc4d
                "transfer" -> Hexc19410
                else -> Hex33cc4d
            },
            textAlign = TextAlign.End,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(end = 16.dp)
        )
    }
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Hexc9c6c1)
    )
}

fun formatDate(inputDate: String): String {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH)
    val outputFormatter = DateTimeFormatter.ofPattern("EEEE, dd MMM yyyy", Locale.ENGLISH)

    val date = LocalDate.parse(inputDate, inputFormatter)
    return date.format(outputFormatter)
}

@Composable
fun SelectCategoryDialog(
    categoryList: List<AddCategory>,
    onDismiss: () -> Unit,
    clearFilter: () -> Unit,
    selectedType: MutableState<String>
) {
    val categoryListNew = buildList {
        add(AddCategory(category = "All Categories", categoryType = ""))
        add(AddCategory(category = "All Income", categoryType = ""))
        add(AddCategory(category = "All Expense", categoryType = ""))
        addAll(categoryList)

    }
    AlertDialog(
        containerColor = Color(0xFFFFFFFF),
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Category Filter",
                style = NotoSerifWithHex39393920sp
            )
        },
        text = {
            LazyColumn {
                items(categoryListNew) { item ->
                    SelectDateItem(
                        date = item.category,
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
        },
        dismissButton = {
            TextButton(onClick = {
                clearFilter()
            }) {
                Text("Clear Filter", style = NotoSerifWithHex5d372418sp)
            }
        }
    )

}
