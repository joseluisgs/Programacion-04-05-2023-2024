package dev.joseluisgs

import dev.joseluisgs.models.banda.*

fun main() {
    val cantanteA = CantanteA("CantanteA")
    cantanteA.presentar()
    cantanteA.tocar()
    cantanteA.cantar()

    val guitarrista = GuitarristaA("GuitarristaA")
    guitarrista.presentar()
    guitarrista.tocar()
    guitarrista.afinar()

    tocandoConMusicoA(cantanteA)
    tocandoConMusicoA(guitarrista)

    val cantanteB = CantanteB("CantanteB")
    cantanteB.presentar()
    cantanteB.tocar()
    cantanteB.cantar()

    val guitarristaB = GuitarristaB("GuitarristaB")
    guitarristaB.presentar()
    guitarristaB.tocar()
    guitarristaB.afinar()

    tocandoConMusicoB(cantanteB)
    tocandoConMusicoB(guitarristaB)

    val tipoMusico: TipoMusico = TipoMusico.CANTANTE

    tocandoConMusicoC(tipoMusico)

}

fun tocandoConMusicoC(tipoMusico: TipoMusico) {
    when (tipoMusico) {
        TipoMusico.CANTANTE -> {
            println("Cantando")
        }

        TipoMusico.GUITARRISTA -> {
            println("Tocando guitarra")
        }
    }
}


fun tocandoConMusicoA(musico: MusicoAbstract) {
    when (musico) {
        is CantanteA -> {
            musico.cantar()
        }
        // No estás obligado a poner todas las opciones
        is GuitarristaA -> {
            musico.afinar()
        }
    }
}

fun tocandoConMusicoB(musico: MusicoSealed) {
    when (musico) {
        is CantanteB -> {
            musico.cantar()
        }

        is GuitarristaB -> {
            musico.afinar()
        }
    }
}