package com.example.codeclicker.start

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codeclicker.R
import com.example.codeclicker.ui.theme.quicksandFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageScreen() {
    Scaffold(
        topBar = { TopBar("Selección de Personaje") },
        bottomBar = { BottomBar() }
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
                    .height(185.dp)
                    .padding(15.dp, 8.dp)
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
            ) {
                val imageList: List<Int> = listOf(
                    R.drawable.uno,
                    R.drawable.dos,
                )

                val charactersList = listOf(
                    CharacterData(0, painterResource(imageList[0]), painterResource(imageList[1])),
                )

                val charac = charactersList[0]

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

            var text by remember { mutableStateOf("") }

            Text(
                text = "Nombre",
                style = TextStyle(
                    fontFamily = quicksandFamily,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Normal
                ),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp)
            )
            TextField(
                value = text,
                textStyle = TextStyle(
                    fontFamily = quicksandFamily,
                    fontSize = 22.sp,
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
                    .height(58.dp)
                    .padding(horizontal = 30.dp),
                onValueChange = { text = it }
            )
        }
    }
}