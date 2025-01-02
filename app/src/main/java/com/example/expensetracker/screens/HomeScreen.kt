package com.example.expensetracker.screens

import android.app.Activity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.expensetracker.R
import com.example.expensetracker.navigation.ScreenRoutes
import com.example.expensetracker.ui.theme.Hex674b3f
import com.example.expensetracker.ui.theme.HexFFFFFFFF
import com.example.expensetracker.ui.theme.Hexe0e0e0
import com.example.expensetracker.ui.theme.Hexf1efe3
import com.example.expensetracker.ui.theme.HonchokomonoWithHexe0e0e018sp
import com.example.expensetracker.ui.theme.HonchokomonoWithHexe0e0e020sp


@Composable
fun HomeScreen(
    navHostController: NavHostController,
    context: Activity
) {

    Column (modifier = Modifier
            .fillMaxWidth()) {
        TopBar()

        Spacer(modifier = Modifier.width(16.dp))

        DashboardDetails(navHostController)


    }

}


@Composable
fun TopBar() {
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

            ) {
            Text("December")
        }



        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Profile",
                tint = Hex674b3f,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "Menu",
                tint = Hex674b3f,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))

        }
    }
}







@Composable
fun DashboardDetails( navHostController: NavHostController) {
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
                modifier = Modifier.size(24.dp).padding(end = 5.dp)
            )

            Text(
                text = "Personal",
                style = HonchokomonoWithHexe0e0e018sp
            )

            Icon(
                painter = painterResource(R.drawable.baseline_chevron_right_24),
                contentDescription = "chevron_right",
                tint = Hexe0e0e0,
                modifier = Modifier.size(24.dp).padding(start = 5.dp)
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

        val financialSummary = arrayListOf(
            mapOf("label" to "Income", "amount" to 10000),
            mapOf("label" to "Expense", "amount" to 1000),
            mapOf("label" to "Balance", "amount" to 9000)
        )



        LazyColumn(modifier = Modifier.wrapContentWidth().padding(start = 32.dp, end = 32.dp, top = 16.dp)) {
            item {
                FinancialItem(financialSummary)
            }

        }

        Spacer(modifier = Modifier.weight(1f))
        BottomButtons(navHostController)

    }
}

@Composable
fun FinancialItem(financialSummary: List<Map<String, Any>>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        financialSummary.forEachIndexed { index, item ->
            if (item["label"] == "Balance") {
                Spacer(modifier = Modifier.height(8.dp))

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

                Spacer(modifier = Modifier.height(8.dp))
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = item["label"].toString(),
                    style = HonchokomonoWithHexe0e0e020sp
                )
                Text(
                    text = item["amount"].toString(),
                    style = HonchokomonoWithHexe0e0e020sp
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
            onClick = { navHostController.navigate(ScreenRoutes.Detail.route) },
            colors = ButtonDefaults.buttonColors(Color.Transparent, contentColor = HexFFFFFFFF),
            border = BorderStroke(2.dp, HexFFFFFFFF),
            shape = RoundedCornerShape(4.dp),

            ) {
            Text("+ Expense")
        }

        Button(
            onClick = { navHostController.navigate(ScreenRoutes.Detail.route) },
            colors = ButtonDefaults.buttonColors(Color.Transparent, contentColor = HexFFFFFFFF),
            border = BorderStroke(2.dp, HexFFFFFFFF),
            shape = RoundedCornerShape(4.dp),

            ) {
            Text("+ Income")
        }

    }
}
