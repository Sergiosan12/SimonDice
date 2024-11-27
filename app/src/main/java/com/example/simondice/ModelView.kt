package com.example.simondice

import android.util.Log
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/**
 * Clase ModelView que se encarga de manejar la lógica de la aplicación.
 */
class ModelView() : ViewModel() {

    private val TAG_LOG = "miDebug"

    //Variable que almacena el estado del juego como observable.
    val estadoLiveData : MutableLiveData<Estados> = MutableLiveData(Estados.INICIO)

    //Lista de botones
    var buttons = mutableStateOf(listOf<ButtonData>())

    //Variable que almacena el mensaje que se muestra en la pantalla
    var mensajeC = mutableStateOf("")

    //Lista de colores con mutableList para agregar y eliminar elementos
    private val secuenciaColores = mutableListOf<ColorButton>()

    //Variable que almacena el índice actual de la secuencia de colores
    private var indiceActual = 0

    /**
     * Inicialización de la clase ModelView.
     * Se inicializa el estado del juego y se obtienen los botones.
     */
    init {
        Log.d(TAG_LOG,"Estado: ${estadoLiveData.value}")
        buttons.value = getButtons()
    }

    /**
     * Función que inicia el juego.
     */
    fun empezarJugar() {
        estadoLiveData.value = Estados.GENERANDO
        secuenciaColores.clear()
        agregarColorASecuencia()
    }

    /**
     * Función que agrega un color a la secuencia de colores para que el usuario adivine.
     * Incrementa la ronda.
     * Muestra la secuencia de colores.
     */
    fun agregarColorASecuencia() {
        val randomButtonIndex = (1..4).random()
        val nuevoColor = ColorButton.values().first { it.value == randomButtonIndex }
        secuenciaColores.add(nuevoColor)
        Datos.ronda.value = Datos.ronda.value?.plus(1) // Incrementa la ronda
        mostrarSecuencia()
    }

    /**
     * Función que muestra la secuencia de colores.
     * Muestra un color por un tiempo determinado.
     * Cambia el estado a ADIVINANDO.
     */
    private fun mostrarSecuencia() {
        viewModelScope.launch {
            for (color in secuenciaColores) {
                mensajeC.value = color.label
                delay(500)
                mensajeC.value = ""
                delay(500)
            }
            delay(500)
            estadoLiveData.value = Estados.ADIVINANDO
            indiceActual = 0
        }
    }

    /**
     * Función que compara el color seleccionado con el color de la secuencia.
     * Si el color seleccionado es correcto, se incrementa el índice actual.
     * Si el índice actual es igual al tamaño de la secuencia, se cambia el estado a GENERANDO.
     */
    fun compararColorSeleccionado(colorSeleccionado: ColorButton): Boolean {
        if (colorSeleccionado == secuenciaColores[indiceActual]) {
            indiceActual++
            if (indiceActual == secuenciaColores.size) {
                estadoLiveData.value = Estados.GENERANDO
                viewModelScope.launch {
                    delay(1500)
                    agregarColorASecuencia()
                }
            }
            return true
        } else {
            endGame()
            return false
        }
    }
    /**
     * Función que finaliza el juego.
     * Cambia el estado a PERDIDO y muestra un mensaje en la pantalla.
     */
    fun endGame() {
        estadoLiveData.value = Estados.PERDIDO
        mensajeC.value = "Perdiste"
        Datos.ronda.value = 0
        Log.d(TAG_LOG, "Estado: ${estadoLiveData.value}")
    }

    /**
     * Función que retorna una lista de botones.
     */
    fun getButtons(): List<ButtonData> {
        return listOf(
            ButtonData(ColorButton.VERDE, RoundedCornerShape(topStart = 180.dp)),
            ButtonData(ColorButton.ROJO, RoundedCornerShape(topEnd = 180.dp)),
            ButtonData(ColorButton.AMARILLO, RoundedCornerShape(bottomStart = 180.dp)),
            ButtonData(ColorButton.AZUL, RoundedCornerShape(bottomEnd = 180.dp))
        )
    }


}