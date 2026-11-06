package com.example.dessertclicker.ui

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * ViewModel for managing Dessert Clicker state following MVVM & UDF patterns.
 */
class DessertViewModel : ViewModel() {

    private val desserts = Datasource.dessertList

    private val _uiState = MutableStateFlow(
        DessertUiState(
            revenue = 0,
            dessertsSold = 0,
            currentDessertIndex = 0,
            currentDessertPrice = desserts[0].price,
            currentDessertImageId = desserts[0].imageId
        )
    )
    val uiState: StateFlow<DessertUiState> = _uiState.asStateFlow()

    /**
     * Handles dessert tap: updates revenue, sold count, and transitions to next dessert if unlocked.
     */
    fun onDessertClicked() {
        _uiState.update { currentState ->
            val dessertsSold = currentState.dessertsSold + 1
            val nextDessertIndex = determineDessertIndex(dessertsSold)
            val nextDessert = desserts[nextDessertIndex]
            currentState.copy(
                revenue = currentState.revenue + currentState.currentDessertPrice,
                dessertsSold = dessertsSold,
                currentDessertIndex = nextDessertIndex,
                currentDessertPrice = nextDessert.price,
                currentDessertImageId = nextDessert.imageId
            )
        }
    }

    /**
     * Determines which dessert to display based on the total desserts sold.
     */
    private fun determineDessertIndex(dessertsSold: Int): Int {
        var dessertIndex = 0
        for (index in desserts.indices) {
            if (dessertsSold >= desserts[index].startProductionAmount) {
                dessertIndex = index
            } else {
                break
            }
        }
        return dessertIndex
    }
}
