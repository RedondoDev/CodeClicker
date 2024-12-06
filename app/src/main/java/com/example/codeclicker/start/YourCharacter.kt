package com.example.codeclicker.start

data class YourCharacter(
    val selectedCharacterIndex: Int,
    val name: String,
    val language: String,
    var clics: Int,
    var money: Int,
    var copilot: Boolean
)
