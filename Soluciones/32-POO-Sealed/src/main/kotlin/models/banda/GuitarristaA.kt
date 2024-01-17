package dev.joseluisgs.models.banda

class GuitarristaA(
    nombre: String
) : MusicoAbstract(nombre) {
    override fun tocar() {
        println("Tocando guitarra")
    }

    fun afinar() {
        println("Afinando")
    }
}