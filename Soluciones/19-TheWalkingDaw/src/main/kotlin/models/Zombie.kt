package models

data class Zombie(var vida: Int = (1..2).random()) {
    fun recibirDanio(daño: Int) {
        vida -= daño
    }
}