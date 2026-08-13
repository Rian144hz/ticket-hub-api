package org.tickethubapi.tickethubapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TicketHubApiApplication

fun main(args: Array<String>) {
    runApplication<TicketHubApiApplication>(*args)
}
