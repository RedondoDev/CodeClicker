package com.example.codeclicker.start

import android.app.Activity
import android.media.MediaPlayer
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.codeclicker.R
import com.example.codeclicker.Routes
import com.example.codeclicker.ui.theme.quicksandFamily

@Composable
fun LanguageScreen(navController: NavController, selectedCharacterIndex: Int) {

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

    val charac = charactersList[selectedCharacterIndex]
    var text by remember { mutableStateOf("") }
    val context = LocalContext.current

    Scaffold(
        topBar = { TopBar("Selección de Personaje") },
        bottomBar = {
            BottomBar(
                text1 = "Atrás",
                text2 = "Finalizar",
                navController = navController,
                onClickBack = { navController.navigate(Routes.CharacterScreen) },
                onClickContinue = {
                    if (text.isEmpty()) {
                        Toast.makeText(context, "Introduce tu nombre", Toast.LENGTH_SHORT).show()
                    } else {
                        // Comprobación de nombre
                        // Crear en la BD
                        navController.navigate(Routes.CharacterScreen) // ClickerScreen
                    }
                })
        }
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xffcbd8e8))
                .padding(innerPadding)
        ) {
            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(
                        start = 15.dp,
                        end = 15.dp,
                        top = 15.dp,
                        bottom = 15.dp
                    )
                    .clip(shape = RoundedCornerShape(30.dp))
                    .border(
                        width = 2.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(30.dp)
                    )
                    .shadow(
                        elevation = 1.dp,
                        shape = RoundedCornerShape(30.dp),
                        spotColor = Color.Black
                    )
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(30.dp)
                    )
                    .clickable { navController.navigate(Routes.CharacterScreen) }
            ) {

                Image(
                    painter = charac.image1,
                    contentDescription = "Character junior image",
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .clip(shape = RoundedCornerShape(30.dp))
                )
                Image(
                    painter = charac.image2,
                    contentDescription = "Character senior image",
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .clip(shape = RoundedCornerShape(30.dp))
                )
            }

            Text(
                text = "Nombre",
                style = TextStyle(
                    fontFamily = quicksandFamily,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Normal
                ),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 30.dp,
                        top = 5.dp,
                        bottom = 15.dp
                    )
            )
            TextField(
                value = text,
                textStyle = TextStyle(
                    fontFamily = quicksandFamily,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal
                ),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    cursorColor = Color(0xFF3c6391),
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(66.dp)
                    .padding(
                        start = 30.dp,
                        end = 30.dp,
                        bottom = 10.dp
                    ),
                onValueChange = { text = it }
            )
            Text(
                text = "Lenguaje de Programación",
                style = TextStyle(
                    fontFamily = quicksandFamily,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Normal
                ),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 30.dp,
                        top = 15.dp,
                        bottom = 10.dp
                    )
            )

            val languageList = listOf("Java", "Kotlin", "Swift", "C", "Python", "JavaScript")

            CardsRows(languageList)

        }
    }
}

@Composable
fun CardsRows(languageList: List<String>) {

    val context = LocalContext.current
    val blop = remember { MediaPlayer.create(context, R.raw.blop) }
    var selectedLanguage by rememberSaveable { mutableStateOf<String?>(languageList[0]) }

    for (i in languageList.indices step 2) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(horizontal = 40.dp)
        ) {
            LanguageCard(
                text = languageList[i],
                isSelected = selectedLanguage == languageList[i],
                onClick = {
                    if (selectedLanguage != languageList[i]) {
                        selectedLanguage =
                            if (selectedLanguage == languageList[i]) null else languageList[i]
                    }
                    if (blop.isPlaying) {
                        blop.stop()
                        blop.prepare()
                    }
                    blop.start()
                }
            )
            if (i + 1 < languageList.size) {
                LanguageCard(
                    text = languageList[i + 1],
                    isSelected = selectedLanguage == languageList[i + 1],
                    onClick = {
                        if (selectedLanguage != languageList[i + 1]) {
                            selectedLanguage =
                                if (selectedLanguage == languageList[i + 1]) null else languageList[i + 1]
                        }
                        if (blop.isPlaying) {
                            blop.stop()
                            blop.prepare()
                        }
                        blop.start()
                    }
                )
            }
        }
    }
}

@Composable
fun LanguageCard(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(130.dp)
            .height(55.dp)
            .clip(shape = RoundedCornerShape(15.dp))
            .border(
                width = if (isSelected) 4.dp else 2.dp,
                color = if (isSelected) Color(0xFF3c6391) else Color.Black,
                shape = RoundedCornerShape(15.dp),
            )
            .shadow(
                elevation = if (isSelected) 4.dp else 2.dp,
                shape = RoundedCornerShape(15.dp),
                spotColor = Color.Black
            )
            .background(
                color = Color.White,
                shape = RoundedCornerShape(15.dp)
            )
            .clickable { onClick() }
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontFamily = quicksandFamily,
                fontSize = 22.sp,
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        )
    }
}