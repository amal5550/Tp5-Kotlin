package com.example.dessertclicker

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.dessertclicker.model.Dessert

class DessertViewModel(desserts: List<Dessert>) : ViewModel() {

    // Initialize UI state
    var uiState by mutableStateOf(
        DessertUiState(
            currentDessertImageId = desserts.first().imageId,
            currentDessertPrice = desserts.first().price
        )
    )
        private set

    private val allDesserts: List<Dessert> = desserts

    // Method to handle dessert click
    fun onDessertClicked() {
        // Update revenue and number of desserts sold
        uiState = uiState.copy(
            revenue = uiState.revenue + uiState.currentDessertPrice,
            dessertsSold = uiState.dessertsSold + 1
        )

        // Determine the next dessert to show
        val dessertToShow = determineDessertToShow(allDesserts, uiState.dessertsSold)
        uiState = uiState.copy(
            currentDessertImageId = dessertToShow.imageId,
            currentDessertPrice = dessertToShow.price
        )
    }

    // Logic to determine which dessert to show
    private fun determineDessertToShow(
        desserts: List<Dessert>,
        dessertsSold: Int
    ): Dessert {
        var dessertToShow = desserts.first()
        for (dessert in desserts) {
            if (dessertsSold >= dessert.startProductionAmount) {
                dessertToShow = dessert
            } else {
                break
            }
        }
        return dessertToShow
    }
}
