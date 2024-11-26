package com.example.codeclicker

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.codeclicker.start.CharacterScreen
import com.example.codeclicker.start.LanguageScreen
import com.example.codeclicker.ui.theme.CodeClickerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CodeClickerTheme {

                val showSystemUi by remember { mutableStateOf(false) }
                val view = LocalView.current
                val window = (view.context as Activity).window
                val insetsController = WindowCompat.getInsetsController(window, view)
                if (!view.isInEditMode) {
                    if (!showSystemUi) {
                        insetsController.apply {
                            hide(WindowInsetsCompat.Type.systemBars())
                            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                        }
                    } else { insetsController.apply { show(WindowInsetsCompat.Type.systemBars()) } }
                }

                CharacterScreen()
                // LanguageScreen()
            }
        }
    }
}

