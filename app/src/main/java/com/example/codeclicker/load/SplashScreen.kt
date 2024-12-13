package com.example.codeclicker.load

import android.annotation.SuppressLint
import android.window.SplashScreen
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.codeclicker.R
import com.example.codeclicker.Routes
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScreen(navController: NavHostController) {

    val splashDuration = 3000
    var registered: Registered?
    val context = LocalContext.current
    val manager = LogInManager(context as ComponentActivity)

    Splash(splashDuration)

    LaunchedEffect(key1 = true) {
        delay(3000)
        navController.popBackStack()
        registered = manager.signInGoogle()

        if (registered == null) {
            context.finish()
        } else {
            if (registered!!.created){
                // Pedir el de verdad
                navController.navigate("${Routes.NavigationGame}/1/registrado/java")
                println("YA ESTÁ CREADO, A JUGAR")
            } else {
            // Hacer Log In
                navController.navigate(Routes.CharacterScreen)
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
