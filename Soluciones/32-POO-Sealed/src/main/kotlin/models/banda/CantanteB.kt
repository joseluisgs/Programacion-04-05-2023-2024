package dev.joseluisgs.models.banda

class CantanteB(
    nombre: String
) : MusicoSealed(nombre) {
    override fun tocar() {
        println("Tocando cantante")
    }

    fun cantar() {
        println("Cantando")
    }
}
