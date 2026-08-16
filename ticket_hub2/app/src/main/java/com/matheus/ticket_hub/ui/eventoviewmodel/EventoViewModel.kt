package com.matheus.ticket_hub.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matheus.ticket_hub.data.api.RetrofitClient
import com.matheus.ticket_hub.data.model.EventoRequestDTO
import com.matheus.ticket_hub.domain.model.Evento
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class EventoViewModel : ViewModel() {

    var eventos by mutableStateOf<List<Evento>>(emptyList())
        private set

    var carregando by mutableStateOf(false)
        private set

    init {
        carregarEventos()
    }

    fun carregarEventos() {
        viewModelScope.launch {
            carregando = true
            try {
                eventos = RetrofitClient.apiService.getEventos()
            } catch (e: Exception) {
                Log.e("EventoViewModel", "Erro ao carregar eventos", e)
            } finally {
                carregando = false
            }
        }
    }

    fun criarEvento(
        nome: String,
        capacidade: String,
        preco: String,
        dataHora: String,
        onSucesso: () -> Unit = {},
        onErro: (String) -> Unit = {}
    ) {
        viewModelScope.launch {
            carregando = true
            try {
                val capacidadeInt = capacidade.toIntOrNull() ?: 100
                val precoDouble = preco.replace(",", ".").toDoubleOrNull() ?: 0.0

                val dto = EventoRequestDTO(
                    nome = nome.ifBlank { "Novo Evento" },
                    capacidadeTotal = if (capacidadeInt < 1) 1 else capacidadeInt,
                    preco = precoDouble,
                    // CORREÇÃO: converte a data do formato brasileiro (dd/MM/yyyy HH:mm)
                    // para ISO (yyyy-MM-dd'T'HH:mm:ss), que é o que a API espera.
                    dataHora = converterDataParaIso(dataHora)
                )

                RetrofitClient.apiService.criarEvento(dto)
                carregarEventos()
                // SÓ navega para o sucesso quando a API realmente respondeu OK
                onSucesso()
            } catch (e: Exception) {
                // CORREÇÃO: em caso de falha (rede, servidor offline, erro 4xx/5xx),
                // NÃO simula sucesso. Propaga o erro para a UI exibir a mensagem.
                Log.e("EventoViewModel", "Erro ao criar evento na API: ${e.message}", e)
                onErro(e.message ?: "Erro desconhecido ao criar evento")
            } finally {
                carregando = false
            }
        }
    }
}

// Converte "dd/MM/yyyy HH:mm" (digitado pelo admin) para ISO "yyyy-MM-dd'T'HH:mm:ss".
// Se vier em branco ou não vier no formato BR, usa a data atual como fallback.
private fun converterDataParaIso(dataHora: String): String {
    if (dataHora.isBlank()) {
        return SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
            .format(Date())
    }
    return try {
        val br = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        val dt = br.parse(dataHora)
        if (dt != null) {
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault()).format(dt)
        } else {
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault()).format(Date())
        }
    } catch (e: Exception) {
        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault()).format(Date())
    }
}