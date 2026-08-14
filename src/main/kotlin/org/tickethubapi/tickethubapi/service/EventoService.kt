package org.tickethubapi.tickethubapi.service

import org.springframework.transaction.annotation.Transactional
import org.springframework.stereotype.Service
import org.tickethubapi.tickethubapi.domain.entities.Evento
import org.tickethubapi.tickethubapi.domain.entities.Ingresso
import org.tickethubapi.tickethubapi.dto.CompraIngressoRequestDTO
import org.tickethubapi.tickethubapi.dto.EventoRequestDTO
import org.tickethubapi.tickethubapi.exceptions.EventoEsgotadoException
import org.tickethubapi.tickethubapi.repository.EventoRepository
import org.tickethubapi.tickethubapi.repository.IngressoRepository
import java.math.BigDecimal
import org.tickethubapi.tickethubapi.exceptions.EventoNaoEncontradoException
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
    fun listarEventos(): List<Evento> {
        return eventoRepository.findAll().filter { it.ingressosDisponiveis > 0 }
    }
    @Transactional
    fun comprarIngresso(dto: CompraIngressoRequestDTO): List<Ingresso> {
        val eventoId = dto.eventoId ?: throw IllegalArgumentException("O ID do evento é obrigatório.")
        val quantidade = dto.quantidade ?: 1

        val evento = eventoRepository.findById(eventoId).orElseThrow {
            EventoNaoEncontradoException("Evento com ID $eventoId não foi encontrado.")
        }

        if (evento.ingressosDisponiveis < quantidade) {
            throw EventoEsgotadoException("Quantidade solicitada ($quantidade) excede os ingressos disponíveis (${evento.ingressosDisponiveis}).")
        }

        evento.ingressosDisponiveis -= quantidade
        eventoRepository.save(evento)

        val ingressos = (1..quantidade).map {
            Ingresso(
                compradorNome = dto.compradorNome ?: "",
                compradorCpf = dto.compradorCpf ?: "",
                valorPago = evento.preco,
                dataCompra = LocalDateTime.now(),
                evento = evento
            )
        }

        return ingressoRepository.saveAll(ingressos)
    }
}
