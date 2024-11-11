package com.example.simondice

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color

object Datos {
    // Se inicializa la variable numero en 0
    var numero: Int = 0
}

    // Se crea un enum class con los colores de los botones y sus valores asociados
    enum class ColorButton(val color: Color, val label: String, val value: Int) {
        VERDE(Color.Green, "Verde", 1),
        ROJO(Color.Red, "Rojo", 2),
        AMARILLO(Color.Yellow, "Amarillo", 3),
        AZUL(Color.Blue, "Azul", 4)
    }

    // Se crea una data class con los datos de los botones
    data class ButtonData(val colorButton: ColorButton, val shape: RoundedCornerShape)

    enum class Estados (val value: Int, val label: String) {
        INICIO(0, "Inicio"),
        GENERANDO(1, "Generando"),
        ADIVINANDO(2, "Adivinando"),
        PERDIDO(3, "Perdido")

    }

    enum class EstadosAuxiliares(val txt: String) {
        AUX1(txt = "aux1"),
        AUX2(txt = "aux2"),
        AUX3(txt = "aux3"),
    }
