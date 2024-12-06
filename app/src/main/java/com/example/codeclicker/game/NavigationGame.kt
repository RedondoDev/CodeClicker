package com.example.codeclicker.game

import androidx.compose.foundation.Indication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.codeclicker.R
import com.example.codeclicker.Routes
import com.example.codeclicker.start.BottomBarButton
import com.example.codeclicker.start.TopBar
import com.example.codeclicker.start.YourCharacter

@Composable
fun NavigationGame(
    selectedCharacterIndex: Int,
    text: String,
    selectedLanguage: String
) {
    val yourCharacter = YourCharacter(selectedCharacterIndex, text, selectedLanguage, 1,0, false)

    val navController = rememberNavController()

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = { TopBar(text) },
        bottomBar = { BottomBar2(navController) }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Routes.ClickerScreen,
            builder = {
                composable(Routes.GithubScreen) {
                    GithubScreen()
                }
                composable(Routes.ClickerScreen) {
                    ClickerScreen(yourCharacter)
                }
                composable(Routes.RankingScreen) {
                    RankingScreen()
                }
            },
            modifier = Modifier.padding(innerPadding)
        )
    }

}

@Composable
fun BottomBar2(navController: NavController) {
    val listaIconos = listOf(
        NavItem(icono = painterResource(R.drawable.github), label = "Github"),
        NavItem(icono = painterResource(R.drawable.logonavbar), label = "CodeClicker"),
        NavItem(icono = painterResource(R.drawable.star), label = "Ranking")
    )

    var selectedIndex by rememberSaveable { mutableIntStateOf(1) }
    val interactionSource = remember { MutableInteractionSource() }

    NavigationBar(
        containerColor = Color(0xFF3c6391)
    ) {
        listaIconos.forEachIndexed { index, navItem ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = navItem.icono,
                        contentDescription = navItem.label,
                        tint = if (selectedIndex == index) Color.White else Color(0xFFA7ACBB)
                    )
                },
                label = { },
                selected = false,
                onClick = {
                    selectedIndex = index
                    when (index) {
                        0 -> navController.navigate(Routes.GithubScreen)
                        1 -> navController.navigate(Routes.ClickerScreen)
                        2 -> navController.navigate(Routes.RankingScreen)
                    }
                },
                modifier = Modifier
                    .size(45.dp)
            )
        }
    }
}
