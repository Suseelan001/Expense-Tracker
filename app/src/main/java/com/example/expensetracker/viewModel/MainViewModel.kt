package com.example.expensetracker.viewModel



import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

  var selectedCategory by mutableStateOf<String>("")
  var selectedTransactionType by mutableStateOf<String>("")
  var enteredAmount by mutableStateOf<String>("")


}
