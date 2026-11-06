package com.example.artspace.ui

import androidx.lifecycle.ViewModel
import com.example.artspace.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * ViewModel для управления состоянием галереи ArtSpace
 * Реализует принцип SSOT и однонаправленный поток данных (UDF).
 */
class ArtSpaceViewModel : ViewModel() {

    private val artworks = listOf(
        ArtworkInfo(
            id = 1,
            imageRes = R.drawable.mona_lisa,
            titleRes = R.string.artwork1_title,
            artistRes = R.string.artwork1_artist
        ),
        ArtworkInfo(
            id = 2,
            imageRes = R.drawable.starry_night,
            titleRes = R.string.artwork2_title,
            artistRes = R.string.artwork2_artist
        ),
        ArtworkInfo(
            id = 3,
            imageRes = R.drawable.the_scream,
            titleRes = R.string.artwork3_title,
            artistRes = R.string.artwork3_artist
        ),
        ArtworkInfo(
            id = 4,
            imageRes = R.drawable.the_wave,
            titleRes = R.string.artwork4_title,
            artistRes = R.string.artwork4_artist
        )
    )

    // Приватное изменяемое состояние
    private val _uiState = MutableStateFlow(
        ArtSpaceUiState(
            currentArtworkIndex = 1,
            currentArtwork = artworks[0]
        )
    )

    // Публичное неизменяемое состояние для наблюдения в UI
    val uiState: StateFlow<ArtSpaceUiState> = _uiState.asStateFlow()

    /**
     * Переход к следующему произведению искусства
     */
    fun nextArtwork() {
        _uiState.update { currentState ->
            val nextIndex = if (currentState.currentArtworkIndex >= artworks.size) 1 else currentState.currentArtworkIndex + 1
            currentState.copy(
                currentArtworkIndex = nextIndex,
                currentArtwork = artworks[nextIndex - 1]
            )
        }
    }

    /**
     * Переход к предыдущему произведению искусства
     */
    fun previousArtwork() {
        _uiState.update { currentState ->
            val prevIndex = if (currentState.currentArtworkIndex <= 1) artworks.size else currentState.currentArtworkIndex - 1
            currentState.copy(
                currentArtworkIndex = prevIndex,
                currentArtwork = artworks[prevIndex - 1]
            )
        }
    }
}
