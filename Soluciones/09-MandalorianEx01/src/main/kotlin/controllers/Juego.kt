package controllers

import models.Personaje

const val NUM_MAX_VICTORIAS = 3
const val NUM_VECES_POR_RONDA = 3

/**
 * Clase que representa un juego
 * @property personaje1 Personaje 1
 * @property personaje2 Personaje 2
 */
class Juego(
    val personaje1: Personaje,
    val personaje2: Personaje
) {

    /**
     * Método que simula el juego
     */
    fun jugar() {
        println("Entrenando la fuerza")
        var ronda = 1

        do {
            println("Ronda: $ronda")

            // Juegan Mando y Grogu
            val numAciertosMando = personaje1.lanzarBola(NUM_VECES_POR_RONDA)
            println("$personaje1 ha acertado $numAciertosMando veces")
            val numAciertosGrogu = personaje2.lanzarBola(NUM_VECES_POR_RONDA)
            println("$personaje2 ha acertado $numAciertosGrogu veces")

            if (numAciertosMando > numAciertosGrogu) {
                personaje1.numVictorias++
                println("Gana Mando la ronda con $numAciertosMando aciertos. Grogu ha tenido $numAciertosGrogu aciertos")
            } else if (numAciertosGrogu > numAciertosMando) {
                personaje2.numVictorias++
                println("Gana Grogu la ronda con $numAciertosGrogu aciertos. Mando ha tenido $numAciertosMando aciertos")
            } else {
                println("Empate")
            }

            ronda++
        } while (personaje1.numVictorias < NUM_MAX_VICTORIAS && personaje2.numVictorias < NUM_MAX_VICTORIAS)
    }

    /**
     * Método que muestra el resultado del juego
     */
    fun informe() {
        if (personaje1.numVictorias > personaje2.numVictorias) {
            println("Gana Mando la partida con ${personaje1.numVictorias} victorias")
            println("Grogu ha tenido ${personaje2.numVictorias} victorias")
        } else {
            println("Gana Grogu la partida con ${personaje2.numVictorias} victorias")
            println("Mando ha tenido ${personaje1.numVictorias} victorias")
        }
    }
}