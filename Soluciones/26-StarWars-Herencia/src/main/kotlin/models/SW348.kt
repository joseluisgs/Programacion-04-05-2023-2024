package models

import kotlin.math.min

/**
 * Doid SW348
 * @property id The id of the Droid.
 * @property maxEnergy The maximum energy level of the Droid.
 * @property defense The defense level of the Droid.
 */
class SW348(
    id: Int,
    maxEnergy: Int = 50,
    val defense: Int = (9..12).random()
) : Droid(id, maxEnergy) {

    // override val color: String = "[🔴]"

    /**
     * Defends against incoming damage.
     *
     * @param damage The amount of damage to be defended against.
     * @return The remaining damage after defending.
     */
    fun defend(damage: Int): Int {
        println("Enemy tries to defend with $defense")
        return min(damage, defense)
    }

    override fun toString(): String {
        return "${super.toString()}SW348(id=$id, energy=${this.maxEnergy}, defense=$defense, createdAt=$createdAt)"
    }
}