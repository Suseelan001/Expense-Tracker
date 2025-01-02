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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.expensetracker.R
import com.example.expensetracker.navigation.BottomBarRoutes
import com.example.expensetracker.navigation.ScreenRoutes
import com.example.expensetracker.ui.theme.Hex164872
import com.example.expensetracker.ui.theme.Hex3d3a35
import com.example.expensetracker.ui.theme.Hex674b3f
import com.example.expensetracker.ui.theme.Hex6a6762
import com.example.expensetracker.ui.theme.Hexc9c6c1
import com.example.expensetracker.ui.theme.Hexd8d5cc
import com.example.expensetracker.ui.theme.Hexddd0bf
import com.example.expensetracker.ui.theme.Hexf1efe3
import com.example.expensetracker.ui.theme.Hexf6f3ea
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


@Composable
fun AddAccountScreen(
    navHostController: NavHostController
){
    Column( modifier = Modifier
        .fillMaxSize()
        .background(Hexddd0bf)) {

        TopAppBarAddAccount(navHostController)
        Spacer(modifier = Modifier
            .padding(top = 16.dp))
        AddAccountDetail()


    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarAddAccount(navHostController: NavHostController){
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
                    modifier = Modifier.size(35.dp).padding(start = 16.dp)
                        .clickable { navHostController.popBackStack() }

                )                }
        },
        actions = {
            TextButton(onClick = { /* Handle done action */ }) {
                Text("Done", color = Hex674b3f)
            }
        },
    )

}

@Composable
fun AddAccountDetail() {
    var name by remember { mutableStateOf("") }



    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Hexd8d5cc)

    ){
        Text("Account Detail", modifier = Modifier.padding(start = 16.dp, top = 5.dp, bottom = 5.dp), color = Hex6a6762)
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
                                text = "",
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

            Text(
                text = "Select Colour",
                color = Hex3d3a35,
                modifier = Modifier
                    .padding(5.dp)
                    .weight(0.70f)
            )
        }

        Spacer(
            modifier = Modifier
                .height(1.dp)
                .background(Hexc9c6c1)
                .fillMaxWidth()
        )
    }


}



