package models

const val PROB_ESPECIAL = 10
const val PROB_VIP = 30
const val PROB_NORMAL = 60

/**
 * Vamos a aplicar un patrón de diseño llamado Factory
 * Anulando el constructor por defecto y creando un factory
 * para crear las butacas según necesitemos, por ejemplo:
 * - Butaca.default() -> Butaca()
 * - Butaca.random() -> Butaca() o Butaca(tipo = TipoButaca.ESPECIAL) o Butaca(tipo = TipoButaca.VIP)
 */

class Butaca private constructor(
    var ocupada: Boolean = false,
    val tipo: TipoButaca = TipoButaca.NORMAL
) {

    // Hacemos un factory con companion object
    companion object {
        fun default(): Butaca {
            return Butaca()
        }

        fun vip(): Butaca {
            return Butaca(tipo = TipoButaca.VIP)
        }

        fun especial(): Butaca {
            return Butaca(tipo = TipoButaca.ESPECIAL)
        }

        fun random(): Butaca {
            val random = (0..100).random()
            return when {
                random <= PROB_ESPECIAL -> Butaca(tipo = TipoButaca.ESPECIAL)
                random <= PROB_VIP -> Butaca(tipo = TipoButaca.VIP)
                else -> Butaca()
            }
        }
    }

    override fun toString(): String {
        return when (ocupada) {
            true -> "[🔴]"
            false -> when (tipo) {
                TipoButaca.NORMAL -> "[🟢]"
                TipoButaca.ESPECIAL -> "[🔵]"
                TipoButaca.VIP -> "[🟣]"
            }
        }
    }
}