package personas

const val PI = 3.1416
private const val PI_CONST = 3.1416
internal const val PI_INTERNAL = 3.1416


fun imprimirSaludo(nombre: String) {
    metodoOculto(nombre)
}

private fun metodoOculto(nombre: String) {
    println("Hola, mi nombre es $nombre")
}

internal fun metodoOculto2(nombre: String) {
    println("Hola, mi nombre es $nombre")
}
