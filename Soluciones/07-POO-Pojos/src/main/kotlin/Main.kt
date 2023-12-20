import models.persona.Persona

fun main(args: Array<String>) {
    var p1 = Persona("Juan", 20)
    val p2 = Persona("Juan", 20)

    println(p1)
    println(p2)

    println(p1 == p2)
    println(p1 === p2)

    // Puedo usar la función copy para crear una copia de un objeto
    // modificando solo los atributos que yo quiera (si se da el caso)
    var p3 = p1.copy(edad = 30)
    println(p3)
    var p4 = p3.copy()
    var p5 = p3.copy(nombre = "Pedro", edad = 40)
    println(p4)
    println(p5)
    println(p3.hashCode())

    // Usamos destructuring para obtener los atributos de un objeto
    // y asignarlos a variables
    val (nombre, edad) = p1
    println(nombre)
    println(edad)

    // En vez de usar destructuring, puedo usar los getters
    val miNombre = p1.nombre
    val miEdad = p1.edad

}