package com.example.segundodia.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.segundodia.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController){
    LaunchedEffect(key1=true) {
        delay(1500)
        navController.navigate("Home"){
            popUpTo("Home"){

            }
        }
    }

    Box(
      contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize().background(Color.DarkGray)
    ){
        Image(
            painter= painterResource(R.drawable.leyendasdelacarretera),
            contentDescription = "Logo"
            //modifier = Modifier.fillMaxWidth()
        )
    }
}