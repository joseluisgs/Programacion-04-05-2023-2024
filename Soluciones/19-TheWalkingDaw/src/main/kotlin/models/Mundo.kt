package models

import kotlin.random.Random

private const val TAM_TABLERO = 5 // Tamaño del mundo
private const val NUM_BLOQUES = 7 // Cantidad de bloques con los que chocamos
private const val NUM_ENEMIGOS = 10 // Cantidad de enemigos
private const val NUM_PAQUETES_VIDAS = 2 // Cantidad de paquetes de vidas
private const val NUM_PAQUETES_MUNICION = 2 // Cantidad de paquetes de vidas
private const val DURACION_JUEGO = 30_000 // 10 segundos
private const val PAUSA = 1000 // 1 segundo
private const val ENEMIGOS_INTERVALO = 5000 // 5 segundos
private const val VIDA_INTERVALO = 7500 // 5 segundos
private const val MUNICION_INTERVALO = 10000 // 5 segundos
const val PROB_RECIBIR_ATAQUE = 50 // probabilidad de ataque

class Mundo {
    private var tablero = Array(TAM_TABLERO) { arrayOfNulls<Any>(TAM_TABLERO) }
    private var tableroBuffer = Array(TAM_TABLERO) { arrayOfNulls<Any>(TAM_TABLERO) }
    private val prota = Protagonista(pistola = Pistola()) // Inyectamos la pistola (Dependencia)
    private var enemigosMuertos = 0

    private var tiempo = 0
    private var salida = Continuacion.CONTINUAR
    private var direccion = Direccion.entries.random()
    private var posicion = Posicion(tablero.indices.random(), tablero.indices.random())


    init {
        println("Bienvenido al juego de los zombies!")
        tablero[posicion.fila][posicion.columna] = prota
        tablero = inicializarTablero()
        // imprimirTablero(tablero)
    }

    // Inicializa el mundo con los enemigos, bloques y vidas
    private fun inicializarTablero(): Array<Array<Any?>> {
        var nuevoTablero = copiarTablero(tablero, Array(TAM_TABLERO) { arrayOfNulls<Any>(TAM_TABLERO) })
        repeat(NUM_PAQUETES_VIDAS) {
            nuevoTablero = colocarEnPosicionAleatoria(PaqueteVida(), nuevoTablero)
        }
        repeat(NUM_PAQUETES_MUNICION) {
            nuevoTablero = colocarEnPosicionAleatoria(PaqueteMunicion(), nuevoTablero)
        }
        repeat(NUM_BLOQUES) {
            nuevoTablero = colocarEnPosicionAleatoria(Bloque(), nuevoTablero)
        }
        repeat(NUM_ENEMIGOS) {
            nuevoTablero = colocarEnPosicionAleatoria(Zombie(), nuevoTablero)
        }
        return nuevoTablero
    }

    // Copia el mundo a un nuevo mundo
    private fun copiarTablero(origen: Array<Array<Any?>>, destino: Array<Array<Any?>>): Array<Array<Any?>> {
        for (i in origen.indices) {
            for (j in origen[i].indices) {
                destino[i][j] = origen[i][j]
            }
        }
        return destino
    }

    // Coloca un item aleatoriamente en el mundo
    private fun colocarEnPosicionAleatoria(item: Any, tablero: Array<Array<Any?>>): Array<Array<Any?>> {
        var fila: Int
        var columna: Int

        val nuevoTablero = copiarTablero(tablero, Array(TAM_TABLERO) { arrayOfNulls<Any>(TAM_TABLERO) })
        // Soretamos una posición aleatoria hasta que encontremos una vacía
        do {
            fila = (0 until TAM_TABLERO).random()
            columna = (0 until TAM_TABLERO).random()
        } while (nuevoTablero[fila][columna] != null)
        nuevoTablero[fila][columna] = item
        return nuevoTablero
    }

    // Verifica si hay algún espacio vacío en el mundo
    private fun hayEspacioLibre(tablero: Array<Array<Any?>>): Boolean {
        for (i in tablero.indices) {
            for (j in tablero[i].indices) {
                if (tablero[i][j] == null) {
                    return true
                }
            }
        }
        return false
    }

