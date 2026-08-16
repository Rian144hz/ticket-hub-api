package com.matheus.ticket_hub.data.api

import com.matheus.ticket_hub.data.model.EventoRequestDTO
import com.matheus.ticket_hub.domain.model.Evento
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("eventos")
    suspend fun getEventos(): List<Evento>

    @POST("eventos")
    suspend fun criarEvento(@Body request: EventoRequestDTO): Evento
}