package models

/**
 * Doid SW421
 * @property id The id of the Droid.
 * @property maxEnergy The maximum energy level of the Droid.
 * @property velocity The velocity of the Droid.
 */
class SW6969(
    id: Int,
    maxEnergy: Int = 200,
    val shield: Int = 7,
    val velocity: Int = 20
) : Droid(id, maxEnergy), Defensa, Escapar, Explotar {

    override val color: String = "[🟡]"

    override fun toString(): String {
        return "${super.toString()}SW6969(id=$id, energy=${this.maxEnergy}, shield=$shield, velocity=$velocity, createdAt=$createdAt)"
    }

    override fun move(): Boolean {
        println("Enemy tries to move with $velocity")
        return (1..100).random() <= velocity
    }

    override fun defend(damage: Int): Int {
        println("Enemy tries to defend with $shield")
        return if (this.shield > damage) 0 else damage - this.shield
    }

    // Explorar va a ser un 30% de posibilidades de explotar
    // y cojo la implementación por defecto
}