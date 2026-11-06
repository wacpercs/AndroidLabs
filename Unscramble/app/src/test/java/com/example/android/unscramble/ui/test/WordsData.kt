package com.example.android.unscramble.ui.test

import com.example.android.unscramble.data.allWords

/**
 * Maps the scrambled word to the unscrambled word from the dictionary.
 */
fun getUnscrambledWord(scrambledWord: String): String {
    val sortedScrambled = scrambledWord.toCharArray().sorted().joinToString("")
    for (word in allWords) {
        if (word.toCharArray().sorted().joinToString("") == sortedScrambled) {
            return word
        }
    }
    return ""
}
