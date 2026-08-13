package org.tickethubapi.tickethubapi.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.tickethubapi.tickethubapi.domain.entities.Evento

@Repository
interface EventoRepository : JpaRepository<Evento, Long>

