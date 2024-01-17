package dev.joseluisgs.dev.joseluisgs.controllers

import dev.joseluisgs.dev.joseluisgs.models.ParametrosVuelo
import org.lighthousegames.logging.logging

private val log = logging()
private const val TIEMPO_MAX = 30000
private const val TASA_ERROR = 15


class PilotoAutomatico(
    private val alturaCrucero: Int,
    private val velocidadCrucero: Int,
    private val porcentajeVariacionAltura: Int,
    private val porcentajeVariacionVelocidad: Int
) {

    private val parametros = ParametrosVuelo(
        altura = alturaCrucero + (alturaCrucero * arrayOf(
            -3,
            -2,
            -1,
            1,
            2,
            3
        ).random() * porcentajeVariacionAltura / 100),
        velocidad = velocidadCrucero + (velocidadCrucero * arrayOf(
            -3,
            -2,
            -1,
            1,
            2,
            3
        ).random() * porcentajeVariacionVelocidad / 100)
    )

    fun iniciar() {
        log.debug { "Iniciando piloto automático" }
        var tiempo = 0
        var error = false
        do {
            println("Altura: ${parametros.altura}, Velocidad: ${parametros.velocidad}, Error: $error")
            // Comprobamos si hay que variar la altura
            comprobarAltura()
            // Comparar valocidad
            comprobarVelocidad()

            if (tiempo % 5000 == 0) {
                error = comprobarError()
            }
            tiempo += 1000
            Thread.sleep(1000)
        } while (tiempo < TIEMPO_MAX && !error)
        if (error) {
            println("Error detectado, abortando misión")
        } else {
            println("Misión completada")
        }
        println("Altura final: ${parametros.altura}, Velocidad final: ${parametros.velocidad}, Error: $error")
    }

    private fun comprobarVelocidad() {
        log.debug { "Comprobando velocidad" }
        if (parametros.velocidad > velocidadCrucero) {
            log.debug { "Bajando velocidad" }
            parametros.velocidad -= (parametros.velocidad * porcentajeVariacionVelocidad / 100)
        } else if (parametros.velocidad < velocidadCrucero) {
            log.debug { "Subiendo velocidad" }
            parametros.velocidad += (parametros.velocidad * porcentajeVariacionVelocidad / 100)
        }
    }

    private fun comprobarAltura() {
        log.debug { "Comprobando altura" }
        if (parametros.altura > alturaCrucero) {
            log.debug { "Bajando altura" }
            parametros.altura -= (parametros.altura * porcentajeVariacionAltura / 100)
        } else if (parametros.altura < alturaCrucero) {
            log.debug { "Subiendo altura" }
            parametros.altura += (parametros.altura * porcentajeVariacionAltura / 100)
        }
    }

    private fun comprobarError(): Boolean {
        log.debug { "Comprobando error" }
        return (0..100).random() < TASA_ERROR
    }

}