    // Imprime el mundo
    private fun imprimirTablero(world: Array<Array<Any?>>) {
        println("╔═" + "═══".repeat(TAM_TABLERO) + "═╗")
        for (i in world.indices) {
            print("║ ")
            for (j in world[i].indices) {
                when (world[i][j]) {
                    is Protagonista -> print(" P ")
                    is Zombie -> print(" Z ")
                    is Bloque -> print(" B ")
                    is PaqueteVida -> print(" V ")
                    is PaqueteMunicion -> print(" M ")
                    else -> print("   ")
                }
            }
            println(" ║")
        }
        println("╚═" + "═══".repeat(TAM_TABLERO) + "═╝")
    }

    fun simular() {
        do {
            println("Tiempo: $tiempo")
            println("Vida: ${prota.salud}")
            println("Municion: ${prota.pistola.municion}")
            println("Direccion: ${miDireccion(direccion)}")
            imprimirTablero(tablero)
            println()

            // Doble buffer, para no modificar el mundo original, los cambios se hacen en el buffer
            tableroBuffer = copiarTablero(tablero, tableroBuffer)

            accionJuego()

            // Copiamos el buffer al mundo original
            tablero = copiarTablero(tableroBuffer, tablero)

            // Esperamos un segundo
            Thread.sleep(PAUSA.toLong())
            tiempo += PAUSA

            // Cada cinco segundos, aparece un enemigo nuevo
            if (tiempo % ENEMIGOS_INTERVALO == 0 && hayEspacioLibre(tablero)) { // Verificamos si hay espacio para un nuevo enemigo
                println("Aparece un nuevo enemigo!")
                tablero = colocarEnPosicionAleatoria(Zombie(), tablero)
            }

            // Cada cinco segundos, aparece un enemigo nuevo
            if (tiempo % VIDA_INTERVALO == 0 && hayEspacioLibre(tablero)) { // Verificamos si hay espacio para un nuevo enemigo
                println("Aparece un paquete de vida!")
                tablero = colocarEnPosicionAleatoria(PaqueteVida(), tablero)
            }

            // Cada cinco segundos, aparece un enemigo nuevo
            if (tiempo % MUNICION_INTERVALO == 0 && hayEspacioLibre(tablero)) { // Verificamos si hay espacio para un nuevo enemigo
                println("Aparece un paquete de municion!")
                tablero = colocarEnPosicionAleatoria(PaqueteMunicion(), tablero)
            }

            println()

            // Ciclo hasta que se acabe el tiempo o la vida
        } while (tiempo < DURACION_JUEGO && prota.salud > 0 && salida != Continuacion.SALIR)

        println()
        println("\nFin del juego!")
        if (prota.salud <= 0) {
            println("Has muerto!")
            prota.salud = 0
        } else {
            println("Has sobrevivido!")
        }
        if (salida == Continuacion.SALIR) {
            println("Has escapado!")
        }
        println("Has matado a $enemigosMuertos enemigos.")
        println("Tu vida final fue ${prota.salud}.")
        println("Tu municion final fue ${prota.pistola.municion}.")
    }

    private fun miDireccion(direccion: Direccion): String {
        return when (direccion) {
            Direccion.NORTE -> "Norte"
            Direccion.SUR -> "Sur"
            Direccion.ESTE -> "Este"
            Direccion.OESTE -> "Oeste"
        }
    }


