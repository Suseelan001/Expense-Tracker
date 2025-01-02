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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.expensetracker.navigation.BottomBarRoutes
import com.example.expensetracker.navigation.ScreenRoutes
import com.example.expensetracker.ui.theme.Hex33cc4d
import com.example.expensetracker.ui.theme.Hex674b3f
import com.example.expensetracker.ui.theme.HexFFFFFFFF
import com.example.expensetracker.ui.theme.Hexddd0bf
import com.example.expensetracker.ui.theme.Hexdedbd4
import com.example.expensetracker.ui.theme.Hexf1efe3
import com.example.expensetracker.ui.theme.Hexf6f3ea


@Composable
fun AccountScreen(
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

        TopAppBarAccountScreen(navHostController)

        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Button(
                modifier = Modifier
                    .padding(top = 16.dp),
                onClick = { },
                colors = ButtonDefaults.buttonColors(Hex33cc4d, contentColor = HexFFFFFFFF),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text("Transfer")
            }
        }
        val financialSummary = arrayListOf(
          "Personal","Company 1","Company 2"
        )

        LazyColumn(modifier = Modifier
            .wrapContentWidth()
            .padding( top = 16.dp)) {
            financialSummary.forEach{ iteam->
                item {
                    AccountItem(iteam)
                }
            }


        }



    }







}

@Composable
fun TopAppBarAccountScreen(navHostController: NavHostController){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Hexf1efe3),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = {  },
            colors = ButtonDefaults.buttonColors(Color.Transparent, contentColor = Color.Transparent)) {
            Text("")
        }



        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "add",
                tint = Hex674b3f,
                modifier = Modifier.size(39.dp)
                    .clickable { navHostController.navigate(ScreenRoutes.AddAccountScreen.route)  }

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
fun AccountItem(list:String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Hexf6f3ea)
    ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
                    .padding(15.dp)

            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Add Icon",
                    modifier = Modifier.size(24.dp),
                    tint = Hex674b3f
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = list,
                    color = Hex674b3f,
                    style = TextStyle(fontSize = 16.sp)
                )
            }
        Spacer(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(Hexdedbd4)
        )


    }
}