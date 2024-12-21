package com.example.codeclicker.load

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.codeclicker.R
import com.example.codeclicker.Routes
import kotlinx.coroutines.delay

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScreen(navController: NavHostController) {

    val splashDuration = 3000
    val context = LocalContext.current


    Splash(splashDuration)

    LaunchedEffect(key1 = true) {
        val manager = LogInManager(context as ComponentActivity)
        delay(3000)
        navController.popBackStack()
        val registered = manager.signInGoogle()
        val userId = registered?.userId
        println("userId en SplashScreen: $userId")

        if (registered == null) {
            context.finish()
        } else {
            if (registered.created) {
                val dataBase = userId?.let { DataBase(context, it) }
                val character = dataBase?.getCharacter()
                if (character != null) {
                    navController.navigate("${Routes.NavigationGame}/$userId/-1//")
                    println("YA ESTÁ CREADO, A JUGAR")
                } else {
                    navController.navigate("${Routes.CharacterScreen}/$userId")
                }
            } else {
                    println("EN SPLASH")
                    navController.navigate("${Routes.CharacterScreen}/$userId")
                    println("AÚN NO ESTÁ CREADO, A CREAR")

            }
        }
    }

}

@Composable
fun Splash(splashDuration: Int) {
    var animated by rememberSaveable { mutableStateOf(false) }
    val alpha: Float by animateFloatAsState(
        targetValue = if (animated) 1f else 0f,
        animationSpec = tween(durationMillis = splashDuration),
        label = "alpha"
    )
    LaunchedEffect(Unit) {
        animated = true
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .alpha(alpha)
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "Logo"
        )
    }
}
