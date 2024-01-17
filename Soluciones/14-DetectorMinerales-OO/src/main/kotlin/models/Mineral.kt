package models

class Mineral(var cantidad: Int) {
    override fun toString(): String {
        return if (cantidad >= 10) {
            "$cantidad"
        } else {
            "0$cantidad"
        }
    }
}