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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.expensetracker.R
import com.example.expensetracker.navigation.BottomBarRoutes
import com.example.expensetracker.navigation.ScreenRoutes
import com.example.expensetracker.ui.theme.Hex33cc4d
import com.example.expensetracker.ui.theme.Hex674b3f
import com.example.expensetracker.ui.theme.Hex9e3d46
import com.example.expensetracker.ui.theme.Hexc9c6c1
import com.example.expensetracker.ui.theme.Hexdbeed8
import com.example.expensetracker.ui.theme.Hexddd0bf
import com.example.expensetracker.ui.theme.Hexeedad9
import com.example.expensetracker.ui.theme.Hexf1efe3


@Composable
fun NotificationScreen(
    navHostController: NavHostController
) {
    BackHandler {
        navHostController.navigate(BottomBarRoutes.SPENDING_SCREEN.routes){
            popUpTo(BottomBarRoutes.SPENDING_SCREEN.routes){
                inclusive = true
            }
        }
    }

    Column( modifier = Modifier
        .fillMaxSize()
        .background(Hexddd0bf)) {

        TopBarTransactionScreen(navHostController)
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {

                Text("Personal",
                    modifier = Modifier
                    .padding(16.dp))
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Box(
                modifier = Modifier
                    .weight(0.50f)
                    .height(30.dp)
                    .background(Hexdbeed8),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "$ 0.00",
                    color = Hex33cc4d,
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
                    .height(30.dp)
                    .background(Hexeedad9),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "$ 0.00",
                    color = Hex9e3d46,
                    textAlign = TextAlign.Center
                )
            }
        }


        val financialSummary = arrayListOf(
            "Fuel","Food","Drink"
        )

        LazyColumn(modifier = Modifier
            .wrapContentWidth()) {
            financialSummary.forEach{ item ->
                item {
                    ExpenseItem(item)
                }
            }
        }
    }
}

@Composable
fun ExpenseItem(item:String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFDF5E6))
    ) {
        Icon(
            painter = painterResource(id = R.drawable.person_icon),
            contentDescription = item,
            tint = Color.Black,
            modifier = Modifier
                .size(40.dp)
                .padding(start = 16.dp)
                .align(Alignment.CenterVertically)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f)
                .padding(bottom = 5.dp, top = 5.dp)

        ) {
            Text(
                text = item,
                color = Color.Black,
            )
            Text(
                text = "Thursday, 02 Jan 2025",
                color = Color.Gray,
            )
        }

        Text(
            text = "₹ 200.00",
            color = Color(0xFFB22222),
            textAlign = TextAlign.End,
            modifier = Modifier.align(Alignment.CenterVertically)
                .padding(end = 16.dp)
        )
    }
    Spacer(modifier = Modifier
        .fillMaxWidth()
        .height(1.dp)
        .background(Hexc9c6c1))

}

@Composable
fun TopBarTransactionScreen(navHostController: NavHostController) {
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
            Text("January")
        }



        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add",
                tint = Hex674b3f,
                modifier = Modifier.size(34.dp)
                    .clickable { navHostController.navigate(ScreenRoutes.AddTransactionScreen.route)  }
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
