package com.matheus.ticket_hub.ui.screens.checkout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.matheus.ticket_hub.domain.model.Evento

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen(
    evento: Evento,
    quantidade: Int = 2,
    onVoltarClick: () -> Unit,
    onConfirmarCompraClick: (nome: String, cpf: String) -> Unit
) {
    var nomeCompleto by remember { mutableStateOf("") }
    var cpf by remember { mutableStateOf("") }

    val valorTotal = evento.preco * quantidade

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Checkout",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E1E1E)
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onVoltarClick,
                        modifier = Modifier
                            .padding(8.dp)
                            .background(Color(0xFFF2EBFD), CircleShape)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Voltar",
                            tint = Color(0xFF6C38FF)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        bottomBar = {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = Color.White,
                shadowElevation = 8.dp
            ) {
                Box(modifier = Modifier.padding(16.dp)) {
                    Button(
                        onClick = { onConfirmarCompraClick(nomeCompleto, cpf) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6C38FF))
                    ) {
                        Text(
                            text = "Confirmar compra",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFF8F9FA))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Card: Resumo do pedido
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Resumo do pedido",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E1E1E)
                    )

                    Text(
                        text = (evento.titulo ?: "").ifBlank { "Sem título" },
                        fontSize = 14.sp,
                        color = Color(0xFF757575)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Data", fontSize = 14.sp, color = Color(0xFF757575))
                        Text(
                            text = (evento.dataHora ?: "").ifBlank { "Data a definir" },
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF1E1E1E)
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Quantidade", fontSize = 14.sp, color = Color(0xFF757575))
                        Text(
                            text = "$quantidade ingressos",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF1E1E1E)
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Valor unitário", fontSize = 14.sp, color = Color(0xFF757575))
                        Text(
                            text = "R$ ${String.format("%.2f", evento.preco)}",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF1E1E1E)
                        )
                    }

                    HorizontalDivider(color = Color(0xFFEEEEEE), thickness = 1.dp)

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Total",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1E1E1E)
                        )
                        Text(
                            text = "R$ ${String.format("%.2f", valorTotal)}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF6C38FF)
                        )
                    }
                }
            }

            // Seção: Dados do comprador
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Dados do comprador",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E1E1E)
                )

                Column {
                    Text(
                        text = "Nome completo",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF1E1E1E),
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                    OutlinedTextField(
                        value = nomeCompleto,
                        onValueChange = { nomeCompleto = it },
                        placeholder = { Text("Digite seu nome completo", color = Color.Gray) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White,
                            unfocusedBorderColor = Color(0xFFE0E0E0),
                            focusedBorderColor = Color(0xFF6C38FF)
                        ),
                        singleLine = true
                    )
                }

                Column {
                    Text(
                        text = "CPF",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF1E1E1E),
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                    OutlinedTextField(
                        value = cpf,
                        onValueChange = { cpf = it },
                        placeholder = { Text("123.456.789-00", color = Color.Gray) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White,
                            unfocusedBorderColor = Color(0xFFE0E0E0),
                            focusedBorderColor = Color(0xFF6C38FF)
                        ),
                        singleLine = true
                    )
                    Text(
                        text = "Formato: 000.000.000-00",
                        fontSize = 12.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(top = 4.dp, start = 4.dp)
                    )
                }
            }
        }
    }
}