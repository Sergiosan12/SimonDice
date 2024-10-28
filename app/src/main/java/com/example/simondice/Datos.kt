package com.example.simondice

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color

object Datos {

    var numero: Int = 0

    enum class ColorButton(val color: Color, val label: String) {
        VERDE(Color.Green, "Verde"),
        ROJO(Color.Red, "Rojo"),
        AMARILLO(Color.Yellow, "Amarillo"),
        AZUL(Color.Blue, "Azul")
    }

    data class ButtonData(val colorButton: ColorButton, val shape: RoundedCornerShape)
}
