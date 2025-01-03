package com.example.expensetracker.viewModel



import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class MainViewModel  : ViewModel() {

  private val _selectedItem = MutableStateFlow<String>("")
  val selectedItem: StateFlow<String> = _selectedItem

  fun updateSelectedItem(item: String) {
    _selectedItem.value = item
  }



}
