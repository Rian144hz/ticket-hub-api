package org.tickethubapi.tickethubapi.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.tickethubapi.tickethubapi.domain.entities.Ingresso

@Repository
interface IngressoRepository : JpaRepository<Ingresso, Long>