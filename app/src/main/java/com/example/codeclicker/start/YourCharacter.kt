package com.example.codeclicker.start

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class YourCharacter(
    var selectedCharacterIndex: Int,
    var name: String,
    var language: String,
) {
    var clics by mutableIntStateOf(0)
    var money by mutableIntStateOf(0)
    var functions by mutableIntStateOf(1)
    var bots by mutableIntStateOf(1)
    var copilot by mutableIntStateOf(1)
    var isCooldown by mutableStateOf(false)
    var timer by mutableLongStateOf(20L)
    constructor(): this(0, "Name", "Language")
}

