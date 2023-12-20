package personas

class Persona(val nombre: String, var edad: Int) {
    private val id: Long = System.currentTimeMillis()

    fun saludar() {
        imprimirSaludo(nombre)
        metodoOculto2(nombre)
    }

    fun despedirse() {
        println("Adios, mi nombre es $nombre")
    }

    private fun calcularEdad() {
        println("Mi edad es $edad")
    }

    override fun toString(): String {
        return "Persona(nombre=$nombre, edad=$edad, id=$id)"
    }
}