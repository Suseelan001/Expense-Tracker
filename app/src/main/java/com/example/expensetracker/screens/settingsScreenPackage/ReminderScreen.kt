package com.example.expensetracker.screens.settingsScreenPackage



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.expensetracker.ui.theme.Hex6a6762
import com.example.expensetracker.ui.theme.Hexc9c6c1
import com.example.expensetracker.ui.theme.Hexd124a83
import com.example.expensetracker.ui.theme.Hexd8d5cc
import com.example.expensetracker.ui.theme.Hexded1c0
import com.example.expensetracker.ui.theme.Hexf6f3ea



@Composable
fun ReminderScreen(
    navHostController: NavHostController){


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Hexded1c0)
    ) {
        SettingsDetailTSHeaderRow(navHostController,"Reminders")

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            contentAlignment = Alignment.Center
        ) {

        }

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalDivider(thickness = 1.dp, color = Hexc9c6c1)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Hexd8d5cc)
                .padding(vertical = 2.dp)
        ) {
            Text(text = "Reminders", modifier = Modifier.padding(start = 16.dp), color = Hex6a6762)
        }



        HorizontalDivider(thickness = 1.dp, color = Hexc9c6c1)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Hexf6f3ea)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {


            Text(text = "Frequency", modifier = Modifier.weight(1f), color = Hexd124a83)
            Text(text = "Every Week", color = Hexd124a83)

        }
        HorizontalDivider(thickness = 1.dp, color = Hexc9c6c1)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Hexf6f3ea)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {


            Text(text = "Time", modifier = Modifier.weight(1f), color = Hexd124a83)
            Text(text = "12:00", color = Hexd124a83)

        }
        HorizontalDivider(thickness = 1.dp, color = Hexc9c6c1)


    }}



