package com.matheus.ticket_hub.domain.model

import com.google.gson.annotations.SerializedName

data class Evento(
    val id: Long,

    @SerializedName("nome")
    val titulo: String,

    val categoria: String? = null,

    @SerializedName("data_hora")
    val dataHora: String? = null,
    val local: String? = null,
    val descricao: String? = null,
    val preco: Double,
    val ingressosDisponiveis: Int
)
