package dev.joseluisgs

import dev.joseluisgs.dev.joseluisgs.controllers.PilotoAutomatico

const val PORCENTAJE_VARIACION_ALTURA = 10
const val PORCENTAJE_VARIACION_VELOCIDAD = 5

fun main() {
    println("Hola piloto automático")
    val altura: Int = pedirAltura()
    val velocidad: Int = pedirVelocidad()
    val piloto = PilotoAutomatico(altura, velocidad, PORCENTAJE_VARIACION_ALTURA, PORCENTAJE_VARIACION_VELOCIDAD)
    piloto.iniciar()
}

fun pedirVelocidad(): Int {
    var velocidad = 0
    var isOk = false
    do {
        println("Introduzca la velocidad de vuelo: [100 - 900]")
        velocidad = readln().toIntOrNull() ?: 0
        if (velocidad in 100..900) {
            isOk = true
        } else {
            println("Velocidad incorrecta, debe estar entre 100 y 900")
        }
    } while (!isOk)

    return velocidad
}

fun pedirAltura(): Int {
    var altura = 0
    var isOk = false
    do {
        println("Introduzca la altura de vuelo: [1000 - 2000]")
        altura = readln().toIntOrNull() ?: 0
        if (altura in 1000..2000) {
            isOk = true
        } else {
            println("Altura incorrecta, debe estar entre 1000 y 2000")
        }
    } while (!isOk)

    return altura
}
