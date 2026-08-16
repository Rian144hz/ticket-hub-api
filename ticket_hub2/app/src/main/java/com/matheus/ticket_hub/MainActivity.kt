package com.matheus.ticket_hub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.matheus.ticket_hub.ui.navigation.AppNavigation
import com.matheus.ticket_hub.ui.theme.Ticket_hubTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Ticket_hubTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Chama a navegação sem passar a lista estática mockEventos
                    AppNavigation()
                }
            }
        }
    }
}