package factories

import models.*
import models.enums.DroidType


/**
 * A factory class for generating Droid objects.
 */
class DroidFactory {
    companion object {
        private var droidId = 0

        /**
         * Generates a random Droid object with various properties.
         *
         * @return A randomly generated Droid object.
         */
        fun random(): Droid {
            val random = (1..100).random()
            droidId++
            return when {
                random <= 20 -> SW348(droidId, 50, (9..12).random())
                random <= 60 -> SW447(droidId, 100, (5..10).random())
                random <= 80 -> SW421(droidId, (100..150).random(), (10..30).random())
                else -> SW6969(id = droidId) // El resto por defecto!!
            }
        }

        /**
         * Creates a Droid object based on the given Type.
         *
         * @param type The type of the Droid. It can be one of the following: SW348, SW447, or SW421.
         * @return A Droid object with the specified type. The Droid object's properties are set based on the given type.
         */
        fun fromType(type: DroidType): Droid {
            droidId++
            return when (type) {
                DroidType.SW348 -> SW348(droidId, 50, (9..12).random())
                DroidType.SW447 -> SW447(droidId, 100, (5..10).random())
                DroidType.SW421 -> SW421(droidId, (100..150).random(), (10..30).random())
                DroidType.SW6969 -> SW6969(id = droidId) // El resto por defecto!!
            }
        }
    }

}