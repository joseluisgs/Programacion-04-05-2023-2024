package models

interface Explotar : Accion {

    fun explotar(): Boolean {
        println("Enemy tries to explote")
        return (1..100).random() <= 70
    }
}