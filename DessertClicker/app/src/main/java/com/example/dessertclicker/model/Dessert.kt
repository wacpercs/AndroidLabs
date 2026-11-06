package com.example.dessertclicker.model

import androidx.annotation.DrawableRes

/**
 * [Dessert] represents the information about each dessert that can be clicked.
 */
data class Dessert(
    @DrawableRes val imageId: Int,
    val price: Int,
    val startProductionAmount: Int
)
