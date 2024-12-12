package com.example.codeclicker

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.codeclicker.game.ClickerScreen
import com.example.codeclicker.game.NavigationGame
import com.example.codeclicker.load.LogInManager
import com.example.codeclicker.load.SplashScreen
import com.example.codeclicker.start.CharacterScreen
import com.example.codeclicker.start.LanguageScreen
import com.example.codeclicker.ui.theme.CodeClickerTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityInfo.SCREEN_ORIENTATION_PORTRAIT.also { requestedOrientation = it };
        setContent {
            CodeClickerTheme {

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Routes.SplashScreen,
                    builder = {
                        composable(Routes.SplashScreen) {
                            SplashScreen(navController)
                        }
                        composable(Routes.CharacterScreen) {
                            CharacterScreen(navController)
                        }
                        composable("${Routes.LanguageScreen}/{selectedCharacterIndex}") {
                            val selectedCharacterIndex =
                                it.arguments?.getString("selectedCharacterIndex")?.toInt() ?: 0
                            LanguageScreen(navController, selectedCharacterIndex)
                        }
                        composable("${Routes.NavigationGame}/{selectedCharacterIndex}/{text}/{selectedLanguage}") { backStackEntry ->
                            val selectedCharacterIndex = backStackEntry.arguments?.getString("selectedCharacterIndex")?.toInt() ?: 0
                            val text = backStackEntry.arguments?.getString("text") ?: ""
                            val selectedLanguage = backStackEntry.arguments?.getString("selectedLanguage") ?: ""
                            NavigationGame(selectedCharacterIndex, text, selectedLanguage)
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
