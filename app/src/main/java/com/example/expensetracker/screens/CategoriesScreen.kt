package com.example.expensetracker.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.expensetracker.R
import com.example.expensetracker.model.AddCategory
import com.example.expensetracker.navigation.BottomBarRoutes
import com.example.expensetracker.navigation.ScreenRoutes
import com.example.expensetracker.ui.theme.Hex674b3f
import com.example.expensetracker.ui.theme.Hex816f64
import com.example.expensetracker.ui.theme.Hexd8eeec
import com.example.expensetracker.ui.theme.Hexddd0bf
import com.example.expensetracker.ui.theme.Hexdedbd4
import com.example.expensetracker.ui.theme.Hexe2cdb8
import com.example.expensetracker.ui.theme.Hexf1efe3
import com.example.expensetracker.ui.theme.Hexf6f3ea
import com.example.expensetracker.ui.theme.NotoSerifWithHex5d372418sp
import com.example.expensetracker.viewModel.AddCategoryViewModel
import com.google.gson.Gson


@Composable
fun CategoriesScreen(
    navHostController: NavHostController,
    addCategoryViewModel: AddCategoryViewModel
) {
    val clickedButton = remember { mutableStateOf("expense") }
    addCategoryViewModel.initializeDefaultCategories()
    val getCategoryList by addCategoryViewModel
        .getAllRecord(clickedButton.value)
        .observeAsState(emptyList())
    val selectedItems = remember { mutableStateListOf<AddCategory>() }
    var isSelectionMode by remember { mutableStateOf(false) }


    BackHandler {
        navHostController.navigate(BottomBarRoutes.SPENDING_SCREEN.routes) {
            popUpTo(BottomBarRoutes.SPENDING_SCREEN.routes) {
                inclusive = true
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Hexddd0bf)
    ) {

        Box(
            modifier = Modifier.shadow(
                elevation = 4.dp,
                shape = RectangleShape
            )
        ) {
            if (selectedItems.isEmpty()) {
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
                                    navHostController.navigate("${ScreenRoutes.AddAccountScreen.route}/${"0"}/${"Category"}/${clickedButton.value}")
                                }
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "Menu",
                            tint = Hex674b3f,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            } else
            {
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
                                        navHostController.navigate("${ScreenRoutes.AddAccountScreen.route}/${selectedItem.id}/${"Category"}/${selectedItem.categoryType}")
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
                                    addCategoryViewModel.deleteSingleRecord(item.id)
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
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    clickedButton.value = "expense"
                },
                colors = ButtonDefaults.buttonColors(
                    if (clickedButton.value == "expense") Hex674b3f else Color.Transparent,
                    contentColor = if (clickedButton.value == "expense") Hexe2cdb8 else Hex674b3f
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
                colors = ButtonDefaults.buttonColors(
                    if (clickedButton.value == "income") Hex674b3f else Color.Transparent,
                    contentColor = if (clickedButton.value == "income") Hexe2cdb8 else Hex674b3f
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


        getCategoryList.forEach { item ->
            CategoriesItem(
                item = item,
                isSelected = selectedItems.contains(item),
                isSelectionMode = isSelectionMode,
                onClick = { clickedItem ->
                    if (!isSelectionMode) {
                        navHostController.navigate("${ScreenRoutes.AddAccountScreen.route}/${clickedItem.id}/${"Category"}/${item.categoryType}")
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


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoriesItem(
    item: AddCategory,
    isSelected: Boolean,
    isSelectionMode: Boolean,
    onClick: (AddCategory) -> Unit,
    onLongPress: (AddCategory) -> Unit
) {

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
                onLongClick = { onLongPress(item) }
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
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
                color = Color.Black,
                style = TextStyle(fontSize = 18.sp)
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
