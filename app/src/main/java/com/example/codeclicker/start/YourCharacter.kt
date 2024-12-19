package com.example.codeclicker.start

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class YourCharacter(
    var selectedCharacterIndex: Int,
    var name: String,
    var language: String,
    var clics: Int,
) {
    var money by mutableIntStateOf(0)
    var functions by mutableIntStateOf(0)
    var bots by mutableIntStateOf(0)
    var copilot by mutableIntStateOf(0)
    constructor(): this(0, "Name", "Language", 0)
}
