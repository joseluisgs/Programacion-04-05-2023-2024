import controllers.Juego
import models.Personaje


fun main(args: Array<String>) {
    println("Entrenando la fuerza")

    val mando = Personaje("Mando", 40)
    val grogu = Personaje("Grogu", 30)

    println(mando)
    println(grogu)

    val juego = Juego(mando, grogu)
    juego.jugar()
    juego.informe()
    
}