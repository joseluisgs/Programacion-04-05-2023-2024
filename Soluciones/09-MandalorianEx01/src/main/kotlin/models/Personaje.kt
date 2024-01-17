package models

/**
 * Clase que representa un personaje
 * @property nombre Nombre del personaje
 * @property probabilidad Probabilidad de acierto del personaje
 * @property numVictorias Número de victorias del personaje
 */
data class Personaje(
    val nombre: String,
    val probabilidad: Int
) {
    var numVictorias: Int = 0

    override fun toString(): String {
        return "Personaje(nombre='$nombre', probabilidad=$probabilidad, numVictorias=$numVictorias)"
    }

    /**
     * Método que simula el lanzamiento de una bola
     * @param numVeces Número de veces que se lanza la bola
     * @return Número de aciertos
     */
    fun lanzarBola(numVeces: Int): Int {
        var numAciertos = 0
        for (i in 1..numVeces) {
            println("Lanzamiento: $i - $nombre lanza la bola")
            val resultado = (1..100).random()
            if (resultado <= probabilidad) {
                println("$nombre ha acertado")
                numAciertos++
            } else {
                println("$nombre ha fallado")
            }
        }
        return numAciertos
    }
}