package dev.joseluisgs.models.banda

class CantanteA(
    nombre: String
) : MusicoAbstract(nombre) {
    override fun tocar() {
        println("Tocando cantante")
    }

    fun cantar() {
        println("Cantando")
    }
}