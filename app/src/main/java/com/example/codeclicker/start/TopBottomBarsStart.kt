package com.example.codeclicker.start

import android.media.MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.codeclicker.R
import com.example.codeclicker.ui.theme.quicksandFamily
import com.google.android.gms.wallet.button.ButtonConstants


@Composable
fun TopBar(text: String) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(Color(0xFF3c6391))
    ) {
        Text(
            text = text,
            color = Color.White,
            style = TextStyle(
                fontFamily = quicksandFamily,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Composable
fun BottomBar(
    text1: String,
    text2: String,
    navController: NavController,
    onClickBack: () -> Unit,
    onClickContinue: () -> Unit
) {
    NavigationBar(
        containerColor = Color(0xFF3c6391)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            BottomBarButton(text1, onClick = { onClickBack() })
            BottomBarButton(text2, onClick = { onClickContinue() })
        }
    }
}

@Composable
fun BottomBarButton(text: String, onClick: () -> Unit) {

    val context = LocalContext.current
    val spacebar = remember { MediaPlayer.create(context, R.raw.spacebar) }

    Button(
        onClick = {
            if (spacebar.isPlaying) {
                spacebar.stop()
                spacebar.prepare()
            }
            spacebar.start()
            onClick()
        },
        colors = ButtonDefaults.buttonColors(Color.White),
        modifier = Modifier
            .width(150.dp)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(30.dp)
            )
    ) {
        Text(
            text = text,
            color = Color.Black,
            style = TextStyle(
                fontFamily = quicksandFamily,
                fontSize = 22.sp,
                fontWeight = FontWeight.Normal
            )
        )
    }
}