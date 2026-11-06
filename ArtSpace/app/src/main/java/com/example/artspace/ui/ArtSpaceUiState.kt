package com.example.artspace.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.artspace.R

/**
 * Модель данных произведения искусства
 */
data class ArtworkInfo(
    val id: Int,
    @DrawableRes val imageRes: Int,
    @StringRes val titleRes: Int,
    @StringRes val artistRes: Int
)

/**
 * Состояние UI для приложения ArtSpace
 */
data class ArtSpaceUiState(
    val currentArtworkIndex: Int = 1,
    val currentArtwork: ArtworkInfo = ArtworkInfo(
        id = 1,
        imageRes = R.drawable.mona_lisa,
        titleRes = R.string.artwork1_title,
        artistRes = R.string.artwork1_artist
    )
)
