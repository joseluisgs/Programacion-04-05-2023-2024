package dev.joseluisgs.models

open class Persona(
    val nombre: String,
    val edad: Int,
) {
    override fun toString(): String {
        return "Persona(nombre='$nombre', edad=$edad)"
    }

    open fun presentarse() {
        println("Persona: Hola, soy $nombre y tengo $edad años")
    }
}