    private fun accionJuego() {
        // Calculamos la nueva posición
        val nuevaPosicion = Posicion(posicion.fila, posicion.columna)
        var direccionActual = Direccion.entries.random()
        println("Nueva dirección, nos movemos en ${miDireccion(direccionActual)}")



        when (direccionActual) {
            Direccion.NORTE -> if (posicion.fila > 0) nuevaPosicion.fila-- // Movimiento hacia el norte, decrementamos la fila
            Direccion.SUR -> if (posicion.fila < TAM_TABLERO - 1) nuevaPosicion.fila++ // Movimiento hacia el sur, incrementamos la fila
            Direccion.ESTE -> if (posicion.columna < TAM_TABLERO - 1) nuevaPosicion.columna++ // Movimiento hacia el este, incrementamos la columna
            Direccion.OESTE -> if (posicion.columna > 0) nuevaPosicion.columna-- // Movimiento hacia el oeste, decrementamos la columna
        }


        println("Nueva posición: ${nuevaPosicion.fila}, ${nuevaPosicion.columna}")

        // Si la nueva posición está fuera del mundo, cambiamos la dirección
        if (nuevaPosicion.fila !in tablero.indices || nuevaPosicion.columna !in tablero.indices) {
            direccionActual = cambiarDireccionEnLimiteTablero(tablero, posicion, direccionActual)
            nuevaPosicion.fila = posicion.fila
            nuevaPosicion.columna = posicion.columna
        }

        // Actualizamos la posición del jugador
        tableroBuffer[posicion.fila][posicion.columna] = null
        posicion.fila = nuevaPosicion.fila
        posicion.columna = nuevaPosicion.columna

        // Si se encuentra con un enemigo o una vida, se ejecuta la acción correspondiente
        when (tablero[posicion.fila][posicion.columna]) {
            null -> println(
                "No encontramos nada en ${posicion.fila}, ${posicion.columna}, seguimos avanzado en ${
                    miDireccion(
                        direccionActual
                    )
                }"
            )

            is Bloque -> {
                // Si se encuentra con un bloque y tiene al menos 2 municiones, dispara al bloque
                if (prota.pistola.municion >= 2) {
                    prota.pistola.municion -= 2
                    tableroBuffer[posicion.fila][posicion.columna] = null
                    println("Hemos disparado al bloque en ${posicion.fila}, ${posicion.columna} y lo hemos destruido gastando dos balas.")
                } else {
                    // Si no tiene suficiente munición, intenta cambiar de dirección
                    val newDirection =
                        cambiarDireccionCuandoHayBloque(tablero, posicion, direccion)
                    if (newDirection != direccionActual) {
                        direccionActual = newDirection
                    } else {
                        // Si todas las direcciones están bloqueadas, el jugador se queda en su posición actual
                        println("Estamos atrapados por bloques y no tenemos suficientes balas. Nos quedamos en nuestra posición actual.")
                    }
                }
            }

            is Zombie -> {
                println("Encontramos un enemigo en ${posicion.fila}, ${posicion.columna}")
                // Si tenemos munición, atacamos al enemigo
                if (prota.pistola.municion > 0) {
                    // El enemigo tiene una probabilidad del 60% de atacarnos antes de que podamos disparar
                    if (Random.nextInt(100) < PROB_RECIBIR_ATAQUE) {
                        prota.salud--
                        println("El enemigo nos ha atacado antes! Perdemos una vida.")
                    }
                    // Disparamos al enemigo
                    prota.pistola.municion--
                    println("Hemos matado al enemigo en ${posicion.fila}, ${posicion.columna} gastando una bala.")
                } else {
                    // Si no hay munición, el jugador puede moverse a una casilla con un enemigo, pero pierde dos vidas
                    prota.salud -= 2
                    println("Hemos atacado al enemigo con el machete y hemos perdido dos vidas.")
                }
                enemigosMuertos++
                tableroBuffer[posicion.fila][posicion.columna] = null
            }

            is PaqueteVida -> {
                // Si se encuentra con una vida, la recoge
                val paquete = tablero[posicion.fila][posicion.columna] as PaqueteVida
                prota.salud += paquete.vida
                tableroBuffer[posicion.fila][posicion.columna] = null
                println("Hemos encontrado una vida en ${posicion.fila}, ${posicion.columna} y la hemos recogido.")
            }

            is PaqueteMunicion -> {
                // Si se encuentra con una munición, la recoge
                val paquete = tablero[posicion.fila][posicion.columna] as PaqueteMunicion
                prota.pistola.municion += paquete.municion
                tableroBuffer[posicion.fila][posicion.columna] = null
                println("Hemos encontrado una munición en ${posicion.fila}, ${posicion.columna} y la hemos recogido.")
            }

        }

