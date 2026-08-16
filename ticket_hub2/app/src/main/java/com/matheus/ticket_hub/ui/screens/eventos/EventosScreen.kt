package com.matheus.ticket_hub.ui.screens.eventos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.matheus.ticket_hub.domain.model.Evento
import com.matheus.ticket_hub.ui.components.CardEvento
import com.matheus.ticket_hub.ui.components.HeaderBusca

@Composable
fun EventosScreen(
    eventos: List<Evento>,
    onEventoClick: (Long) -> Unit
) {
    // Cores dos banners, na ordem do Figma: roxo, laranja, teal
    val bannerCores = listOf(
        Color(0xFF7C3AED), // roxo  - Festival Nova Onda
        Color(0xFFF97316), // laranja - Stand-up: Rir é Preciso
        Color(0xFF14B8A6)  // teal  - card parcial
    )

    var textoBusca by remember { mutableStateOf("") }

    val eventosFiltrados = if (textoBusca.isBlank()) {
        eventos
    } else {
        eventos.filter {
            (it.titulo ?: "").contains(textoBusca, ignoreCase = true)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
    ) {
        // Cabeçalho roxo com busca (igual ao Frame 1 do Figma)
        HeaderBusca(
            textoBusca = textoBusca,
            onBuscaChange = { textoBusca = it }
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            itemsIndexed(
                items = eventosFiltrados,
                key = { _, evento -> evento.id }
            ) { index, evento ->
                CardEvento(
                    evento = evento,
                    corBanner = bannerCores[index % bannerCores.size],
                    onComprarClick = { onEventoClick(evento.id) }
                )
            }
        }
    }
}
