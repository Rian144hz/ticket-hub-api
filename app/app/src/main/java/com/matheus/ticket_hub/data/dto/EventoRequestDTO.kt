package com.matheus.ticket_hub.data.model

data class EventoRequestDTO(
    val nome: String,
    val capacidadeTotal: Int,
    val preco: Double,
    val dataHora: String? = null
)