        // Actualizamos la posición del jugador en el buffer
        // tableroBuffer[posicion.fila][posicion.columna] = null
        tableroBuffer[posicion.fila][posicion.columna] = prota
        // Hemos salido si la posición nueva es [4,4]
        salida = Continuacion.CONTINUAR
        if (posicion.fila == TAM_TABLERO - 1 && posicion.columna == TAM_TABLERO - 1) {
            println("Hemos salido del mundo!")
            salida = Continuacion.SALIR
        }
    }


    // Cambia la dirección de movimiento a una dirección no bloqueada cuando se sale del mundo
    private fun cambiarDireccionEnLimiteTablero(
        tablero: Array<Array<Any?>>,
        posicion: Posicion,
        direccion: Direccion
    ): Direccion {
        // Debemos cambiar la dirección a una dirección no bloqueada y que no sea la actual
        var nuevaDireccion = direccion
        val direcciones = Direccion.entries
        var direccionesSinActual = arrayOf<Direccion>()
        for (dir in direcciones) {
            if (dir != direccion) {
                direccionesSinActual += dir
            }
        }
        // Barajamos las direcciones
        val direccionesShuffled = barajarArray(direccionesSinActual)

        for (dir in direccionesShuffled) {
            val nuevaFil =
                if (dir == Direccion.NORTE) posicion.fila - 1 else if (dir == Direccion.SUR) posicion.fila + 1 else posicion.fila
            val nuevaCol =
                if (dir == Direccion.ESTE) posicion.columna + 1 else if (dir == Direccion.OESTE) posicion.columna - 1 else posicion.columna

            // if (nuevaFil in tablero.indices && nuevaCol in tablero.indices && tablero[nuevaFil][nuevaCol] != 'B' && tablero[nuevaFil][nuevaCol] != 'E') {
            if (nuevaFil in tablero.indices && nuevaCol in tablero.indices) {
                nuevaDireccion = dir
                break // Salimos del bucle
            }
        }
        println("Nos hemos salido del mundo y cambiamos de dirección a ${miDireccion(nuevaDireccion)}")
        return nuevaDireccion
    }

    // Cambia la dirección de movimiento cuando se encuentra un bloque
    private fun cambiarDireccionCuandoHayBloque(
        tablero: Array<Array<Any?>>,
        posicion: Posicion,
        direccion: Direccion
    ): Direccion {
        var nuevaDireccion = direccion
        val direcciones = barajarArray(Direccion.values())
        var estaTodoBloqueado = true

        for (dir in direcciones) {
            val nuevaFila =
                if (dir == Direccion.NORTE) posicion.fila - 1 else if (dir == Direccion.SUR) posicion.fila + 1 else posicion.fila
            val nuevaColumna =
                if (dir == Direccion.ESTE) posicion.columna + 1 else if (dir == Direccion.OESTE) posicion.columna - 1 else posicion.columna

            // Si llegamos aquí, es porque la nueva posición está dentro del mundo
            // Hacemos un movimiento seguro, es decir, si la nueva posición es un bloque o un enemigo, no cambiamos de dirección
            if (nuevaFila in tablero.indices && nuevaColumna in tablero.indices && tablero[nuevaFila][nuevaColumna] != 'B' && tablero[nuevaFila][nuevaColumna] != 'E') {
                // if (newPosFil in world.indices && newPosCol in world.indices) {
                nuevaDireccion = dir
                estaTodoBloqueado = false
                break // Salimos del bucle
            }
        }

        if (estaTodoBloqueado) {
            println("Todas las direcciones están bloqueadas. El jugador se queda en su posición actual.")
        } else {
            println("Chocamos con un bloque y cambiamos de dirección a ${miDireccion(nuevaDireccion)}")
        }

        return nuevaDireccion
    }

    // Desordena un array
    private fun barajarArray(array: Array<Direccion>): Array<Direccion> {
        val rand = Random
        for (i in array.size - 1 downTo 1) {
            val j = rand.nextInt(i + 1)
            // Intercambio
            val temp = array[i]
            array[i] = array[j]
            array[j] = temp
        }
        return array
    }
}