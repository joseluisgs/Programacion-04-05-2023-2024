package dev.joseluisgs

import dev.joseluisgs.factories.TestFactory
import dev.joseluisgs.factories.TestFactory.Type
import dev.joseluisgs.models.A
import dev.joseluisgs.models.B
import dev.joseluisgs.models.C
import dev.joseluisgs.models.D

fun main() {
    println("Herencia en Kotlin")

    // val a = A() // Es abstracta, no se puede instanciar
    println("Instanciamos B")
    val b = B()

    println("Instanciamos C")
    val c = C()

    println("Instanciamos D")
    val d = D()

    println("Instanciamos A")
    val a: A = D()

    println("Comprobamos los tipos de A")
    checkType(a)

    println("Comprobamos los tipos de B")
    checkType(b)

    println("Comprobamos los tipos de C")
    checkType(c)

    println("Comprobamos los tipos de D")
    checkType(d)

    val o = TestFactory.create(Type.B) // as B
    println("Comprobamos los tipos de o")
    checkType(o)

}

// En los parametros podemos pasar cualquier objeto que herede de A
fun checkType(o: A) {
    if (o is A) {
        println("Es A")
    }
    if (o is B) {
        println("Es B")
    }
    if (o is C) {
        println("Es C")
    }
    if (o is D) {
        println("Es D")
    }

    val test = B()
    test.salary
}