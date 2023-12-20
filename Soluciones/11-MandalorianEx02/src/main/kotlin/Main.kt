import models.Configuration
import models.Puerto

fun main(args: Array<String>) {
    val configuration = Configuration(args)

    val puerto = Puerto("Nevarro", configuration.darsenas, configuration.puertas)

    println("Bienvenido al puerto de ${puerto.nombre}")

    var opcion: Int

    do {
        println("1. Estado del puerto")
        println("2. Atracar nave")
        println("3. Buscar nave")
        println("4. Despegar nave")
        println("5. Listado de naves")
        println("6. Salir")
        print("Elige una opción: ")

        opcion = readln().toIntOrNull() ?: 0

        when (opcion) {
            1 -> imprimirPuerto(puerto)
            2 -> atracarNave(puerto)
            3 -> buscarNave(puerto)
            4 -> despegarNave(puerto)
            5 -> imprimirListadoNaves(puerto)
            6 -> despedida()
            else -> println("Opción incorrecta")
        }

    } while (opcion in 1..5)
}

/**
 * Imprime un mensaje de despedida
 */
private fun despedida() {
    println("Hasta pronto")
}

/**
 * Listado de naves del puerto
 * @param puerto Puerto
 */
private fun imprimirListadoNaves(puerto: Puerto) {
    println(puerto.listadoNaves())
}

/**
 * Imprime el estado del puerto
 * @param puerto Puerto
 */
private fun imprimirPuerto(puerto: Puerto) {
    println(puerto)
}

/**
 * Despega una nave del puerto
 * @param puerto Puerto
 */
fun despegarNave(puerto: Puerto) {
    val numeroControl = getNumeroControl()
    val res = puerto.despegarNave(numeroControl)
    println(res)

}

/**
 * Busca una nave en el puerto
 * @param puerto Puerto
 */
fun buscarNave(puerto: Puerto) {
    val numeroControl = getNumeroControl()
    val res = puerto.buscarNave(numeroControl)
    println(res)

}

/**
 * Atraca una nave en el puerto
 * @param puerto Puerto
 */

fun atracarNave(puerto: Puerto) {
    val numeroControl = getNumeroControl()
    val res = puerto.atracarNave(numeroControl)
    println(res)
}

/**
 * Solicita el número de control de la nave
 * @return String Número de control de la nave en formato XXX000X
 */
private fun getNumeroControl(): String {
    val numControlRegex = Regex("[A-Z]{3}[0-9]{3}[A-Z]")
    var numeroControl: String
    var inputOk: Boolean = false
    do {
        println("Introduce el número de control: ")
        numeroControl = readln()
        inputOk = numControlRegex.matches(numeroControl)
        if (!inputOk) {
            println("El número de control no es correcto. Debe tener el formato: XXX000X")
        }
    } while (!inputOk)
    return numeroControl
}