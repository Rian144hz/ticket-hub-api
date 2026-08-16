package com.matheus.ticket_hub.ui.screens.detalhes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.matheus.ticket_hub.domain.model.Evento

@Composable
fun DetalheEventoScreen(
    evento: Evento,
    onVoltarClick: () -> Unit = {},
    onIrParaCheckoutClick: (quantidade: Int) -> Unit = {}
) {
    var quantidade by remember { mutableIntStateOf(1) }
    val total = evento.preco * quantidade

    Scaffold(
        bottomBar = {
            Surface(
                shadowElevation = 8.dp,
                color = Color.White
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("Total", fontSize = 12.sp, color = Color.Gray)
                        Text(
                            "R$ ${String.format("%.2f", total)}",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF6C38FF)
                        )
                    }

                    Button(
                        onClick = { onIrParaCheckoutClick(quantidade) },
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6C38FF)),
                        contentPadding = PaddingValues(horizontal = 32.dp, vertical = 14.dp)
                    ) {
                        Text("Comprar ingresso", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    }
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .background(Color.White)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
                    .background(Color(0xFF8B5CF6))
            ) {
                IconButton(
                    onClick = onVoltarClick,
                    modifier = Modifier
                        .padding(top = 16.dp, start = 16.dp)
                        .background(Color.White.copy(alpha = 0.3f), CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Voltar",
                        tint = Color.White
                    )
                }
            }

            Column(modifier = Modifier.padding(20.dp)) {
                Surface(
                    color = Color(0xFFF3E8FF),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = (evento.categoria ?: "").ifBlank { "GERAL" },
                        color = Color(0xFF6C38FF),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = (evento.titulo ?: "").ifBlank { "Sem título" },
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E1E1E)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = (evento.dataHora ?: "").ifBlank { "Data a definir" }, color = Color.Gray, fontSize = 14.sp)
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = Color(0xFFFF5722),
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = (evento.local ?: "").ifBlank { "Local a definir" }, color = Color.Gray, fontSize = 14.sp)
                }

                Spacer(modifier = Modifier.height(20.dp))
                HorizontalDivider(color = Color(0xFFEEEEEE))
                Spacer(modifier = Modifier.height(20.dp))

                Text("Sobre o evento", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = (evento.descricao ?: "").ifBlank { "Sem descrição." },
                    color = Color.Gray,
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                )

                Spacer(modifier = Modifier.height(20.dp))
                HorizontalDivider(color = Color(0xFFEEEEEE))
                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("Quantidade", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        Text("Ingressos por pessoa", fontSize = 12.sp, color = Color.Gray)
                    }

                    Surface(
                        shape = RoundedCornerShape(24.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE0E0E0)),
                        color = Color.White
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            IconButton(
                                onClick = { if (quantidade > 1) quantidade-- }
                            ) {
                                Icon(Icons.Default.Remove, contentDescription = "Diminuir", tint = Color(0xFF6C38FF))
                            }

                            Text(
                                text = "$quantidade",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 8.dp)
                            )

                            IconButton(
                                onClick = { if (quantidade < (evento.ingressosDisponiveis ?: 0)) quantidade++ }
                            ) {
                                Icon(Icons.Default.Add, contentDescription = "Aumentar", tint = Color(0xFF6C38FF))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetalheEventoScreenPreview() {
    val eventoMock = Evento(
        id = 1,
        titulo = "Festival Nova Onda",
        categoria = "MÚSICA · FESTIVAL",
        dataHora = "Sábado, 12 de Setembro · 20:00",
        local = "Arena Central — São Paulo, SP",
        descricao = "Uma noite única com as melhores atrações da cena independente.",
        preco = 120.0,
        ingressosDisponiveis = 48
    )
    DetalheEventoScreen(evento = eventoMock)
}