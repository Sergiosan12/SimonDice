package com.example.simondice

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.livedata.observeAsState

@Composable
fun IU(viewModel: ModelView) {
    val TAG_LOG = "miDebug"
    val estado by viewModel.estadoLiveData.observeAsState(Datos.Estados.INICIO)
    val mensajeC by viewModel.mensajeC

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.background(Color.Black)
    ) {
        BasicTextField(
            value = mensajeC,
            onValueChange = { },
            modifier = Modifier
                .padding(16.dp)
                .background(Color.White)
                .padding(8.dp),
            readOnly = true
        )

        Botones(viewModel, estado, TAG_LOG)
        Boton_Start(viewModel, estado)
    }
}

@Composable
fun Botones(viewModel: ModelView, estado: Datos.Estados, TAG_LOG: String) {
    val buttons = viewModel.getButtons()
    var color by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.background(Color.Black)
    ) {
        buttons.chunked(2).forEach { rowButtons ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.background(Color.Black)
            ) {
                rowButtons.forEach { buttonData ->
                    Button(
                        onClick = {
                            if (estado == Datos.Estados.ADIVINANDO) {
                                Log.d(TAG_LOG, buttonData.colorButton.label)
                                color = buttonData.colorButton.label
                                val isCorrect = viewModel.compararNumeros(buttonData.colorButton.value)
                                if (isCorrect) {
                                    viewModel.crearRandomBoton()
                                } else {
                                    viewModel.endGame()
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (estado == Datos.Estados.ADIVINANDO) buttonData.colorButton.color else Color.Gray
                        ),
                        modifier = Modifier
                            .padding(5.dp)
                            .size(width = 180.dp, height = 180.dp),
                        shape = buttonData.shape,
                    ) {
                    }
                }
            }
        }
    }
}

@Composable
fun Boton_Start(viewModel: ModelView, estado: Datos.Estados) {
    Button(
        onClick = {
            viewModel.empezarJugar()
        },
        modifier = Modifier
            .padding(5.dp)
            .size(width = 180.dp, height = 50.dp),
        enabled = estado == Datos.Estados.INICIO || estado == Datos.Estados.PERDIDO
    ) {
        Text("Start")
    }
}