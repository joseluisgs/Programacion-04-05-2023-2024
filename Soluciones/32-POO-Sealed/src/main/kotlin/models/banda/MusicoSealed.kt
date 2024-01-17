package dev.joseluisgs.models.banda

sealed class MusicoSealed(
    val nombre: String
) {
    abstract fun tocar()

    fun presentar() {
        println("Hola soy $nombre")
    }

}