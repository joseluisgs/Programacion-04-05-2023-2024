package models

/**
 * Doid SW421
 * @property id The id of the Droid.
 * @property maxEnergy The maximum energy level of the Droid.
 * @property velocity The velocity of the Droid.
 */
class SW421(
    id: Int,
    maxEnergy: Int = (100..150).random(),
    val velocity: Int = (10..30).random()
) : Droid(id, maxEnergy) {

    override val color: String = "[🔵]"

    override fun toString(): String {
        return "${super.toString()}SW421(id=$id, energy=${this.maxEnergy}, velocity=$velocity, createdAt=$createdAt)"
    }

    fun move(): Boolean {
        println("Enemy tries to move with $velocity")
        return (1..100).random() <= velocity
    }
}