package com.example.expensetracker.screens.settingsScreenPackage



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.expensetracker.ui.theme.Hex888888
import com.example.expensetracker.ui.theme.Hexded1c0
import com.example.expensetracker.viewModel.MainViewModel
import com.example.expensetracker.viewModel.SharedPreferenceViewModel


@Composable
fun DropBoxScreen(
    navHostController: NavHostController){


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Hexded1c0)
    ) {
        SettingsDetailTSHeaderRow(navHostController, "Automatic Syncing")

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier.wrapContentWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { /* Handle Link action */ },
                colors = ButtonDefaults.buttonColors(Color(0xFF00B14F)),
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier
                    .wrapContentWidth()
            ) {
                Text(
                    text = "Link to Dropbox",
                    color = Color.White,
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "You will need a Dropbox account to sync your data. Use the 'Link' button above to connect to or create your Dropbox account.",
                color = Color(0xFF888888),
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = { /* Handle Link action */ },
                colors = ButtonDefaults.buttonColors(Color(0xFF00B14F)),
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(bottom = 16.dp)
            ) {
                Text(
                    text = "De-duplicate",
                    color = Color.White,
                )
            }
        }
    }


}
