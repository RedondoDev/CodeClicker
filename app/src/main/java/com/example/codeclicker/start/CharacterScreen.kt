package com.example.codeclicker.start

import android.app.Activity
import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.codeclicker.R
import com.example.codeclicker.Routes

@Composable
fun CharacterScreen(navController: NavController, userId: String, modifier: Modifier = Modifier) {

    println("EN CHAR")
    val context = LocalContext.current
    val blop = remember { MediaPlayer.create(context, R.raw.blop) }

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

    var selectedCharacterIndex by rememberSaveable { mutableIntStateOf(0) }

    Scaffold(
        topBar = { TopBar("Selección de Personaje") },
        bottomBar = {
            val activity = (LocalContext.current as? Activity)
            BottomBar(
                text1 = "Salir",
                text2 = "Continuar",
                navController = navController,
                onClickBack = { activity?.finish() },
                onClickContinue = {
                    navController.navigate("${Routes.LanguageScreen}/$userId/$selectedCharacterIndex")
                    println("CharacterScreen: Navigating to LanguageScreen with userId: $userId and selectedCharacterIndex: $selectedCharacterIndex")
                })
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color(0xffcbd8e8))
        ) {
            items(charactersList.size) { index ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(185.dp)
                        .padding(15.dp, 8.dp)
                        .clip(shape = RoundedCornerShape(30.dp))
                        .clickable {
                            selectedCharacterIndex = index
                            if (blop.isPlaying) {
                                blop.stop()
                                blop.prepare()
                            }
                            blop.start()
                        }
                        .border(
                            width = if (selectedCharacterIndex == index) 4.dp else 2.dp,
                            color = if (selectedCharacterIndex == index) Color(0xFF3c6391) else Color.Black,
                            shape = RoundedCornerShape(30.dp)
                        )
                        .shadow(
                            elevation = if (selectedCharacterIndex == index) 4.dp else 1.dp,
                            shape = RoundedCornerShape(30.dp),
                            spotColor = Color.Black
                        )
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(30.dp)
                        )
                ) {
                    val charac = charactersList[index]
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
            }
        }
    }

}