package com.example.codeclicker.load

import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.codeclicker.R
import com.example.codeclicker.Routes
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {

    var registered by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        delay(3000)
        navController.popBackStack()
        if (!registered) {
            navController.navigate(Routes.CharacterScreen)
        } else {
            // Hacer Log In
        }
    }

    Splash()
}

@Composable
fun Splash() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "Logo"
        )
    }
}
