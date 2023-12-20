package dev.joseluisgs.models

open class B : A() {
    private val percentage: Int = 10

    init {
        println("Init B")
    }

    val salary: Int
        get() = this.value + this.percentage


    open fun test() {
        println("Test from B")
    }
}