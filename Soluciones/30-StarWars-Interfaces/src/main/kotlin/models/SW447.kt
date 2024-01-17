package models

/**
 * Doid SW447
 * @property id The id of the Droid.
 * @property maxEnergy The maximum energy level of the Droid.
 * @property shield The shield of the Droid.
 */
class SW447(
    id: Int,
    maxEnergy: Int = 100,
    val shield: Int = (5..10).random()
) : Droid(id, maxEnergy), Defensa {

    override val color: String = "[🟢]"

    /**
     * Defends against incoming damage.
     *
     * @param damage The amount of damage to be defended against.
     * @return The remaining damage after defending.
     */
    override fun defend(damage: Int): Int {
        println("Enemy tries to defend with $shield")
        return if (this.shield > damage) 0 else damage - this.shield
    }

    override fun toString(): String {
        return "${super.toString()}SW447(id=$id, energy=${this.maxEnergy}, shield=$shield, createdAt=$createdAt)"
    }
}