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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.expensetracker.model.AddAccount
import com.example.expensetracker.navigation.BottomBarRoutes
import com.example.expensetracker.navigation.ScreenRoutes
import com.example.expensetracker.ui.theme.Hex33cc4d
import com.example.expensetracker.ui.theme.Hex674b3f
import com.example.expensetracker.ui.theme.Hexddd0bf
import com.example.expensetracker.ui.theme.Hexdedbd4
import com.example.expensetracker.ui.theme.Hexf1efe3
import com.example.expensetracker.ui.theme.Hexf6f3ea
import com.example.expensetracker.viewModel.AddAccountViewModel


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
        Spacer(modifier = Modifier.height(16.dp))
        if (getAccountList.size>1){
            CenteredTransferBox(navHostController)

        }
        Spacer(modifier = Modifier
            .height(16.dp))
                getAccountList.forEach{ item->
                    AccountItem(
                        addAccount = item,
                        onClick = { selectedAccount ->
                            navHostController.navigate("${ScreenRoutes.AddAccountScreen.route}/${selectedAccount.id}/${"Account"}/${""}")

                        }
                    )
                }
    }







}
@Composable
fun CenteredTransferBox(navHostController:NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .clickable {
                    navHostController.navigate(ScreenRoutes.TransferScreen.route)
                }
                .background(Hex33cc4d, shape = RoundedCornerShape(4.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Transfer",
                color = Color.White,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
            )
        }
    }
}

@Composable
fun TopAppBarAccountScreen(navHostController: NavHostController){
    Box(
        modifier = Modifier.shadow(
            elevation = 4.dp,
            shape = RectangleShape
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Hexf1efe3)
                .padding(start = 16.dp, end = 16.dp, top = 10.dp, bottom = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(1f))



            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "add",
                    tint = Hex674b3f,
                    modifier = Modifier
                        .size(34.dp)
                        .clickable {
                            navHostController.navigate("${ScreenRoutes.AddAccountScreen.route}/${"0"}/${"Account"}/${""}")
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
}

@Composable
fun AccountItem(addAccount: AddAccount, onClick: (AddAccount) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Hexf6f3ea)
            .clickable { onClick(addAccount) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
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
                color = Color.Black,
                style = TextStyle(fontSize = 22.sp)
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

