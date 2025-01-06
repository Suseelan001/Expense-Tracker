package com.example.expensetracker.screens


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.AlertDialog
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.expensetracker.R
import com.example.expensetracker.model.AddAccount
import com.example.expensetracker.model.AddCategory
import com.example.expensetracker.ui.theme.Hex164872
import com.example.expensetracker.ui.theme.Hex3d3a35
import com.example.expensetracker.ui.theme.Hex674b3f
import com.example.expensetracker.ui.theme.Hex6a6762
import com.example.expensetracker.ui.theme.Hex9e3d46
import com.example.expensetracker.ui.theme.HexFFFFFFFF
import com.example.expensetracker.ui.theme.Hexc9c6c1
import com.example.expensetracker.ui.theme.Hexd8d5cc
import com.example.expensetracker.ui.theme.Hexddd0bf
import com.example.expensetracker.ui.theme.Hexf1efe3
import com.example.expensetracker.ui.theme.Hexf6f3ea
import com.example.expensetracker.viewModel.AddAccountViewModel
import com.example.expensetracker.viewModel.AddCategoryViewModel


@Composable
fun AddAccountScreen(
    screenType:String,
    accountId:String,
    transactionType:String,
    navHostController: NavHostController,
    addAccountViewModel:AddAccountViewModel,
    addCategoryViewModel: AddCategoryViewModel
){
    var name by remember { mutableStateOf("") }
    var selectedColor by remember { mutableStateOf("") }
    val taskLoaded = remember { mutableStateOf(false) }
    var showColorPicker by remember { mutableStateOf(false) }
    var primaryAccount by remember { mutableStateOf(false) }
    val context= LocalContext.current

    if (screenType=="Category"){
        val getCategoryRecord by addCategoryViewModel.getSingleRecord(accountId.toInt()).observeAsState()
        if (getCategoryRecord != null && !taskLoaded.value) {
            name = getCategoryRecord?.category ?: ""
            selectedColor = getCategoryRecord?.color ?: ""
            taskLoaded.value = true
        }
    }else{
        val getAccountRecord by addAccountViewModel.getSingleRecord(accountId.toInt()).observeAsState()
        if (getAccountRecord != null && !taskLoaded.value) {
            name = getAccountRecord?.accountName ?: ""
            selectedColor = getAccountRecord?.color ?: ""
            primaryAccount=getAccountRecord?.primaryAccount?:false
            taskLoaded.value = true
        }
    }




    Column( modifier = Modifier
        .fillMaxSize()
        .background(Hexddd0bf)) {

        TopAppBarAddAccount(transactionType,screenType,navHostController,name,selectedColor,addAccountViewModel,accountId.toInt(),addCategoryViewModel,primaryAccount)
        Spacer(modifier = Modifier
            .padding(top = 16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Hexd8d5cc)
        ) {
            Text(
                "$screenType Detail",
                modifier = Modifier.padding(start = 16.dp, top = 5.dp, bottom = 5.dp),
                color = Hex6a6762
            )
        }

        Column(
            modifier = Modifier
                .background(Hexf6f3ea)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Text(
                    text = "Name",
                    color = Hex164872,
                    modifier = Modifier
                        .padding(5.dp)
                        .weight(0.30f),
                    textAlign = TextAlign.End
                )

                Spacer(
                    modifier = Modifier
                        .height(34.dp)
                        .width(1.dp)
                        .background(Hexc9c6c1)
                )

                BasicTextField(
                    modifier = Modifier
                        .weight(0.70f)
                        .padding(5.dp),
                    value = name,
                    onValueChange = { newAmount ->
                        name = newAmount
                    },
                    textStyle = TextStyle(color = Hex3d3a35),
                    singleLine = true,
                    decorationBox = { innerTextField ->
                        Box(
                            modifier = Modifier
                                .background(Color.Transparent),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            if (name.isEmpty()) {
                                Text(
                                    text = "Enter Name",
                                    color = Hexc9c6c1,
                                )
                            }
                            innerTextField()
                        }
                    }
                )
            }

            Spacer(
                modifier = Modifier
                    .height(1.dp)
                    .background(Hexc9c6c1)
                    .fillMaxWidth()
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Text(
                    text = "Colour",
                    color = Hex164872,
                    modifier = Modifier
                        .padding(5.dp)
                        .weight(0.30f),
                    textAlign = TextAlign.End
                )
                Spacer(
                    modifier = Modifier
                        .height(34.dp)
                        .width(1.dp)
                        .background(Hexc9c6c1)
                )

                if (selectedColor == "") {
                    Text(
                        text = "Select color",
                        color = Hex164872,
                        modifier = Modifier
                            .padding(5.dp)
                            .weight(0.70f)
                            .clickable { showColorPicker = true },
                        textAlign = TextAlign.Start
                    )
                } else {
                    Row(
                        modifier = Modifier
                            .padding(5.dp)
                            .weight(0.70f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(start = 5.dp)
                                .size(24.dp)
                                .background(Color(android.graphics.Color.parseColor(selectedColor)))
                                .clickable { showColorPicker = true }
                        )

                        Spacer(modifier = Modifier.width(5.dp))


                        Icon(
                            painter = painterResource(id = R.drawable.close),
                            contentDescription = "Clear Color",
                            tint = Hex674b3f,
                            modifier = Modifier
                                .clickable {
                                    selectedColor=""
                                }
                        )

                    }
                }
            }

            Spacer(
                modifier = Modifier
                    .height(1.dp)
                    .background(Hexc9c6c1)
                    .fillMaxWidth()
            )
        }

        if (showColorPicker) {
            ColorPickerDialog(
                onColorSelected = { color ->
                    selectedColor = color
                    showColorPicker = false
                },
                onDismiss = { showColorPicker = false }
            )
        }

        Spacer(modifier = Modifier
            .padding(top = 16.dp))

        println("CHECK_TAG_PRIMARY_ACCOUNT___ $primaryAccount")
        if (accountId.toInt()>0) {
            if (!primaryAccount){

                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        modifier = Modifier
                            .padding(top = 16.dp, bottom = 16.dp),
                        onClick = {
                            if (screenType=="Category"){

                                addCategoryViewModel.deleteSingleRecord(accountId.toInt())
                                Toast.makeText(context, "Your Category  has been deleted", Toast.LENGTH_SHORT).show()
                                navHostController.popBackStack()



                            }else {
                                if (screenType=="Account")

                                    addAccountViewModel.deleteSingleRecord(accountId.toInt())
                                Toast.makeText(context, "Your account has been deleted", Toast.LENGTH_SHORT).show()
                                navHostController.popBackStack()
                            }

                        },
                        colors = ButtonDefaults.buttonColors(Hex9e3d46, contentColor = HexFFFFFFFF),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text("Delete")
                    }
                }
            }

        }


    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarAddAccount(transactionType:String
                        ,screenType:String,
                        navHostController: NavHostController,
                        name:String,selectedColor:String,addAccountViewModel:AddAccountViewModel,
                        accountId:Int,addCategoryViewModel:AddCategoryViewModel,
                        primaryAccount:Boolean){
    val context = LocalContext.current
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Hexf1efe3,
            titleContentColor = Color.White
        ),
        title = { Text("") },
        navigationIcon = {
            IconButton(onClick = { /* Handle back action */ }) {
                Icon(
                    painter = painterResource(R.drawable.close),
                    contentDescription = "close",
                    tint = Hex674b3f,
                    modifier = Modifier
                        .size(35.dp)
                        .padding(start = 16.dp)
                        .clickable { navHostController.popBackStack() }

                )                }
        },
        actions = {
            TextButton(onClick = {
                if (name.isEmpty()){
                    Toast.makeText(context, "Please select name ", Toast.LENGTH_SHORT).show()
                }else{

                    if (screenType=="Account"){
                        val addAccount = AddAccount(accountName = name, color = selectedColor,primaryAccount=primaryAccount)

                        if (accountId > 0) {
                            val updatedAccount = addAccount.copy(id = accountId)
                            addAccountViewModel.updateRecord(updatedAccount)
                            Toast.makeText(context, "Your account has been updated", Toast.LENGTH_SHORT).show()
                        } else {
                            addAccountViewModel.insertAccount(addAccount)
                            Toast.makeText(context, "Your account has been created", Toast.LENGTH_SHORT).show()
                        }

                        navHostController.popBackStack()
                    }else{
                        if (screenType=="Category"){

                            val addCategory = AddCategory(category = name, color = selectedColor,categoryType=transactionType)

                            if (accountId > 0) {
                                val updatedAccount = addCategory.copy(id = accountId)
                                addCategoryViewModel.updateRecord(updatedAccount)
                                Toast.makeText(context, "Your category has been updated", Toast.LENGTH_SHORT).show()
                            } else {
                                addCategoryViewModel.insertAccount(addCategory)
                                Toast.makeText(context, "Your category has been created", Toast.LENGTH_SHORT).show()
                            }

                            navHostController.popBackStack()

                        }
                    }
                }
            }) {
                Text("Done", color = Hex674b3f)
            }
        },
    )

}


@Composable
fun ColorPickerDialog(onColorSelected: (String) -> Unit, onDismiss: () -> Unit) {
    val colors = listOf("#FF0000", "#FF00FF", "#0000FF","#008000", "#FFFF00", "#808080", "#D3D3D3")

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Color Picker") },
        text = {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(colors) { hexColor ->
                        Box(
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxSize()
                                .aspectRatio(1f)
                                .background(
                                    Color(android.graphics.Color.parseColor(hexColor)),
                                    shape = CircleShape
                                )
                                .clickable { onColorSelected(hexColor) }
                        )



                }
            }

        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("CANCEL")
            }
        }
    )

}




