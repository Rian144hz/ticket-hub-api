package org.tickethubapi.tickethubapi.dto

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class CompraIngressoRequestDTO(
    @field:NotNull(message = "O ID do evento é obrigatório")
    val eventoId: Long?,

    @field:NotBlank(message = "O nome do comprador é obrigatório")
    val compradorNome: String?,

    @field:NotBlank(message = "O CPF do comprador é obrigatório")
    val compradorCpf: String?,

    @field:NotNull(message = "A quantidade é obrigatória")
    @field:Min(value = 1, message = "Você deve comprar pelo menos 1 ingresso")
    val quantidade: Int?
)