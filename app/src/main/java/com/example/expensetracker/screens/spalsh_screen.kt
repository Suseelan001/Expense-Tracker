package com.example.expensetracker.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.expensetracker.navigation.ScreenRoutes
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
    navHostController: NavHostController
) {

    LaunchedEffect(key1 = Unit){
        delay(2000)
        navHostController.navigate(ScreenRoutes.BottomBar.route)
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Text(text = "Splash Screen", style = TextStyle(
            color = Color.Black,
            fontWeight = FontWeight.SemiBold,
            fontSize = 30.sp
        )
        )
    }

}