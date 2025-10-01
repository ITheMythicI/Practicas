package com.example.segundodia.view

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.segundodia.R
import com.example.segundodia.components.MainButton
import com.example.segundodia.components.MainIconButton
import com.example.segundodia.components.Space
import com.example.segundodia.components.TitleBar
import com.example.segundodia.data.CarsDetailsRepo
import com.example.segundodia.ui.theme.col
import com.example.segundodia.ui.theme.col2

private val muscleIds = setOf(1, 11, 12, 13)
private val jdmIds    = setOf(2, 21, 22, 23)

private fun titleFor(id: Int) = when (id) {
    1  -> "MUSCLE"
    2  -> "JDM"
    11 -> "MUSTANG"
    12 -> "CAMARO"
    13 -> "CHALLENGER"
    21 -> "GTR"
    22 -> "CIVIC"
    23 -> "SUPRA"
    else -> "Details"
}

private fun brushFor(id: Int): Brush =
    when {
        id in muscleIds -> col
        id in jdmIds    -> col2
        else -> Brush.verticalGradient(listOf(Color.Gray, Color.DarkGray))
    }
private data class CarItem(
    @DrawableRes val drawable: Int,
    val detailId: Int,
    val label: String
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsView(navController: NavController, id: Int) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { TitleBar(titleFor(id)) },
                modifier = Modifier.background(brushFor(id)),
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent
                ),
                navigationIcon = {
                    MainIconButton(icon = Icons.AutoMirrored.Filled.ArrowBack) {
                        navController.popBackStack()
                    }
                }
            )
        }
    ) { innerPadding ->
        DetailsBody(
            navController = navController,
            id = id,
            contentPadding = innerPadding
        )
    }
}
@Composable
private fun DetailsBody(
    navController: NavController,
    id: Int,
    contentPadding: PaddingValues
) {
    val muscleItems = remember {
        listOf(
            CarItem(R.drawable.m1,   11, "Mustang"),
            CarItem(R.drawable.c1,   12, "Camaro"),
            CarItem(R.drawable.d1,   13, "Challenger"),
        )
    }
    val jdmItems = remember {
        listOf(
            CarItem(R.drawable.gtr,   21, "GT-R"),
            CarItem(R.drawable.civic, 22, "Civic"),
            CarItem(R.drawable.supra, 23, "Supra"),
        )
    }

    when (id) {
        1 -> CategoryList(
            items = muscleItems,
            onItemClick = { detailId -> navController.navigate("Detail/$detailId") },
            contentPadding = contentPadding
        )

        2 -> CategoryList(
            items = jdmItems,
            onItemClick = { detailId -> navController.navigate("Detail/$detailId") },
            contentPadding = contentPadding
        )

        11, 12, 13, 21, 22, 23 -> CarDetail(id = id, contentPadding = contentPadding)

        else -> Box(
            Modifier
                .fillMaxSize()
                .padding(contentPadding),
            contentAlignment = Alignment.Center
        ) { Text("Detalle no disponible") }
    }
}
@Composable
private fun CategoryList(
    items: List<CarItem>,
    onItemClick: (Int) -> Unit,
    contentPadding: PaddingValues
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)
            .background(Color.DarkGray),
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items) { item ->
            CarHeroCard(img = item.drawable, tag = item.label) {
                onItemClick(item.detailId)
            }
        }
    }
}
@Composable
private fun CarHeroCard(
    @DrawableRes img: Int,
    tag: String,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        shape = MaterialTheme.shapes.medium,
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
            Surface(
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.92f),
                shape = MaterialTheme.shapes.small,
                tonalElevation = 0.dp,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(12.dp)
            ) {
                Text(
                    text = tag.uppercase(),
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}
@Composable
private fun CarDetail(id: Int, contentPadding: PaddingValues) {
    val data = CarsDetailsRepo.byId(id)

    if (data == null) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(contentPadding),
            contentAlignment = Alignment.Center
        ) {
            Text("Detalle no disponible")
        }
        return
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)
            .background(Color.DarkGray),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                shape = MaterialTheme.shapes.medium,
                elevation = CardDefaults.cardElevation(6.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16 / 9f)
            ) {
                Image(
                    painter = painterResource(data.image),
                    contentDescription = data.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
        item {
            Text(
                text = data.name,
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White
            )
        }
        item {
            SpecsSection(specs = data.specs)
        }
    }
}
@Composable
private fun SpecsSection(specs: List<Pair<String, String>>) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.08f)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Ficha técnica", color = Color.White, style = MaterialTheme.typography.titleMedium)
            specs.forEach { (k, v) ->
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(k, color = Color.LightGray)
                    Text(v, color = Color.White)
                }
            }
        }
    }
}

@Composable
private fun DescriptionBlock(text: String) {

    Column(Modifier.fillMaxWidth()) {
        Text(
            text = text,
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium,
        )
        Spacer(Modifier.height(8.dp))
    }
}


