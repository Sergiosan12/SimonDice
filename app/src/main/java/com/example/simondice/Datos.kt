package com.example.simondice

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color

object Datos {

    var numero: Int = 0

    enum class ColorButton(val color: Color, val label: String, val value: Int) {
        VERDE(Color.Green, "Verde", 1),
        ROJO(Color.Red, "Rojo", 2),
        AMARILLO(Color.Yellow, "Amarillo", 3),
        AZUL(Color.Blue, "Azul", 4)
    }

    data class ButtonData(val colorButton: ColorButton, val shape: RoundedCornerShape)
}