package org.tickethubapi.tickethubapi.controller

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.tickethubapi.tickethubapi.domain.entities.Evento
import org.tickethubapi.tickethubapi.domain.entities.Ingresso
import org.tickethubapi.tickethubapi.dto.CompraIngressoRequestDTO
import org.tickethubapi.tickethubapi.dto.EventoRequestDTO
import org.tickethubapi.tickethubapi.service.EventoService


@RestController
@RequestMapping("/eventos")
class EventoController(
    private val EventoService: EventoService
) {
    @PostMapping
    fun criarEvento(@Valid @RequestBody dto: EventoRequestDTO): ResponseEntity<Evento> {
        val eventoCriado = EventoService.criarEvento(dto)
        return ResponseEntity.status(HttpStatus.CREATED).body(eventoCriado)
    }
    @GetMapping
    fun listarDisponiveis(): ResponseEntity<List<Evento>> {
        val eventos = EventoService.listarEventos()
        return ResponseEntity.ok(eventos)
    }

    @PostMapping("/comprar")
    fun comprarIngresso(@Valid @RequestBody dto: CompraIngressoRequestDTO): ResponseEntity<List<Ingresso>> {
        val ingressosGerados = EventoService.comprarIngresso(dto)
        return ResponseEntity.status(HttpStatus.CREATED).body(ingressosGerados)
    }
}
