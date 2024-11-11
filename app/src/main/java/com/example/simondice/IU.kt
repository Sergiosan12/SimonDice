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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.delay

@Composable
fun IU(viewModel: ModelView) {
    val TAG_LOG = "miDebug"
    val estado by viewModel.estadoLiveData.observeAsState(Estados.INICIO)

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.background(Color.Black)
    ) {
        if (estado == Estados.PERDIDO) {
            Text(
                text = "Perdiste",
                color = Color.White,
                modifier = Modifier.padding(16.dp)
            )
        }

        Botones(viewModel, estado, TAG_LOG)
        Boton_Start(viewModel, estado)
    }
}

@Composable
fun Botones(viewModel: ModelView, estado: Estados, TAG_LOG: String) {
    val buttons = viewModel.getButtons()
    val mensajeC by viewModel.mensajeC
    var iluminado by remember { mutableStateOf<ColorButton?>(null) }

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
                    val isIlluminated = mensajeC == buttonData.colorButton.label || iluminado == buttonData.colorButton
                    Button(
                        onClick = {
                            if (estado == Estados.ADIVINANDO) {
                                Log.d(TAG_LOG, buttonData.colorButton.label)
                                iluminado = buttonData.colorButton
                                val isCorrect = viewModel.compararColorSeleccionado(buttonData.colorButton)
                                if (!isCorrect) {
                                    viewModel.endGame()
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isIlluminated) buttonData.colorButton.color.copy(alpha = 0.5f) else buttonData.colorButton.color
                        ),
                        modifier = Modifier
                            .padding(5.dp)
                            .size(width = 180.dp, height = 180.dp),
                        shape = buttonData.shape,
                    ) {
                    }

                    // Reset illumination after a short delay
                    if (iluminado == buttonData.colorButton) {
                        LaunchedEffect(iluminado) {
                            delay(500)
                            iluminado = null
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Boton_Start(viewModel: ModelView, estado: Estados) {
    Button(
        onClick = {
            viewModel.empezarJugar()
        },
        modifier = Modifier
            .padding(5.dp)
            .size(width = 180.dp, height = 50.dp),
        enabled = estado == Estados.INICIO || estado == Estados.PERDIDO
    ) {
        Text("Start")
    }
}