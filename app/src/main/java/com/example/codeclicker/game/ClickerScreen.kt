package com.example.codeclicker.game

import android.widget.Toast
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codeclicker.R
import com.example.codeclicker.load.DataBase
import com.example.codeclicker.start.CharacterData
import com.example.codeclicker.start.YourCharacter
import com.example.codeclicker.ui.theme.quicksandFamily
import java.util.LinkedList
import kotlin.random.Random

const val roadToSenior = 100 // Balancear

@Composable
fun ClickerScreen(yourCharacter: YourCharacter, dataBase: DataBase) {

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

    var currentToast by remember { mutableStateOf<Toast?>(null) }
    val context = LocalContext.current

    val junior = charactersList[yourCharacter.selectedCharacterIndex].image1
    val senior = charactersList[yourCharacter.selectedCharacterIndex].image2
    val evolved by rememberSaveable { mutableStateOf(false) }

    var clics by rememberSaveable { mutableIntStateOf(yourCharacter.clics) }
    var money by rememberSaveable { mutableIntStateOf(yourCharacter.money) }

    val progress = yourCharacter.clics.toFloat() + 1
    val mProgress =
        animateFloatAsState(progress / roadToSenior, label = "Progreso") // Cambiar cantidad de clics

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(
                start = 10.dp,
                end = 10.dp
            )
    ) {
        Image(
            painter = painterResource(R.drawable.casa),
            "Room image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        )
    }

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
                    if (yourCharacter.copilot == 0) {
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
                tint = if (yourCharacter.copilot == 0) Color.Gray else Color.Black,
                modifier = Modifier.padding(5.dp)
            )
            if (yourCharacter.copilot == 0) {
                Icon(
                    painterResource(R.drawable.diagonal_roja),
                    "",
                    tint = Color(0xFFC56D6D),
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
        var randomLineIndex by remember { mutableIntStateOf(0) }
        var randomFontSize by remember { mutableIntStateOf(Random.nextInt(12, 22)) }
        var line by remember { mutableStateOf(javaLanguage.lines[randomLineIndex]) }

        val lineList by rememberSaveable { mutableStateOf(LinkedList<CodeLine>()) }

        Box(
            contentAlignment = Alignment.BottomStart,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .height(365.dp)
        ) {
            for (line in lineList) {
                var enabled by remember { mutableStateOf(false) } // Tymur Boolean
                val paddingAnimation by animateDpAsState(
                    if (enabled) 250.dp else 0.dp,
                    animationSpec = tween(3000),
                    label = "Padding"
                )
                val alphaAnimation by animateFloatAsState(
                    if (enabled) 0f else 1f,
                    animationSpec = tween(3000),
                    label = "alpha"
                )
                val fontSizeAnimation by animateFloatAsState(
                    (if (enabled) line.size else 0).toFloat(),
                    animationSpec = tween(200),
                    label = "Padding"
                )
                Text(
                    text = line.text,
                    style = TextStyle(
                        fontFamily = quicksandFamily,
                        fontSize = fontSizeAnimation.sp,
                        fontWeight = FontWeight.Normal
                    ),
                    modifier = Modifier
                        .align(alignment = Alignment.BottomCenter)
                        .offset(
                            x = line.initialX.dp,
                            y = -paddingAnimation
                        )
                        .alpha(alpha = alphaAnimation)
                )
                enabled = true
            }
        }


        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .width(320.dp)
                .height(320.dp)
                .padding(
                    top = 70.dp,
                    start = 40.dp,
                    end = 40.dp,
                    bottom = 0.dp
                )
                .bounceClick()
                .clickableWithoutRipple(
                    interactionSource = interactionSource,
                    onClick = {
                        clics++
                        yourCharacter.clics = clics
                        dataBase.updateClics(yourCharacter.clics)

                        money++
                        yourCharacter.money = money
                        dataBase.updateMoney(yourCharacter.money)

                        randomLineIndex = when (yourCharacter.language) {
                            "Java" -> Random.nextInt(javaLanguage.lines.size)
                            "Kotlin" -> Random.nextInt(kotlinLanguage.lines.size)
                            "Swift" -> Random.nextInt(swiftLanguage.lines.size)
                            "C" -> Random.nextInt(cLanguage.lines.size)
                            "Python" -> Random.nextInt(pythonLanguage.lines.size)
                            else -> Random.nextInt(javascriptLanguage.lines.size)
                        }

                        line = when (yourCharacter.language) {
                            "Java" -> javaLanguage.lines[randomLineIndex]
                            "Kotlin" -> kotlinLanguage.lines[randomLineIndex]
                            "Swift" -> swiftLanguage.lines[randomLineIndex]
                            "C" -> cLanguage.lines[randomLineIndex]
                            "Python" -> pythonLanguage.lines[randomLineIndex]
                            else -> javascriptLanguage.lines[randomLineIndex]
                        }

                        randomFontSize = Random.nextInt(12, 20)
                        val initialX = (-100..100).random()

                        lineList.add(CodeLine(line, randomFontSize, initialX))

                    }
                )
        ) {

            Image(
                painter = if (yourCharacter.clics < roadToSenior) junior else senior,
                "Junior/Senior Image",
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

