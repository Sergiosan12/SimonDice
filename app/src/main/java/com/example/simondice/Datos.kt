package com.example.simondice

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color

object Datos {

    // Se inicializa la variable numero en 0
    var numero: Int = 0

    // Se crea un enum class con los colores de los botones y sus valores asociados
    enum class ColorButton(val color: Color, val label: String, val value: Int) {
        VERDE(Color.Green, "Verde", 1),
        ROJO(Color.Red, "Rojo", 2),
        AMARILLO(Color.Yellow, "Amarillo", 3),
        AZUL(Color.Blue, "Azul", 4)
    }

    // Se crea una data class con los datos de los botones
    data class ButtonData(val colorButton: ColorButton, val shape: RoundedCornerShape)
}