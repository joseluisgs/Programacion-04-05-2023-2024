package dev.joseluisgs.factories

import dev.joseluisgs.models.A
import dev.joseluisgs.models.B
import dev.joseluisgs.models.C
import dev.joseluisgs.models.D

class TestFactory {

    companion object {
        fun create(type: Type): A {
            return when (type) {
                Type.B -> B()
                Type.C -> C()
                Type.D -> D()
            }
        }
    }

    enum class Type {
        B, C, D
    }
}