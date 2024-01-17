import personas.PI
import personas.Persona
import personas.imprimirSaludo
import personas.metodoOculto2

fun main(args: Array<String>) {
    val persona = Persona("Juan", 30)
    println(persona)

    imprimirSaludo("Juan")
    // metodoOculto("Juan")  // Es privado!!
    println(PI)
    // println(PI_CONST)  // Es privado!!
    metodoOculto2("Juan")
}