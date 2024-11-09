package com.example.dessertclicker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dessertclicker.model.Dessert

class DessertViewModelFactory(private val desserts: List<Dessert>) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DessertViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DessertViewModel(desserts) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
