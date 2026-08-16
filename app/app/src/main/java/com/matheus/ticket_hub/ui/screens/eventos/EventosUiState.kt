package com.matheus.ticket_hub.ui.screens.eventos

import com.matheus.ticket_hub.domain.model.Evento

sealed interface EventosUiState {
    object Loading : EventosUiState
    data class Success(val eventos: List<Evento>) : EventosUiState
    data class Error(val mensagem: String) : EventosUiState
}