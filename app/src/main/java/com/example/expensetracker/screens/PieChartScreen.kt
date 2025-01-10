package com.example.expensetracker.screens

import android.graphics.Paint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.expensetracker.R
import com.example.expensetracker.commonClass.CommonClass
import com.example.expensetracker.model.TransactionModel
import com.example.expensetracker.ui.theme.Hex292929
import com.example.expensetracker.ui.theme.Hex3d3a35
import com.example.expensetracker.ui.theme.Hex674b3f
import com.example.expensetracker.ui.theme.HexFFFFFFFF
import com.example.expensetracker.ui.theme.Hexddd0bf
import com.example.expensetracker.viewModel.AddAccountViewModel
import com.example.expensetracker.viewModel.AddTransactionViewModel
import java.time.LocalDate
import java.time.Year
import kotlin.math.min


@Composable
fun PieChartScreen(
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
    val clickedButton = remember { mutableStateOf("expense") }

    val accountType = remember { mutableStateOf("") }

    val getPrimaryAccount by addAccountViewModel.getPrimaryAccount().observeAsState()


    LaunchedEffect(getPrimaryAccount) {
        getPrimaryAccount?.let {
            if (accountType.value != it.accountName) {
                accountType.value = it.accountName
            }
        }
    }

    val transactionList by addTransactionViewModel
        .getRecordsByDateRange("accountName","endDate","startDate")
        .observeAsState(emptyList())




    Column( modifier = Modifier
        .fillMaxSize()
        .background(Hexddd0bf)) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(HexFFFFFFFF)
                .padding(16.dp),
        ) {

            Icon(
                painter = painterResource(R.drawable.baseline_arrow_back_24),
                contentDescription = "backIcon",
                tint = Hex292929,
                modifier = Modifier
                    .size(45.dp)
                    .padding(end = 16.dp)
                    .clickable {
                        navHostController.popBackStack()

                    }
            )

            Spacer(modifier = Modifier.weight(1f))



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

            Button(
                onClick = { /* Handle button click */ },
                colors = ButtonDefaults.buttonColors(Color.Transparent, contentColor = Color(android.graphics.Color.parseColor("#674b3f"))),
                border = BorderStroke(2.dp, Color(android.graphics.Color.parseColor("#674b3f"))),
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
                )              }

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



        }

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


        if (transactionList.isNotEmpty()){
            PieChartScreen(sumByAllCategories(transactionList))
            val common = CommonClass()
            val chartColors = common.getChartColors(transactionList.size)

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val entries = sumByAllCategories(transactionList).entries.toList()

                items(entries.zip(chartColors)) { (entry, color) ->
                        BoxWithText(color,entry)
                }
            }
        }
    }
}

@Composable
fun BoxWithText(color: String, entry: Map.Entry<String, Double>) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .background(Color(android.graphics.Color.parseColor(color)))
        )

        Text(
            text = " ${entry.key}",
            color= Hex292929,
            modifier = Modifier
                .wrapContentWidth(),
        )
        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "₹${entry.value}",
            color= Hex292929,
            modifier = Modifier
                .wrapContentWidth()
                .padding(end = 16.dp),
        )
    }

    Spacer(modifier = Modifier
        .fillMaxWidth()
        .height(.5.dp)
        .background(Hex292929))
}



fun sumByAllCategories(transactions: List<TransactionModel>): Map<String, Double> {
    return transactions
        .groupBy { it.category }
        .mapValues { entry -> entry.value.sumOf { it.amount.toDouble() } }
}


@Composable
fun PieChartScreen(categoryTotals: Map<String, Double>) {

    val numberOfCategories = categoryTotals.size
    val common = CommonClass()
    val chartColors = common.getChartColors(numberOfCategories)
    val chartValues = categoryTotals.values.map { it.toFloat() }
    val categoryNames = categoryTotals.keys.toList()

    PieChart(
        modifier = Modifier.padding(20.dp),
        colors = chartColors,
        inputValues = chartValues,
        labels = categoryNames
    )

}

@Composable
fun PieChart(
    modifier: Modifier = Modifier,
    colors: List<String>,
    inputValues: List<Float>,
    labels: List<String>
) {
    require(colors.size == inputValues.size) { "Colors and input values must have the same size." }
    require(inputValues.isNotEmpty()) { "Input values must not be empty." }
    require(labels.size == inputValues.size) { "Labels and input values must have the same size." }

    val density = LocalDensity.current
    val textFontSize = with(density) { 14.dp.toPx() }
    val textPaint = remember {
        Paint().apply {
            textSize = textFontSize
            textAlign = Paint.Align.CENTER
            color = android.graphics.Color.BLACK
        }
    }
    val proportions = inputValues.map { it / inputValues.sum() * 100 }

    val formattedProportions = proportions.map { proportion ->
        if ((proportion % 1).toDouble() == 0.0) {
            proportion.toInt().toString()
        } else {
            String.format("%.2f", proportion)
        }
    }

    val angles = inputValues.map { it / inputValues.sum() * 360 }

    BoxWithConstraints(modifier = modifier, contentAlignment = Alignment.Center) {
        val canvasSize = min(constraints.maxWidth, constraints.maxHeight)
        val size = Size(canvasSize.toFloat(), canvasSize.toFloat())
        val radius = canvasSize / 2f

        val canvasSizeDp = with(density) { canvasSize.toDp() }

        Canvas(
            modifier = Modifier
                .size(canvasSizeDp)
        ) {
            var startAngle = 180f
            angles.forEachIndexed { index, angle ->
                drawArc(
                    color = Color(android.graphics.Color.parseColor(colors[index])),
                    startAngle = startAngle,
                    sweepAngle = angle,
                    useCenter = true,
                    size = size,
                    style = Fill
                )

                val middleAngle = Math.toRadians((startAngle + angle / 2).toDouble())
                val labelX = (radius * 0.6 * kotlin.math.cos(middleAngle) + radius).toFloat()
                val labelY = (radius * 0.6 * kotlin.math.sin(middleAngle) + radius).toFloat()

                drawIntoCanvas { canvas ->
                    canvas.nativeCanvas.drawText(
                        "${labels[index]} ${formattedProportions[index]}%",
                        labelX,
                        labelY,
                        textPaint
                    )
                }
                startAngle += angle
            }
        }

    }

}






