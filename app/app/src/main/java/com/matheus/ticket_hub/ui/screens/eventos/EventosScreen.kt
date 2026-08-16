package com.matheus.ticket_hub.ui.screens.eventos

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.matheus.ticket_hub.domain.model.Evento

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventosScreen(
    eventos: List<Evento>,
    onEventoClick: (Long) -> Unit,
    onAdminClick: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "TicketHub",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E1E1E)
                    )
                },
                actions = {
                    Surface(
                        onClick = onAdminClick,
                        color = Color(0xFFFF5722),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.padding(end = 12.dp)
                    ) {
                        Text(
                            text = "Admin",
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        containerColor = Color(0xFFF8F9FA)
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                items = eventos,
                key = { evento -> evento.id ?: 0L }
            ) { evento ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            evento.id?.let { onEventoClick(it) }
                        },
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        // Categoria com fallback
                        Text(
                            text = (evento.categoria ?: "").ifBlank { "GERAL" },
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF6C38FF)
                        )

                        // Título do evento com fallback
                        Text(
                            text = (evento.titulo ?: "").ifBlank { "Sem título" },
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1E1E1E)
                        )

                        // Data/Hora e Local com fallback
                        val dataTexto = (evento.dataHora ?: "").ifBlank { "Data pendente" }
                        val localTexto = (evento.local ?: "").ifBlank { "Local a definir" }
                        Text(
                            text = "$dataTexto • $localTexto",
                            fontSize = 13.sp,
                            color = Color(0xFF757575)
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Preço formatado com valor padrão
                            val precoSeguro = evento.preco ?: 0.0
                            Text(
                                text = "R$ ${"%.2f".format(precoSeguro)}",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1E1E1E)
                            )

                            // Tratamento de vagas usando apenas ingressosDisponiveis
                            val vagasSeguras = evento.ingressosDisponiveis ?: 0
                            Text(
                                text = "$vagasSeguras vagas",
                                fontSize = 13.sp,
                                color = Color(0xFF2E7D32),
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }
        }
    }
}