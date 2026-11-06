package com.example.android.unscramble.ui.test

import com.example.android.unscramble.data.MAX_NO_OF_WORDS
import com.example.android.unscramble.data.SCORE_INCREASE
import com.example.android.unscramble.ui.GameViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GameViewModelTest {

    private val viewModel = GameViewModel()

    @Test
    fun gameViewModel_CorrectWordGuessed_ScoreUpdatedAndErrorFlagUnset() {
        var currentGameUiState = viewModel.uiState.value
        val correctPlayerWord = getUnscrambledWord(currentGameUiState.currentScrambledWord)

        viewModel.updateUserGuess(correctPlayerWord)
        viewModel.checkUserGuess()

        currentGameUiState = viewModel.uiState.value
        // Assert that the error flag is unset
        assertFalse(currentGameUiState.isGuessedWordWrong)
        // Assert that score is updated correctly
        assertEquals(SCORE_AFTER_FIRST_CORRECT_ANSWER, currentGameUiState.score)
    }

    @Test
    fun gameViewModel_IncorrectGuess_ErrorFlagSet() {
        // Given an incorrect guess
        val incorrectPlayerWord = "wrong_guess_xyz"
        viewModel.updateUserGuess(incorrectPlayerWord)
        viewModel.checkUserGuess()

        val currentGameUiState = viewModel.uiState.value
        // Assert that error flag is set to true
        assertTrue(currentGameUiState.isGuessedWordWrong)
        // Assert that score did not change
        assertEquals(0, currentGameUiState.score)
    }

    @Test
    fun gameViewModel_Initialization_FirstWordLoaded() {
        val gameUiState = viewModel.uiState.value
        val unScrambledWord = getUnscrambledWord(gameUiState.currentScrambledWord)

        // Assert that current word is scrambled and not empty
        assertNotEquals("", gameUiState.currentScrambledWord)
        assertNotEquals(unScrambledWord, gameUiState.currentScrambledWord)
        // Assert that word count starts at 1
        assertEquals(1, gameUiState.currentWordCount)
        // Assert that score starts at 0
        assertEquals(0, gameUiState.score)
        // Assert that flags start as false
        assertFalse(gameUiState.isGuessedWordWrong)
        assertFalse(gameUiState.isGameOver)
    }

    @Test
    fun gameViewModel_AllWordsGuessed_UiStateUpdatedCorrectly() {
        var expectedScore = 0
        repeat(MAX_NO_OF_WORDS) {
            val currentGameUiState = viewModel.uiState.value
            val correctPlayerWord = getUnscrambledWord(currentGameUiState.currentScrambledWord)
            viewModel.updateUserGuess(correctPlayerWord)
            viewModel.checkUserGuess()
            expectedScore += SCORE_INCREASE
        }

        val finalUiState = viewModel.uiState.value
        assertEquals(expectedScore, finalUiState.score)
        assertTrue(finalUiState.isGameOver)
        assertFalse(finalUiState.isGuessedWordWrong)
    }

    @Test
    fun gameViewModel_WordSkipped_ScoreUnchangedAndWordCountIncreased() {
        var currentGameUiState = viewModel.uiState.value
        val correctPlayerWord = getUnscrambledWord(currentGameUiState.currentScrambledWord)
        viewModel.updateUserGuess(correctPlayerWord)
        viewModel.checkUserGuess()

        currentGameUiState = viewModel.uiState.value
        val lastWordCount = currentGameUiState.currentWordCount
        val scoreBeforeSkip = currentGameUiState.score

        viewModel.skipWord()
        currentGameUiState = viewModel.uiState.value

        // Score remains unchanged after skip
        assertEquals(scoreBeforeSkip, currentGameUiState.score)
        // Word count increased by 1
        assertEquals(lastWordCount + 1, currentGameUiState.currentWordCount)
        assertFalse(currentGameUiState.isGuessedWordWrong)
    }

    companion object {
        private const val SCORE_AFTER_FIRST_CORRECT_ANSWER = SCORE_INCREASE
    }
}
