package dev.joseluisgs.models

open class C : B() {
    init {
        println("Init C")
    }

    override fun test() {
        println("Test from C")
    }

    fun superTest() {
        super.test() // Llamamos al test de B (o el que sea su padre)
        this.test() // Llamamos al test de C
    }
}