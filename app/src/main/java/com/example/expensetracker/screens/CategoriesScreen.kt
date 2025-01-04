package com.example.expensetracker.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.expensetracker.model.AddCategory
import com.example.expensetracker.navigation.BottomBarRoutes
import com.example.expensetracker.navigation.ScreenRoutes
import com.example.expensetracker.ui.theme.Hex674b3f
import com.example.expensetracker.ui.theme.Hexddd0bf
import com.example.expensetracker.ui.theme.Hexdedbd4
import com.example.expensetracker.ui.theme.Hexf1efe3
import com.example.expensetracker.ui.theme.Hexf6f3ea
import com.example.expensetracker.viewModel.AddCategoryViewModel


@Composable
fun CategoriesScreen(
    navHostController: NavHostController,
    addCategoryViewModel: AddCategoryViewModel
) {


    val clickedButton = remember { mutableStateOf("expense") }
    addCategoryViewModel.initializeDefaultCategories()

    val getCategoryList by addCategoryViewModel.getAllRecord(clickedButton.value).observeAsState(emptyList())


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

        TopAppBarCategoriesScreen(navHostController,clickedButton.value)

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

        getCategoryList.forEach{ item->
            CategoriesItem(
                item = item,
                onClick = { selectedAccount ->
                    navHostController.navigate("${ScreenRoutes.AddAccountScreen.route}/${selectedAccount.id}/${"Category"}/${""}")

                }
            )
        }





    }







}

@Composable
fun TopAppBarCategoriesScreen(navHostController: NavHostController,clickedButton:String){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Hexf1efe3)
            ,
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
                    .clickable {
                        navHostController.navigate("${ScreenRoutes.AddAccountScreen.route}/${"0"}/${"Category"}/${clickedButton}")
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
fun CategoriesItem(item: AddCategory, onClick: (AddCategory) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Hexf6f3ea)
            .clickable { onClick (item)}
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
                    text = item.category,
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
