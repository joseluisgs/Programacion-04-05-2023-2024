package mosca

const val MAX_VIDAS = 2

class Mosca(var maxVidas: Int = MAX_VIDAS) {

    fun isAlive() = maxVidas > 0

    fun kill() {
        maxVidas -= 1
    }

}