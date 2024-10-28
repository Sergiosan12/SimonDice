package com.example.simondice

import android.util.Log
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel

class ModelView() : ViewModel() {

    private val TAG_LOG = "miDebug"

    var buttons = mutableStateOf(listOf<Datos.ButtonData>())

    init {
        buttons.value = getButtons()
    }

    fun crearRandomBoton() {
        val randomButtonIndex = (1..4).random()
        Datos.numero = randomButtonIndex
        Log.d(TAG_LOG, "Random: $randomButtonIndex")
    }

    fun getButtons(): List<Datos.ButtonData> {
        return listOf(
            Datos.ButtonData(Datos.ColorButton.VERDE, RoundedCornerShape(topStart = 180.dp)),
            Datos.ButtonData(Datos.ColorButton.ROJO, RoundedCornerShape(topEnd = 180.dp)),
            Datos.ButtonData(Datos.ColorButton.AMARILLO, RoundedCornerShape(bottomStart = 180.dp)),
            Datos.ButtonData(Datos.ColorButton.AZUL, RoundedCornerShape(bottomEnd = 180.dp))
        )
    }
}
