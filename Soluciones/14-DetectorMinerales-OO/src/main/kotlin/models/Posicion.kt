package models

data class Posicion(var fila: Int, var columna: Int) {

    fun getNuevaPosicion(direccion: Direccion) {
        val nuevaFila = fila + direccion.fila
        val nuevaColumna = columna + direccion.columna
        this.fila = nuevaFila
        this.columna = nuevaColumna
    }

    companion object {
        fun getInitial(size: Int): Posicion {
            val posicionFila = (0..<size).random()
            val posicionColumna = (0..<size).random()
            return Posicion(posicionFila, posicionColumna)
        }
    }

    override fun toString(): String {
        return "Posicion Actual: f: ${this.fila + 1}, c: ${this.columna + 1}"
    }
}