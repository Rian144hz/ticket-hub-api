package com.matheus.ticket_hub.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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

@Composable
fun CardEvento(
    evento: Evento,
    corBanner: Color,
    onComprarClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onComprarClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .background(corBanner)
            )

            Column(modifier = Modifier.padding(16.dp)) {
                Text(evento.titulo ?: "Sem título", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E1E1E))
                Spacer(modifier = Modifier.height(4.dp))
                Text(evento.dataHora ?: "Data pendente", fontSize = 14.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "R$ ${String.format("%.2f", evento.preco)}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF6C38FF)
                    )

                    Surface(
                        color = Color(0xFFFFECE5),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            "${evento.ingressosDisponiveis} disponíveis",
                            color = Color(0xFFFF5722),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = onComprarClick, // Clique no botão comprar
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6C38FF))
                ) {
                    Text("Comprar", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}