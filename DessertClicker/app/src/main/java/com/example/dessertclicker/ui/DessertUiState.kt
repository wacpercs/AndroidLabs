package com.example.dessertclicker.ui

import androidx.annotation.DrawableRes

/**
 * UI State for Dessert Clicker
 */
data class DessertUiState(
    val revenue: Int = 0,
    val dessertsSold: Int = 0,
    val currentDessertIndex: Int = 0,
    val currentDessertPrice: Int = 5,
    @DrawableRes val currentDessertImageId: Int = 0
)
