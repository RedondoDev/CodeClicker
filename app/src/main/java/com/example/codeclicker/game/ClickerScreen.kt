package com.example.codeclicker.game

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.codeclicker.R
import com.example.codeclicker.Routes
import com.example.codeclicker.start.BottomBar
import com.example.codeclicker.start.CharacterData
import com.example.codeclicker.start.TopBar
import com.example.codeclicker.start.YourCharacter

@Composable
fun ClickerScreen(yourCharacter: YourCharacter) {

    val imageList: List<Int> = listOf(
        R.drawable.uno,
        R.drawable.dos,
        R.drawable.tres,
        R.drawable.cuatro,
        R.drawable.cinco,
        R.drawable.seis,
        R.drawable.siete,
        R.drawable.ocho
    )

    val charactersList = listOf(
        CharacterData(0, painterResource(imageList[0]), painterResource(imageList[1])),
        CharacterData(1, painterResource(imageList[2]), painterResource(imageList[3])),
        CharacterData(2, painterResource(imageList[4]), painterResource(imageList[5])),
        CharacterData(3, painterResource(imageList[6]), painterResource(imageList[7]))
    )

    val unlocked by rememberSaveable { mutableStateOf(yourCharacter.copilot) }
    var currentToast by remember { mutableStateOf<Toast?>(null) }
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(70.dp)
                .align(
                    Alignment.TopStart
                )
                .padding(6.dp)
                .clip(CircleShape)
                .clickable {
                    // Hacer lógica del timer
                    if (!unlocked) {
                        currentToast?.cancel()
                        currentToast =
                            Toast.makeText(context, "Habilidad bloqueada", Toast.LENGTH_SHORT)
                        currentToast?.show()
                    } else {
                        // método de copilot
                    }
                }
        ) {
            Icon(
                painterResource(R.drawable.copilot),
                "",
                tint = Color.Black,
                modifier = Modifier.padding(5.dp)
            )
            if (!unlocked) {
                Icon(
                    painterResource(R.drawable.diagonal_roja),
                    "",
                    tint = Color.Red,
                    modifier = Modifier.padding(
                        start = 8.dp, bottom = 8.dp,
                        top = 10.dp, end = 10.dp
                    )
                )
            }
        }
    }
}


