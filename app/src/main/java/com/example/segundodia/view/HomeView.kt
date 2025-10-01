package com.example.segundodia.view

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.segundodia.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(navController: NavController) {
    val col: Brush = Brush.verticalGradient(listOf(Color.Red, Color.Magenta))

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "LEYENDAS DE LA CARRETERA",
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace
                    )
                },
                modifier = Modifier.background(brush = col),
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground
        ) {
            ContentHomeView(navController)
        }
    }
}

@Composable
private fun ContentHomeView(navController: NavController) {
    val items = listOf(
        HeroCardData(R.drawable.muscle, "MUSCLE") { navController.navigate("Detail/1") },
        HeroCardData(R.drawable.jdm,    "JDM")    { navController.navigate("Detail/2") }
        //HeroCardData(R)
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray),
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(items) { data ->
            CarHeroCard(img = data.img, tag = data.label, onClick = data.onClick)
        }
    }
}

private data class HeroCardData(
    @DrawableRes val img: Int,
    val label: String,
    val onClick: () -> Unit
)

@Composable
private fun CarHeroCard(
    @DrawableRes img: Int,
    tag: String,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        shape = CardDefaults.shape,
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16 / 9f)
    ) {
        Box(Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(img),
                contentDescription = "Imagen $tag",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Box(
                Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(
                        Brush.verticalGradient(
                            0f to Color.Transparent,
                            1f to Color.Black.copy(alpha = 0.45f)
                        )
                    )
            )
            // chip/tag
            Surface(
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.92f),
                shape = MaterialTheme.shapes.small,
                tonalElevation = 2.dp,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(12.dp)
            ) {
                Text(
                    text = tag,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}
