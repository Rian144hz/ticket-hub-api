package org.tickethubapi.tickethubapi.domain.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "ingresso")
class Ingresso(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val compradorNome: String = "",

    @Column(nullable = false)
    val compradorCpf: String = "",

    @Column(nullable = false)
    val valorPago: BigDecimal = BigDecimal.ZERO,

    @Column(nullable = false)
    val dataCompra: LocalDateTime = LocalDateTime.now(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evento_id", nullable = false)
    val evento: Evento = Evento()

){
    constructor() : this(null,"","", BigDecimal.ZERO, LocalDateTime.now())
}
