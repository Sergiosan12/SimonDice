package com.example.simondice

import android.util.Log
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


/**
 * Clase ModelView que se encarga de manejar la lógica de la aplicación.
 */
class ModelView() : ViewModel() {

    private val TAG_LOG = "miDebug"

    //Variable que almacena el estado del juego como observable.
    val estadoLiveData : MutableLiveData<Datos.Estados> = MutableLiveData(Datos.Estados.INICIO)

    //Lista de botones
    var buttons = mutableStateOf(listOf<Datos.ButtonData>())

    //Variable que almacena el mensaje que se muestra en la pantalla
    var mensajeC = mutableStateOf("")

    init {
        Log.d(TAG_LOG,"Estado: ${estadoLiveData.value}")
        buttons.value = getButtons()
    }

    /**
     * Función que crea un número aleatorio y lo asigna a la variable numero.
     */
    fun crearRandomBoton() {
        estadoLiveData.value = Datos.Estados.GENERANDO
        val randomButtonIndex = (1..4).random()
        Datos.numero = randomButtonIndex
        mensajeC.value = Datos.ColorButton.values().first { it.value == randomButtonIndex }.label
        Log.d(TAG_LOG, "Estado: ${estadoLiveData.value} - creado random $randomButtonIndex ")
        estadoLiveData.value=Datos.Estados.ADIVINANDO
        Log.d(TAG_LOG,"Estado: ${estadoLiveData.value}")
    }

    /**
     * Función que compara el número del botón con el número generado aleatoriamente.
     * @param buttonValue Número del botón seleccionado.
     * @return Verdadero si el número del botón es igual al número generado aleatoriamente, falso en caso contrario.
     */
    fun compararNumeros(buttonValue: Int): Boolean {
        return buttonValue == Datos.numero
    }

    /**
     * Función que inicia el juego.
     */
    fun empezarJugar() {
        estadoLiveData.value = Datos.Estados.GENERANDO
        crearRandomBoton()
    }

    /**
     * Función que finaliza el juego.
     */
    fun endGame() {
        estadoLiveData.value = Datos.Estados.PERDIDO
        mensajeC.value = "Perdiste"
        Log.d(TAG_LOG,"Estado: ${estadoLiveData.value}")
    }

    /**
     * Función que retorna una lista de botones.
     */
    fun getButtons(): List<Datos.ButtonData> {
        return listOf(
            Datos.ButtonData(Datos.ColorButton.VERDE, RoundedCornerShape(topStart = 180.dp)),
            Datos.ButtonData(Datos.ColorButton.ROJO, RoundedCornerShape(topEnd = 180.dp)),
            Datos.ButtonData(Datos.ColorButton.AMARILLO, RoundedCornerShape(bottomStart = 180.dp)),
            Datos.ButtonData(Datos.ColorButton.AZUL, RoundedCornerShape(bottomEnd = 180.dp))
        )
    }
}