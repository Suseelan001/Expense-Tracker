package com.example.expensetracker.screens

import androidx.activity.compose.BackHandler
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.expensetracker.model.AddAccount
import com.example.expensetracker.navigation.BottomBarRoutes
import com.example.expensetracker.navigation.ScreenRoutes
import com.example.expensetracker.ui.theme.Hex33cc4d
import com.example.expensetracker.ui.theme.Hex674b3f
import com.example.expensetracker.ui.theme.HexFFFFFFFF
import com.example.expensetracker.ui.theme.Hexddd0bf
import com.example.expensetracker.ui.theme.Hexdedbd4
import com.example.expensetracker.ui.theme.Hexf1efe3
import com.example.expensetracker.ui.theme.Hexf6f3ea
import com.example.expensetracker.viewModel.AddAccountViewModel
import com.google.gson.Gson


@Composable
fun AccountScreen(
    navHostController: NavHostController,
    addAccountViewModel: AddAccountViewModel
) {

    BackHandler {
        navHostController.navigate(BottomBarRoutes.SPENDING_SCREEN.routes){
            popUpTo(BottomBarRoutes.SPENDING_SCREEN.routes){
                inclusive = true
            }
        }
    }
    val getAccountList by addAccountViewModel.getAllRecord().observeAsState(emptyList())




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
                    .padding(top = 16.dp, bottom = 16.dp),
                onClick = { },
                colors = ButtonDefaults.buttonColors(Hex33cc4d, contentColor = HexFFFFFFFF),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text("Transfer")
            }
        }



                getAccountList.forEach{ item->
                    AccountItem(
                        addAccount = item,
                        onClick = { selectedAccount ->
                            navHostController.navigate("${ScreenRoutes.AddAccountScreen.route}/${selectedAccount.id}")

                        }
                    )
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
                modifier = Modifier
                    .size(39.dp)
                    .clickable {
                        navHostController.navigate("${ScreenRoutes.AddAccountScreen.route}/${"0"}")
                    }

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
fun AccountItem(addAccount: AddAccount, onClick: (AddAccount) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Hexf6f3ea)
            .clickable { onClick(addAccount) } // Handle click
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            val selectedColor = if (addAccount.color.isEmpty()) {
                Color.Black
            } else {
                Color(android.graphics.Color.parseColor(addAccount.color))
            }
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Add Icon",
                modifier = Modifier.size(24.dp),
                tint = selectedColor
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = addAccount.accountName,
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

