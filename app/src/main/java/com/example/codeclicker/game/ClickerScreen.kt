package com.example.codeclicker.game

import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codeclicker.R
import com.example.codeclicker.start.CharacterData
import com.example.codeclicker.start.YourCharacter
import com.example.codeclicker.ui.theme.quicksandFamily

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

    val junior = charactersList[yourCharacter.selectedCharacterIndex].image1
    val senior = charactersList[yourCharacter.selectedCharacterIndex].image2

    var clics by rememberSaveable { mutableIntStateOf(yourCharacter.clics) }

    val progress = yourCharacter.clics.toFloat()
    val mProgress = animateFloatAsState(progress / 10) // Cambiar cantidad de clics
    val evolved by rememberSaveable { mutableStateOf(false) }


    var money by rememberSaveable { mutableIntStateOf(yourCharacter.money) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 10.dp,
                top = 10.dp,
                end = 5.dp,
                bottom = 10.dp
            )
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(70.dp)
                .align(Alignment.TopStart)
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
                tint = if (!unlocked) Color.Gray else Color.Black,
                modifier = Modifier.padding(5.dp)
            )
            if (!unlocked) {
                Icon(
                    painterResource(R.drawable.diagonal_roja),
                    "",
                    tint = if (!unlocked) Color(0xFFC56D6D) else Color.Red,
                    modifier = Modifier.padding(
                        start = 8.dp,
                        bottom = 8.dp,
                        top = 10.dp,
                        end = 10.dp
                    )
                )
            }
        }

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .width(70.dp)
                .height(270.dp)
                .align(Alignment.TopEnd)
                .padding(top = 10.dp)
        ) {
            Image(
                painter = senior,
                contentDescription = "Senior",
                modifier = Modifier
                    .width(64.dp)
                    .padding(end = 5.dp)
            )
            Column(
                modifier = Modifier
                    .width(12.dp)
                    .weight(1f)
                    .padding(top = 10.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .border(
                        1.dp,
                        Color.Black,
                        RoundedCornerShape(12.dp)
                    )
            ) {
                Box(
                    modifier = Modifier
                        .weight(
                            if (!evolved) {
                                (if ((1.0 - mProgress.value) <= 0.0) {
                                    0.0001f
                                } else {
                                    1 - mProgress.value
                                })
                            } else {
                                1f
                            }
                        )
                        .fillMaxWidth()
                )
                Box(
                    modifier = Modifier
                        .weight(mProgress.value)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            Color(0xffcbd8e8)
                        )
                        .border(
                            1.dp,
                            Color.Black,
                            RoundedCornerShape(12.dp)
                        )
                )
            }
            Image(
                painter = junior,
                contentDescription = "Junior",
                modifier = Modifier
                    .width(60.dp)
            )

        }

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .width(200.dp)
                .height(70.dp)
                .padding(6.dp)
                .align(Alignment.TopCenter)
        ) {
            Image(
                painterResource(R.drawable.coin),
                "Coin",
                modifier = Modifier
                    .size(25.dp)
                    .padding(
                        top = 1.dp,
                        end = 5.dp
                    )
            )
            Text(
                "${yourCharacter.money}€",
                style = TextStyle(
                    fontFamily = quicksandFamily,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Normal
                ),
            )
        }

        val interactionSource = remember { MutableInteractionSource() }

        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .width(320.dp)
                .height(320.dp)
                .bounceClick()
                .clickableWithoutRipple(
                    interactionSource = interactionSource,
                    onClick = {
                        clics++
                        yourCharacter.clics = clics
                        money++
                        yourCharacter.money = money
                        println("Clicks: ${yourCharacter.clics} y $clics y Money: ${yourCharacter.money} y $money")
                    }
                )
        ) {
            Image(
                painter = if (yourCharacter.money < 10) junior else senior, // Cambiar cantidad de clics
                "Junior Clicker",
                modifier = Modifier
                    .fillMaxSize()
            )
        }

    }

}

fun Modifier.clickableWithoutRipple(
    interactionSource: MutableInteractionSource,
    onClick: () -> Unit
) = composed(
    factory = {
        this.then(
            Modifier.clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = { onClick() }
            )
        )
    }
)

