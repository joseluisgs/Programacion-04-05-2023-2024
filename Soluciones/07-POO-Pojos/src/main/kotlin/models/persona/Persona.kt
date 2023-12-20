package models.persona

/**
 * Los pojos son clases que solo tienen atributos y getters y setters
 * y no metodos salvo los de equals, hashcode y toString
 * en Kotlin se llaman data class
 * Si no te gusta los métodos que genera Kotlin, puedes sobreescribirlos
 * Preferiblemente deben ser inmutables
 */
data class Persona(
    val nombre: String,
    val edad: Int,
    //private val createdAt: LocalDateTime = LocalDateTime.now()
)
