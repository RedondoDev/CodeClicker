package com.example.codeclicker.game

import GithubScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.codeclicker.R
import com.example.codeclicker.Routes
import com.example.codeclicker.load.DataBase
import com.example.codeclicker.start.TopBar
import com.example.codeclicker.start.YourCharacter
import kotlinx.coroutines.delay

@Composable
fun NavigationGame(
    dataBase: DataBase,
    character: YourCharacter
) {

    val navController = rememberNavController()

    LaunchedEffect(key1 = true) {
        while (true) {
            delay(3000)
            character.money += (character.bots - 1)
            dataBase.updateMoney(character.money)
        }
    }

    LaunchedEffect(character.isCooldown) {
        while (character.isCooldown && character.timer > 0) {
            delay(1000)
            character.timer--
            dataBase.updateTimer(character.timer)
        }
        character.isCooldown = false
        character.timer = 20
    }

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = { TopBar(character.name) },
        bottomBar = { BottomBar2(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.ClickerScreen,
            builder = {
                composable(Routes.GithubScreen) {
                    GithubScreen(character, dataBase)
                }
                composable(Routes.ClickerScreen) {
                    ClickerScreen(character, dataBase)
                }
                composable(Routes.RankingScreen) {
                    RankingScreen(character, dataBase)
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

    val navBackStack by navController.currentBackStackEntryAsState()
    var selectedIndex by rememberSaveable { mutableIntStateOf(1) }

    val isSelected = navBackStack?.destination?.route
    selectedIndex = when (isSelected) {
        "github_Screen" -> 0
        "clicker_Screen" -> 1
        "ranking_Screen" -> 2
        else -> 3
    }

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
