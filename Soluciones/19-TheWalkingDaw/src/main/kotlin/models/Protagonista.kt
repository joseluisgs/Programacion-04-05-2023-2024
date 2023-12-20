package models

private const val SALUD_INICIAL = 10 // Vida inicial

data class Protagonista(
    val nombre: String = "Daryl",
    var salud: Int = SALUD_INICIAL,
    val pistola: Pistola
)