package com.example.codeclicker.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.codeclicker.R
import com.example.codeclicker.Routes
import com.example.codeclicker.start.BottomBarButton
import com.example.codeclicker.start.TopBar

@Composable
fun NavigationGame(
    charac: Int,
    text: String,
    selectedLanguage: String
) {

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
                    ClickerScreen(charac, text, selectedLanguage)
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
        NavItem(icono = painterResource(R.drawable.dos), label = "Github"),
        NavItem(icono = painterResource(R.drawable.dos), label = "CodeClicker"),
        NavItem(icono = painterResource(R.drawable.dos), label = "Ranking")
    )

    var selectedIndex by rememberSaveable { mutableIntStateOf(1) }

    NavigationBar(
        containerColor = Color(0xFF3c6391)
    ) {
        listaIconos.forEachIndexed { index, navItem ->
            NavigationBarItem(
                icon = { Icon(painter = navItem.icono, contentDescription = navItem.label) },
                label = { Text(navItem.label) },
                selected = selectedIndex == index,
                onClick = {
                    selectedIndex = index
                    when (index) {
                        0 -> navController.navigate(Routes.GithubScreen)
                        1 -> navController.navigate(Routes.ClickerScreen)
                        2 -> navController.navigate(Routes.RankingScreen)
                    }
                }
            )
        }
    }
}
