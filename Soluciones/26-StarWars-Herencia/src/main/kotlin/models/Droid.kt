package models

import java.time.LocalDateTime

/**
 * Represents a Droid object with various properties and behaviors.
 *
 * @property maxEnergy The maximum energy level of the Droid.
 * @property defense The defense level of the Droid.
 * @property color The color representation of the Droid based on its type.
 * @property isAlive Indicates whether the Droid is alive or not.
 */
abstract class Droid(
    val id: Int,
    var maxEnergy: Int
) {

    open val color: String = "[🔴]"

    protected val createdAt = LocalDateTime.now()

    val isAlive: Boolean
        get() = maxEnergy > 0

    override fun toString(): String {
        return "Droid-"
    }
}
