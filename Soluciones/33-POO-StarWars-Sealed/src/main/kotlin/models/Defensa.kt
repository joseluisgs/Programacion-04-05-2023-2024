package models

interface Defensa : Accion {

    fun defend(damage: Int): Int
}