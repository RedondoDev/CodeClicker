package com.example.codeclicker.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.codeclicker.load.DataBase
import com.example.codeclicker.start.YourCharacter

@Composable
fun GithubScreen(character: YourCharacter, dataBase: DataBase) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text("Github")
//        yourCharacter.copilot = !yourCharacter.copilot
//        dataBase.updateCopilot(yourCharacter.copilot)
    }
}