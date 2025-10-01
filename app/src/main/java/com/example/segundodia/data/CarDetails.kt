package com.example.segundodia.data

import androidx.annotation.DrawableRes
import com.example.segundodia.R

enum class CarCategory { MUSCLE, JDM }

data class CarDetailData(
    val id: Int,
    val name: String,
    @DrawableRes val image: Int,
    val description: String,
    val specs: List<Pair<String, String>>,
    val category: CarCategory
)

object CarsDetailsRepo {
    private val cars = listOf(
        // --- MUSCLE ---
        CarDetailData(
            id = 11,
            name = "Mustang GT",
            image = R.drawable.m1,
            description = "Ícono muscle con V8 5.0, buen balance entre calle y pista.",
            specs = listOf(
                "Motor" to "V8 5.0L",
                "Potencia" to "450–480 hp",
                "Tracción" to "RWD",
                "Transmisión" to "Manual/Auto",
            ),
            category = CarCategory.MUSCLE
        ),
        CarDetailData(
            id = 12,
            name = "Camaro SS",
            image = R.drawable.c1,
            description = "V8 6.2L, chasis rígido y respuesta lineal. Estética agresiva.",
            specs = listOf(
                "Motor" to "V8 6.2L",
                "Potencia" to "455 hp",
                "Tracción" to "RWD",
                "Transmisión" to "Manual/Auto",
            ),
            category = CarCategory.MUSCLE
        ),
        CarDetailData(
            id = 13,
            name = "Challenger Hellcat",
            image = R.drawable.d1,
            description = "Supercargado brutal, personalidad única y sonido demoledor.",
            specs = listOf(
                "Motor" to "V8 6.2L SC",
                "Potencia" to "700+ hp",
                "Tracción" to "RWD",
                "Transmisión" to "Auto",
            ),
            category = CarCategory.MUSCLE
        ),

        // --- JDM ---
        CarDetailData(
            id = 21,
            name = "Nissan GT-R",
            image = R.drawable.gtr,
            description = "Tracción y electrónica top; aceleración feroz, muy estable.",
            specs = listOf(
                "Motor" to "V6 3.8L TT",
                "Potencia" to "480–600+ hp",
                "Tracción" to "AWD",
                "Transmisión" to "DCT",
            ),
            category = CarCategory.JDM
        ),
        CarDetailData(
            id = 22,
            name = "Honda Civic (Type R/Si)",
            image = R.drawable.civic,
            description = "Chasis ligero y dirección precisa; muy divertido en curvas.",
            specs = listOf(
                "Motor" to "I4 2.0L (VTEC/Turbo)",
                "Potencia" to "200–320 hp",
                "Tracción" to "FWD",
                "Transmisión" to "Manual",
            ),
            category = CarCategory.JDM
        ),
        CarDetailData(
            id = 23,
            name = "Toyota Supra",
            image = R.drawable.supra,
            description = "Leyenda del tuning; gran potencial (2JZ/B58).",
            specs = listOf(
                "Motor" to "I6 3.0L",
                "Potencia" to "320–382 hp",
                "Tracción" to "RWD",
                "Transmisión" to "Manual/Auto",
            ),
            category = CarCategory.JDM
        ),
    )

    fun byId(id: Int): CarDetailData? = cars.find { it.id == id }

    fun listForCategoryId(categoryId: Int): List<CarDetailData> =
        when (categoryId) {
            1 -> cars.filter { it.category == CarCategory.MUSCLE }
            2 -> cars.filter { it.category == CarCategory.JDM }
            else -> emptyList()
        }
}
