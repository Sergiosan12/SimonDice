package com.example.simondice

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.MutableLiveData

/**
 * Clase Datos que se encarga de almacenar los datos de la aplicación.
 */
object Datos {
    // Se inicializa la variable numero en 0
    var numero: Int = 0
    var ronda : MutableLiveData <Int> = MutableLiveData(0)
}

/**
 * Enumeración de los colores de los botones.
 */
enum class ColorButton(val color: Color, val label: String, val value: Int) {
    VERDE(Color.Green, "Verde", 1),
    ROJO(Color.Red, "Rojo", 2),
    AMARILLO(Color.Yellow, "Amarillo", 3),
    AZUL(Color.Blue, "Azul", 4)
}

/**
 * Clase ButtonData que se encarga de almacenar los datos de los botones.
 */
data class ButtonData(val colorButton: ColorButton, val shape: RoundedCornerShape)

/**
 * Enumeración de los estados del juego.
 */
enum class Estados (val value: Int, val label: String) {
    INICIO(0, "Inicio"),
    GENERANDO(1, "Generando"),
    ADIVINANDO(2, "Adivinando"),
    PERDIDO(3, "Perdido")

}

