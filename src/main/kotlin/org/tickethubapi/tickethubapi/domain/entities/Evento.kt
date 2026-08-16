package org.tickethubapi.tickethubapi.domain.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "eventos")
class Evento (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var nome: String = "",

    @Column(nullable = false)
    var capacidadeTotal: Int = 0,

    @Column(name = "ingressos_disp", nullable = false)
    var ingressosDisponiveis: Int = 0,

    @Column(nullable = false)
    var preco: BigDecimal = BigDecimal.ZERO,

    @Column(name = "data", nullable = false)
    var data_hora: LocalDateTime = LocalDateTime.now(),

    ){
    constructor() : this(null, "",0,0, BigDecimal.ZERO, LocalDateTime.now())
}