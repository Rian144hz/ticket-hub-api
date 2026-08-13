package org.tickethubapi.tickethubapi.dto

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal
import java.time.LocalDateTime

data class EventoRequestDTO(
    @field:NotBlank(message = "O nome do evento é obrigatório")
    val nome: String?,

    @field:NotNull(message = "A capacidade total é obrigatória")
    @field:Min(value = 1, message = "A capacidade deve ser no mínimo 1")
    val capacidadeTotal: Int?,

    @field:NotNull(message = "O preço é obrigatório")
    val preco: BigDecimal?,

    val dataHora: LocalDateTime?
)
