package com.example.expensetracker.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.expensetracker.R
import com.example.expensetracker.model.AddAccount
import com.example.expensetracker.navigation.BottomBarRoutes
import com.example.expensetracker.navigation.ScreenRoutes
import com.example.expensetracker.ui.theme.Hex33cc4d
import com.example.expensetracker.ui.theme.Hex5d3724
import com.example.expensetracker.ui.theme.Hex674b3f
import com.example.expensetracker.ui.theme.Hex816f64
import com.example.expensetracker.ui.theme.HexFFFFFFFF
import com.example.expensetracker.ui.theme.Hexd8eeec
import com.example.expensetracker.ui.theme.Hexddd0bf
import com.example.expensetracker.ui.theme.Hexdedbd4
import com.example.expensetracker.ui.theme.Hexf1efe3
import com.example.expensetracker.ui.theme.Hexf6f3ea
import com.example.expensetracker.ui.theme.NotoSerifWithHex5d372418sp
import com.example.expensetracker.viewModel.AddAccountViewModel


@Composable
fun AccountScreen(
    navHostController: NavHostController,
    addAccountViewModel: AddAccountViewModel
) {

    val selectedItems = remember { mutableStateListOf<AddAccount>() }
    var isSelectionMode by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }



    BackHandler {
        navHostController.navigate(BottomBarRoutes.SPENDING_SCREEN.routes){
            popUpTo(BottomBarRoutes.SPENDING_SCREEN.routes){
                inclusive = true
            }
        }
    }
    val getAccountList by addAccountViewModel.getAllRecord().observeAsState(emptyList())


    val accountListSize = remember { mutableIntStateOf(0)  }

    accountListSize.intValue=getAccountList.size

    Column( modifier = Modifier
        .fillMaxSize()
        .background(Hexddd0bf)) {

        if (selectedItems.isEmpty()){
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
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clickable {
                                            expanded = !expanded
                                        }

                                )
                        ShowDropdown(expanded = expanded, onDismissRequest = { expanded = false }, navHostController = navHostController)
                    }
                }

            }
        }else {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Hexf1efe3)
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
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
                                        navHostController.navigate("${ScreenRoutes.AddAccountScreen.route}/${selectedItem.id}/${"Account"}/${""}")
                                    }
                                }
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = Hex816f64,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                selectedItems.forEach { item ->
                                    addAccountViewModel.deleteSingleRecord(item.id)
                                    selectedItems.remove(item)
                                }
                                if (selectedItems.isEmpty()) {
                                    isSelectionMode = false
                                }
                            }
                    )
                }
            }

        }






        Spacer(modifier = Modifier.height(16.dp))
        if (getAccountList.size>1){
            CenteredTransferBox(navHostController)

        }

        Spacer(modifier = Modifier
            .height(16.dp))
                getAccountList.forEach{ item->
                    AccountItem(
                        item = item,
                        accountListSize =accountListSize.intValue,
                        isSelected = selectedItems.contains(item),
                        isSelectionMode = isSelectionMode,
                        onClick = { clickedItem ->
                            if (!isSelectionMode) {
                                navHostController.navigate("${ScreenRoutes.AddAccountScreen.route}/${clickedItem.id}/${"Account"}/${""}")
                            }
                        },
                        onLongPress = { longPressedItem ->
                            if (!isSelectionMode) {
                                isSelectionMode = true
                            }
                            if (selectedItems.contains(longPressedItem)) {
                                selectedItems.remove(longPressedItem)
                                if (selectedItems.size == 0) {
                                    isSelectionMode = false
                                }
                            } else {
                                selectedItems.add(longPressedItem)
                            }
                        }
                    )
                }
    }







}

@Composable
fun ShowDropdown(expanded: Boolean, onDismissRequest: () -> Unit, navHostController: NavHostController) {

    DropdownMenu(
        containerColor= HexFFFFFFFF,
        expanded = expanded,
        onDismissRequest = { onDismissRequest () }
    ) {
        DropdownMenuItem(
            text = { Text("Back Up", color = Hex5d3724) },
            onClick = { /* Do something... */ },
            modifier = Modifier.widthIn(min = 200.dp)

        )
        DropdownMenuItem(
            text = { Text("Like", color = Hex5d3724) },
            onClick = { /* Do something... */ },
            modifier = Modifier.widthIn(min = 200.dp)
        )
        DropdownMenuItem(
            text = { Text("Privacy Policy", color = Hex5d3724) },
            onClick = { /* Do something... */ },
            modifier = Modifier.widthIn(min = 200.dp)
        )
        DropdownMenuItem(
            text = { Text("Settings", color = Hex5d3724) },
            onClick = {
                onDismissRequest ()
                navHostController.navigate(ScreenRoutes.SettingsScreen.route)

            },
            modifier = Modifier.widthIn(min = 200.dp)
        )
        DropdownMenuItem(
            text = { Text("Help", color = Hex5d3724) },
            onClick = { /* Do something... */ },
            modifier = Modifier.widthIn(min = 200.dp)
        )
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



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AccountItem(
    accountListSize:Int,
    item: AddAccount,
    isSelected: Boolean,
    isSelectionMode: Boolean,
    onClick: (AddAccount) -> Unit,
    onLongPress: (AddAccount) -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(if (isSelected) Hexd8eeec else Hexf6f3ea)
            .combinedClickable(
                onClick = {
                    if (isSelectionMode) {
                        onLongPress(item)
                    } else {
                        onClick(item)
                    }
                },
                onLongClick = {
                    if (accountListSize > 1) {
                        onLongPress(item)
                    }
                }
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            val selectedColor = if (item.color.isEmpty()) {
                Color.Black
            } else {
                Color(android.graphics.Color.parseColor(item.color))
            }
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Add Icon",
                modifier = Modifier.size(24.dp),
                tint = selectedColor
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = item.accountName,
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


