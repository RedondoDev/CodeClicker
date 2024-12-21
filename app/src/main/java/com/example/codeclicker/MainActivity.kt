package com.example.codeclicker

import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.codeclicker.game.NavigationGame
import com.example.codeclicker.load.DataBase
import com.example.codeclicker.load.SplashScreen
import com.example.codeclicker.start.CharacterScreen
import com.example.codeclicker.start.LanguageScreen
import com.example.codeclicker.start.YourCharacter
import com.example.codeclicker.ui.theme.CodeClickerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityInfo.SCREEN_ORIENTATION_PORTRAIT.also { requestedOrientation = it };
        setContent {
            CodeClickerTheme {

                val navController = rememberNavController()
                val context = LocalContext.current as Activity

                NavHost(
                    navController = navController,
                    startDestination = Routes.SplashScreen,
                    builder = {
                        composable(Routes.SplashScreen) {
                            SplashScreen(navController)
                        }
                        composable("${Routes.CharacterScreen}/{userId}") {
                            println("EN MAIN -> CHAR")
                            val userId =
                                it.arguments?.getString("userId")
                            println(userId)
                            if (userId != null) {
                                CharacterScreen(navController, userId)
                            }
                        }
                        composable("${Routes.LanguageScreen}/{userId}/{selectedCharacterIndex}") {
                            println("EN MAIN -> LANG")
                            val userId =
                                it.arguments?.getString("userId")
                            val selectedCharacterIndex =
                                it.arguments?.getString("selectedCharacterIndex")?.toInt() ?: 0
                            if (userId != null) {
                                LanguageScreen(navController, userId, selectedCharacterIndex)
                            }
                        }
                        composable("${Routes.NavigationGame}/{userId}/{selectedCharacterIndex}/{text}/{selectedLanguage}") { backStackEntry ->
                            println("EN MAIN -> NAVGAME")
                            val userId = backStackEntry.arguments?.getString("userId")
                            val selectedCharacterIndex =
                                backStackEntry.arguments?.getString("selectedCharacterIndex")
                                    ?.toInt() ?: -1
                            val text = backStackEntry.arguments?.getString("text") ?: ""
                            val selectedLanguage =
                                backStackEntry.arguments?.getString("selectedLanguage") ?: ""
                            if (userId != null) {
                                val dataBase = DataBase(context, userId)
                                if (selectedCharacterIndex != -1 && text != "" && selectedLanguage != "") {
                                    val yourCharacter = YourCharacter(
                                        selectedCharacterIndex,
                                        text,
                                        selectedLanguage
                                    )
                                    dataBase.saveCharacter(yourCharacter)
                                    println("LO CREA")
                                }

                                var character by remember { mutableStateOf<YourCharacter?>(null) }

                                LaunchedEffect(userId) {
                                    character = dataBase.getCharacter()
                                    println("LO CARGA")
                                }

                                character?.let {
                                    NavigationGame(dataBase, it)
                                }
                            } else {
                                println("userId es nulo")
                            }
                        }
                    }
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // Guardar el Time
        // Cargar el Time en el onResume
        hideSystemUi()
    }

    private fun hideSystemUi() {
        val view = window.decorView
        val insetsController = WindowCompat.getInsetsController(window, view)
        insetsController.apply {
            hide(WindowInsetsCompat.Type.systemBars())
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

}
