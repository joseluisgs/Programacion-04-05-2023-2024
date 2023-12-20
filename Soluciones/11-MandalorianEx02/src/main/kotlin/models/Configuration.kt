package models

import kotlin.system.exitProcess

/**
 * Clase que representa la configuración del puerto
 * @property config Array<String> Array con la configuración del puerto
 */
class Configuration(config: Array<String>) {
    var darsenas: Int = 0
    var puertas: Int = 0

    init {
        if (config.size != 2) {
            println("No está indicando el numero de dársenas y puertas")
            println("Ejemplo: java -jar puerto_nevarro.jar darsenas:<numero de dársenas> puertas<numero de puertas>")
            exitProcess(0)
        } else {
            darsenas = config[0].split(":")[1].toIntOrNull() ?: -1
            puertas = config[1].split(":")[1].toIntOrNull() ?: -1
            if (darsenas !in (3..5)) {
                println("El numero de dársenas debe estar entre 3 y 5")
            }
            if (puertas !in (5..7)) {
                println("El numero de puertas debe estar entre 3 y 5")
            }
        }
    }
}