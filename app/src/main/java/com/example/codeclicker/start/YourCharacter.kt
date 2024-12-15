package com.example.codeclicker.start

data class YourCharacter(
    var selectedCharacterIndex: Int,
    var name: String,
    var language: String,
    var clics: Int,
    var money: Int,
    var copilot: Boolean
) {
    constructor(): this(0, "Name", "Language", 1, 0, false)
}
