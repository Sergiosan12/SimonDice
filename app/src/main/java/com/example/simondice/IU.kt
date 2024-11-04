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

@Composable
fun IU(viewModel: ModelView) {

    val TAG_LOG = "miDebug"

    var color by remember { mutableStateOf("") }
    val buttons = viewModel.getButtons()
    val gameActive by viewModel.gameActive
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
                                if (gameActive) { // Si el juego está activo
                                    Log.d(TAG_LOG, buttonData.colorButton.label)
                                    color = buttonData.colorButton.label
                                    val isCorrect = viewModel.compararNumeros(buttonData.colorButton.value)
                                    if (isCorrect) { // Si el botón seleccionado es correcto
                                        viewModel.crearRandomBoton() // Se crea un nuevo número aleatorio
                                    } else {
                                        viewModel.endGame()  // Se finaliza el juego
                                    }
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = buttonData.colorButton.color
                            ),
                            modifier = Modifier
                                .padding(5.dp)
                                .size(width = 180.dp, height = 180.dp),
                            shape = buttonData.shape
                        ) {
                        }
                    }
                }
            }

            Button(
                onClick = {
                    viewModel.empezarJugar()
                },
                modifier = Modifier
                    .padding(5.dp)
                    .size(width = 180.dp, height = 50.dp)
            ) {
                Text("Start")
            }
        }
    }
}