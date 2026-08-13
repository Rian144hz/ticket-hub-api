package org.tickethubapi.tickethubapi.service

import org.springframework.stereotype.Service
import org.tickethubapi.tickethubapi.domain.entities.Evento
import org.tickethubapi.tickethubapi.dto.EventoRequestDTO
import org.tickethubapi.tickethubapi.repository.EventoRepository
import org.tickethubapi.tickethubapi.repository.IngressoRepository
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime


@Service
class EventoService (
    private val eventoRepository: EventoRepository,
    private val  ingressoRepository: IngressoRepository
){
    fun criarEvento(dto: EventoRequestDTO): Evento {
        val capacidade = dto.capacidadeTotal ?: throw IllegalArgumentException("A capacidade total precisa ser preenchida.")

        val novoEvento  = Evento(
            nome = dto.nome ?: throw IllegalArgumentException("O nome do evento precisa ser preenchido."),
            capacidadeTotal = capacidade,
            ingressosDisponiveis = capacidade,
            preco = dto.preco ?: BigDecimal.ZERO,
            data_hora = dto.dataHora?: LocalDateTime.now(),
        )
        return eventoRepository.save(novoEvento)
    }
}