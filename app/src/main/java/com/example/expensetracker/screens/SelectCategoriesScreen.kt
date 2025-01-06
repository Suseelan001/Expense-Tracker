package com.example.expensetracker.screens

import android.widget.Toast
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.expensetracker.R
import com.example.expensetracker.model.AddAccount
import com.example.expensetracker.model.AddCategory
import com.example.expensetracker.model.TransactionModel
import com.example.expensetracker.navigation.BottomBarRoutes
import com.example.expensetracker.navigation.ScreenRoutes
import com.example.expensetracker.ui.theme.Hex674b3f
import com.example.expensetracker.ui.theme.Hexddd0bf
import com.example.expensetracker.ui.theme.Hexdedbd4
import com.example.expensetracker.ui.theme.Hexf1efe3
import com.example.expensetracker.ui.theme.Hexf6f3ea
import com.example.expensetracker.viewModel.AddCategoryViewModel
import com.example.expensetracker.viewModel.MainViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectCategoriesScreen(
    navHostController: NavHostController,
    addCategoryViewModel: AddCategoryViewModel,
    transactionType:String,
    mainViewModel: MainViewModel
) {

    addCategoryViewModel.initializeDefaultCategories()

    val getCategoryList by addCategoryViewModel.getAllRecord(transactionType).observeAsState(emptyList())



    Column( modifier = Modifier
        .fillMaxSize()
        .background(Hexddd0bf)) {


        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Hexf1efe3,
                titleContentColor = Color.White
            ),
            title = { Text("") },
            navigationIcon = {
                IconButton(onClick = { navHostController.popBackStack()}) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_chevron_left_24),
                        contentDescription = "back",
                        tint = Hex674b3f,
                        modifier = Modifier.size(35.dp)
                    )                }
            },
            actions = {

            },
        )

            getCategoryList.forEach{ item->
                SelectCategoriesItem(
                    item = item.category,
                    onClick = { categoryName ->
                        mainViewModel.selectedCategory=categoryName
                        navHostController.popBackStack()
                    }
                )
            }


    }







}



@Composable
fun SelectCategoriesItem(item: String, onClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Hexf6f3ea)
            .clickable { onClick(item) }
    ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
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
                    text = item,
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
