package com.matheus.ticket_hub.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HeaderBusca(
    textoBusca: String,
    onBuscaChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF6C38FF))
            .padding(20.dp)
    ) {
        Text("TicketHub", color = Color.White.copy(alpha = 0.8f), fontSize = 14.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Text("Eventos disponíveis", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Text("Encontre o próximo evento e garanta seu ingresso", color = Color.White.copy(alpha = 0.9f), fontSize = 12.sp)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = textoBusca,
            onValueChange = onBuscaChange,
            placeholder = { Text("Buscar shows, festas, teatro...", color = Color.White.copy(alpha = 0.6f)) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = Color.White) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White.copy(alpha = 0.2f),
                unfocusedContainerColor = Color.White.copy(alpha = 0.2f),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            )
        )
    }
}