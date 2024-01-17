package dev.joseluisgs.models

abstract class A {
    val value: Int = 15

    private val youCantSeeMe: Int = 10
    protected val youCantSeeMeToo: Int = 10

    init {
        println("Init A")
    }
}