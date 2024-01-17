package models

interface Explotar {

    fun explotar(): Boolean {
        println("Enemy tries to explote")
        return (1..100).random() <= 70
    }
}