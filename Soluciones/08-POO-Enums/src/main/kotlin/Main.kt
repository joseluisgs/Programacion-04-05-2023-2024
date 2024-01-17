fun main(args: Array<String>) {
    println(Colores.ROJO)
    println(Colores.VERDE)
    println(Colores.AZUL)

    println(Colores.entries.size)
    println(Colores.valueOf("ROJO"))
    println(Colores.entries.joinToString())
    for (color in Colores.entries) {
        println(color)
    }
}