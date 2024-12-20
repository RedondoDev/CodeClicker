package com.example.codeclicker.start

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.time.Duration
import java.time.LocalDateTime

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
//    var lastDisc by mutableStateOf(LocalDateTime.now().toString())
    constructor(): this(0, "Name", "Language")
}

//@SuppressLint("NewApi")
//fun onPlayerReconnect(yourCharacter: YourCharacter) {
//    val now = LocalDateTime.now()
//    val duration = Duration.between(yourCharacter.lastDisc, now)
//    val secondsAway = duration.seconds
//    val coinsEarned = (secondsAway / 5) * yourCharacter.functions
//    yourCharacter.money += coinsEarned.toInt()
//    yourCharacter.lastDisc = now // Actualizar la última hora de conexión
//    // Actualizar el estado del jugador en la base de datos si es necesario
//}
