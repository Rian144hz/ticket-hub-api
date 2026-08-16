package com.matheus.ticket_hub.ui.screens.confirmacao

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.matheus.ticket_hub.domain.model.Evento

@Composable
fun ConfirmacaoScreen(
    evento: Evento,
    nomeComprador: String,
    cpfComprador: String = "",
    quantidade: Int = 2,
    codigoIngresso: String = "TH-8F3K-2091",
    onVerMeusIngressosClick: () -> Unit = {},
    onVoltarParaEventosClick: () -> Unit = {}
) {
    Scaffold(
        containerColor = Color(0xFFF8F9FA)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Ícone de Sucesso
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .background(Color(0xFFE8F5E9), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Sucesso",
                    tint = Color(0xFF2E7D32),
                    modifier = Modifier.size(36.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Título
            Text(
                text = "Compra confirmada!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E1E1E),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Subtítulo
            Text(
                text = "Seu ingresso foi enviado para o seu e-mail\ne já está disponível abaixo.",
                fontSize = 14.sp,
                color = Color(0xFF757575),
                textAlign = TextAlign.Center,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(28.dp))

            // Card com o resumo do ingresso
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Text(
                        text = (evento.titulo ?: "").ifBlank { "Sem título" },
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E1E1E)
                    )

                    Text(
                        text = "${evento.dataHora ?: ""} · ${(evento.local ?: "").ifBlank { "Local a definir" }}",
                        fontSize = 13.sp,
                        color = Color(0xFF757575)
                    )

                    HorizontalDivider(color = Color(0xFFEEEEEE), thickness = 1.dp)

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Comprador", fontSize = 14.sp, color = Color(0xFF757575))
                        Text(
                            text = nomeComprador.ifBlank { "—" },
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1E1E1E)
                        )
                    }

                    if (cpfComprador.isNotBlank()) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "CPF", fontSize = 14.sp, color = Color(0xFF757575))
                            Text(
                                text = cpfComprador,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1E1E1E)
                            )
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Ingressos", fontSize = 14.sp, color = Color(0xFF757575))
                        Text(
                            text = "$quantidade unidades",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1E1E1E)
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    // Caixa do Código do Ingresso
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        color = Color(0xFFF3E8FF)
                    ) {
                        Column(
                            modifier = Modifier.padding(vertical = 14.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "CÓDIGO DO INGRESSO",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF6C38FF),
                                letterSpacing = 0.5.sp
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = codigoIngresso,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF6C38FF),
                                letterSpacing = 1.sp
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Botão "Ver meus ingressos"
            Button(
                onClick = onVerMeusIngressosClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6C38FF))
            ) {
                Text(
                    text = "Ver meus ingressos",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Botão "Voltar para eventos"
            OutlinedButton(
                onClick = onVoltarParaEventosClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(12.dp)),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF1E1E1E))
            ) {
                Text(
                    text = "Voltar para eventos",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E1E1E)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ConfirmacaoScreenPreview() {
    val eventoMock = Evento(
        id = 1L,
        titulo = "Festival Nova Onda",
        categoria = "MÚSICA · FESTIVAL",
        dataHora = "Sáb, 12 Set · 20:00",
        local = "Arena Central — São Paulo, SP",
        descricao = "Descrição de teste",
        preco = 120.0,
        ingressosDisponiveis = 10
    )
    ConfirmacaoScreen(evento = eventoMock, nomeComprador = "Ana Carolina Souza")
}