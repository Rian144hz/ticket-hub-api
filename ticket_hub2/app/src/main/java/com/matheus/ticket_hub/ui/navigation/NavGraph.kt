package com.matheus.ticket_hub.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.matheus.ticket_hub.ui.screens.admin.AdminLoginScreen
import com.matheus.ticket_hub.ui.screens.admin.CriarEventoScreen
import com.matheus.ticket_hub.ui.screens.checkout.CheckoutScreen
import com.matheus.ticket_hub.ui.screens.confirmacao.ConfirmacaoScreen
import com.matheus.ticket_hub.ui.screens.detalhes.DetalheEventoScreen
import com.matheus.ticket_hub.ui.screens.eventos.EventosScreen
import com.matheus.ticket_hub.ui.viewmodel.EventoViewModel

@Composable
fun AppNavigation(viewModel: EventoViewModel = viewModel()) {
    val navController = rememberNavController()
    val listaEventos = viewModel.eventos

    NavHost(navController = navController, startDestination = "eventos") {

        // Tela de Lista de Eventos
        composable("eventos") {
            EventosScreen(
            eventos = listaEventos,
            onEventoClick = { id ->
                navController.navigate("detalhe/$id")
            }
            )
        }

        // Tela de Detalhe do Evento
        composable("detalhe/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toLongOrNull() ?: -1L
            val evento = listaEventos.firstOrNull { it.id == id }
            if (evento != null) {
                DetalheEventoScreen(
                    evento = evento,
                    onVoltarClick = { navController.popBackStack() },
                    onIrParaCheckoutClick = { quantidade ->
                        navController.navigate("checkout/$id/$quantidade")
                    }
                )
            } else {
                LaunchedEffect(Unit) { navController.popBackStack() }
            }
        }

        // Tela de Checkout
        composable("checkout/{id}/{qtd}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toLongOrNull() ?: -1L
            val qtd = backStackEntry.arguments?.getString("qtd")?.toIntOrNull() ?: 1
            val evento = listaEventos.firstOrNull { it.id == id }
            if (evento != null) {
                CheckoutScreen(
                    evento = evento,
                    quantidade = qtd,
                    onVoltarClick = { navController.popBackStack() },
                    onConfirmarCompraClick = { nome, cpf ->
                        val nomeEnc = java.net.URLEncoder.encode(nome, "UTF-8")
                        val cpfEnc = java.net.URLEncoder.encode(cpf, "UTF-8")
                        navController.navigate("confirmacao/$id/$qtd/$nomeEnc/$cpfEnc") {
                            popUpTo("detalhe/$id") { inclusive = true }
                        }
                    }
                )
            } else {
                LaunchedEffect(Unit) { navController.popBackStack() }
            }
        }

        // Tela de Confirmação
        composable("confirmacao/{id}/{qtd}/{nome}/{cpf}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toLongOrNull() ?: -1L
            val qtd = backStackEntry.arguments?.getString("qtd")?.toIntOrNull() ?: 1
            val nome = backStackEntry.arguments?.getString("nome")?.let {
                java.net.URLDecoder.decode(it, "UTF-8")
            } ?: ""
            val cpf = backStackEntry.arguments?.getString("cpf")?.let {
                java.net.URLDecoder.decode(it, "UTF-8")
            } ?: ""
            val evento = listaEventos.firstOrNull { it.id == id }
            if (evento != null) {
                ConfirmacaoScreen(
                    evento = evento,
                    nomeComprador = nome,
                    cpfComprador = cpf,
                    quantidade = qtd,
                    onVerMeusIngressosClick = {
                        navController.navigate("eventos") {
                            popUpTo("eventos") { inclusive = true }
                        }
                    },
                    onVoltarParaEventosClick = {
                        navController.navigate("eventos") {
                            popUpTo("eventos") { inclusive = true }
                        }
                    }
                )
            } else {
                LaunchedEffect(Unit) { navController.popBackStack() }
            }
        }

        // Login do Admin
        composable("admin_login") {
            AdminLoginScreen(
                onLoginSucesso = {
                    navController.navigate("admin_criar_evento") {
                        popUpTo("admin_login") { inclusive = true }
                    }
                }
            )
        }

        // Painel do Admin
        composable("admin_criar_evento") {
            CriarEventoScreen(
                eventosRecentes = listaEventos,
                onCriarEventoClick = { nome, capacidade, preco, dataHora, onErro ->
                    viewModel.criarEvento(
                        nome = nome,
                        capacidade = capacidade,
                        preco = preco,
                        dataHora = dataHora,
                        onSucesso = {
                            navController.navigate("eventos") {
                                popUpTo("admin_criar_evento") { inclusive = true }
                            }
                        },
                        onErro = onErro
                    )
                }
            )
        }
    }
}
