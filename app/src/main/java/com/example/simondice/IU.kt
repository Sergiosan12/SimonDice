// IU.kt
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

class UI {

    @Composable
    fun IU(viewModel: ModelView) {

        var color by remember { mutableStateOf("") }
        var message by remember { mutableStateOf("") }
        val buttons = viewModel.getButtons()

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.background(Color.Black)
        ) {

            BasicTextField(
                value = message,
                onValueChange = { message = it },
                modifier = Modifier
                    .padding(16.dp)
                    .background(Color.White)
                    .padding(8.dp)
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
                                    Log.d("ColorPresionado", buttonData.colorButton.label)
                                    color = buttonData.colorButton.label
                                    val isCorrect = viewModel.compararNumeros(buttonData.colorButton.value)
                                    message = if (isCorrect) "Correcto!" else "Incorrecto!"
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

                // Botón Start
                Button(
                    onClick = {
                        viewModel.crearRandomBoton()
                        message = "Numero generado"
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
}