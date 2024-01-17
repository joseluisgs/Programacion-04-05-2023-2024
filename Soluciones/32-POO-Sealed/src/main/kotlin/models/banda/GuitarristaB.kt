package dev.joseluisgs.models.banda

class GuitarristaB(
    nombre: String
) : MusicoSealed(nombre) {

    override fun tocar() {
        println("Tocando guitarra")
    }

    fun afinar() {
        println("Afinando")
    }
}