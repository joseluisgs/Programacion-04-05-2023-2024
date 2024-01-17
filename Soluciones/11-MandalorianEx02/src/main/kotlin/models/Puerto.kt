package models

const val LIBRE = "_"

/**
 * Clase que representa el puerto de atraque
 * @property nombre Nombre del puerto
 * @property darsenas Número de dársenas del puerto
 * @property puertas Número de puertas por dársena
 * @property mapa Mapa de plazas del puerto
 */
class Puerto(
    val nombre: String,
    val darsenas: Int,
    val puertas: Int,
) {

    private val mapa: Array<Array<String>> = Array(darsenas) { Array(puertas) { LIBRE } }
    private val letras: Array<String> = arrayOf("A", "B", "C", "D", "E")

    override fun toString(): String {
        val mapaString = StringBuilder()
        mapaString.append("Puerto: $nombre\n")
        for (i in 0..<darsenas) {
            if (i == 0) {
                mapaString.append(" ")
                for (j in 0..<puertas) {
                    mapaString.append(" ${j + 1} ")
                }
                mapaString.append("\n")
            }
            for (j in 0..<puertas) {
                if (j == 0) {
                    mapaString.append(letras[i])
                }
                mapaString.append("[${mapa[i][j]}]")
            }
            mapaString.append("\n")
        }

        mapaString.append("Plazas libres: ${getNumPlazasLibres()}\n")
        mapaString.append("Plazas ocupadas: ${darsenas * puertas - getNumPlazasLibres()}\n")
        return mapaString.toString()
    }

    /**
     * Devuelve el número de plazas libres
     * @return Int Número de plazas libres
     */
    private fun getNumPlazasLibres(): Int {
        var numPlazasLibres = 0
        for (i in 0..<darsenas) {
            for (j in 0..<puertas) {
                if (mapa[i][j] == LIBRE) {
                    numPlazasLibres++
                }
            }
        }
        return numPlazasLibres
    }

    /**
     * Comprueba si hay plazas libres
     * @return Boolean True si hay plazas libres, false en caso contrario
     * @see getNumPlazasLibres
     */
    private fun hayPlazasLibres(): Boolean {
        return getNumPlazasLibres() > 0
    }

    private fun getPrimerPlazaLibre(): Posicion? {
        for (i in 0..<darsenas) {
            for (j in 0..<puertas) {
                if (mapa[i][j] == LIBRE) {
                    return Posicion(i, j)
                }
            }
        }
        return null

    }

    /**
     * Atracar una nave en el puerto
     * @param numeroControl Número de control de la nave
     * @return String Mensaje de resultado de la operación
     * @see getNumeroControl
     * @see hayPlazasLibres
     * @see getPrimerPlazaLibre
     * @see Posicion
     * @see mapa
     * @throws Exception Si no hay plazas libres
     */
    fun atracarNave(numeroControl: String): String {
        if (!hayPlazasLibres()) {
            return "No hay plazas libres. No puede atracar la nave"
        }
        // No debe existir una nave con el mismo número de control
        if (buscarPosicion(numeroControl) != null) {
            return "Ya existe una nave con el número de control $numeroControl"
        }
        // hay posiciones libres
        // buscamos la primera posición libre
        val posicion = getPrimerPlazaLibre() ?: throw Exception("No hay plazas libres")
        // asignamos el número de control a la posición
        mapa[posicion.fila][posicion.columna] = numeroControl
        return "La nave $numeroControl ha atracado en la posición ${letras[posicion.fila]}:${posicion.columna + 1}"
    }


    /**
     * Busca una nave en el puerto
     * @param numeroControl Número de control de la nave
     * @return String Mensaje de resultado de la operación
     * @see getNumeroControl
     */
    fun buscarNave(numeroControl: String): String {
        val posicion: Posicion? = buscarPosicion(numeroControl)
        return if (posicion != null) {
            "La nave $numeroControl está en la posición ${letras[posicion.fila]}:${posicion.columna + 1}"
        } else {
            "La nave $numeroControl no está en el puerto"
        }
    }

    /**
     * Despega una nave del puerto de atraque
     * @param numeroControl Número de control de la nave
     * @return String Mensaje de resultado de la operación
     * @see getNumeroControl
     */
    fun despegarNave(numeroControl: String): String {
        val posicion: Posicion? = buscarPosicion(numeroControl)
        if (posicion != null) {
            mapa[posicion.fila][posicion.columna] = LIBRE
            return "La nave $numeroControl ha despegado"
        } else {
            return "La nave $numeroControl no está en el puerto"
        }
    }

    /**
     * Busca la posición de una nave en el mapa
     * @param numeroControl Número de control de la nave
     * @return Posicion? Posición de la nave o null si no está en el puerto
     */
    private fun buscarPosicion(numeroControl: String): Posicion? {
        var posicion: Posicion? = null
        for (i in 0..<darsenas) {
            for (j in 0..<puertas) {
                if (mapa[i][j] == numeroControl) {
                    posicion = Posicion(i, j)
                    break
                }
            }
        }
        return posicion
    }

    /**
     * Devuelve un listado de las naves del puerto en descendente por número de control
     * @return String Listado de naves
     */
    fun listadoNaves(): String {
        // Creamos un array con los números de control de las naves
        val naves = Array(darsenas * puertas - getNumPlazasLibres()) { "" }
        if (naves.isEmpty()) {
            return "No hay naves en el puerto"
        }
        // Las copiamos!!
        var indice = 0 // indice del array de naves
        for (i in 0..<darsenas) {
            for (j in 0..<puertas) {
                if (mapa[i][j] != LIBRE) {
                    naves[indice] = mapa[i][j]
                    indice++ // incrementamos el indice
                }
            }
        }
        // Ordenamos el array por burbuja descedente
        for (i in 0..<naves.size - 1) {
            for (j in 0..<naves.size - i - 1) {
                if (naves[j] < naves[j + 1]) {
                    val aux = naves[j]
                    naves[j] = naves[j + 1]
                    naves[j + 1] = aux
                }
            }
        }
        // Creamos el listado
        return naves.joinToString(prefix = "Listado de naves: [", postfix = "]", separator = ", ")
    }
}