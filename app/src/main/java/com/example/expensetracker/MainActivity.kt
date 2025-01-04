package com.example.expensetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.expensetracker.appstate.rememberAppState
import com.example.expensetracker.bottombar.BottomBarRow
import com.example.expensetracker.model.AddAccount
import com.example.expensetracker.navigation.BottomBarNavigation
import com.example.expensetracker.ui.theme.ExpenseTrackerTheme
import com.example.expensetracker.ui.theme.HexFFFFFFFF
import com.example.expensetracker.ui.theme.Hexf1efe3
import com.example.expensetracker.viewModel.AddAccountViewModel
import com.example.expensetracker.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val addAccountViewModel: AddAccountViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExpenseTrackerTheme {
                val appState = rememberAppState()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color =HexFFFFFFFF
                ) {
                    Scaffold(
                        bottomBar = {
                            if (appState.shouldShowBottomBar)
                                BottomAppBar(
                                    containerColor = Hexf1efe3,
                                    contentPadding = PaddingValues(horizontal = 20.dp),
                                    modifier = Modifier
                                        .height(70.dp)
                                ) {
                                    BottomBarRow(
                                        navHostController = appState.navHostController,
                                    )
                                }
                        }
                    ) { innerPadding ->
                        BottomBarNavigation(
                            navHostController = appState.navHostController,
                            padding = innerPadding,
                            MainViewModel()
                        )
                    }
                }
            }
        }
        observeAndInsertDefaultAccount()
    }

private fun observeAndInsertDefaultAccount() {
    addAccountViewModel.getAllRecord().observe(this) { accountList ->
        if (accountList.isEmpty()) {
            val defaultAccount = AddAccount(
                id = 1,
                accountName = "personal",
                color = "#000000",
                primaryAccount=true
            )
            addAccountViewModel.insertAccount(defaultAccount)
        }
    }
}
}
