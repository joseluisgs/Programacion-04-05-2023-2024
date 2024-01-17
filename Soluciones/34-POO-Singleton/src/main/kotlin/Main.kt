package dev.joseluisgs

fun main() {
    println("Singleton")

    println("Sin Singleton")
    val a = A()
    println(a)
    val b = A()
    println(b)
    val c = A()
    println(c)

    println("Con Singleton de manual")
    val x = X.getInstance()
    println(x)
    val y = X.getInstance()
    println(y)
    val z = X.getInstance()
    println(z)

    println("Con Singleton de Kotlin (object)")
    val s = S
    println(s)
    val t = S
    println(t)
    val u = S
    println(u)
}