// MainActivity.kt
package com.example.simondice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
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
                    IU(ModelView())
                }
            }
        }
    }
}