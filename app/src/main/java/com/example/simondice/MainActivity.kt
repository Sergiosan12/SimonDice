package com.example.simondice

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.simondice.ui.theme.SimonDiceTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimonDiceTheme {
                Surface(
                    color = Color.Black,
                    modifier = Modifier.fillMaxSize()
                ) {
                    BotonesColores()
                }
            }
        }
    }

    enum class ColorButton(val color: Color, val label: String) {
        VERDE(Color.Green, "Verde"),
        ROJO(Color.Red, "Rojo"),
        AMARILLO(Color.Yellow, "Amarillo"),
        AZUL(Color.Blue, "Azul")
    }

    data class ButtonData(val colorButton: ColorButton, val shape: RoundedCornerShape)

    @Preview(showBackground = true)
    @Composable
    fun BotonesColores() {
        var color by remember { mutableStateOf("") }
        var rounds by remember { mutableStateOf(0) }

        val buttons = listOf(
            ButtonData(ColorButton.VERDE, RoundedCornerShape(topStart = 180.dp)),
            ButtonData(ColorButton.ROJO, RoundedCornerShape(topEnd = 180.dp)),
            ButtonData(ColorButton.AMARILLO, RoundedCornerShape(bottomStart = 180.dp)),
            ButtonData(ColorButton.AZUL, RoundedCornerShape(bottomEnd = 180.dp))
        )

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.background(Color.Black)
        ) {
            OutlinedTextField(
                value = color,
                onValueChange = { color = it },
                label = { Text("Color", color = Color.White) },
                modifier = Modifier.padding(8.dp),
                textStyle = TextStyle(color = Color.White)
            )
            OutlinedTextField(
                value = rounds.toString(),
                onValueChange = {},
                label = { Text("Rondas", color = Color.White) },
                readOnly = true,
                modifier = Modifier.padding(8.dp),
                textStyle = TextStyle(color = Color.White)
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
                                    rounds++
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
            }
        }
    }
}



