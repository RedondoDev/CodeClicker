package com.example.codeclicker.start

data class YourCharacter(
    val selectedCharacterIndex: Int,
    val name: String,
    val language: String,
    var clics: Int,
    val money: Int,
    var copilot: Boolean
)
