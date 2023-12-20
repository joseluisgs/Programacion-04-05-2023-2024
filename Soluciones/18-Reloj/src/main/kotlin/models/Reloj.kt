package models

import java.time.LocalTime

class Reloj private constructor(
    private var _horas: Int = 0,
    private var _minutos: Int = 0,
    private var _segundos: Int = 0
) {
    /*init {
        check(_horas in 0..23) { "Las horas deben estar entre 0 y 23" }
        check(_minutos in 0..59) { "Los minutos deben estar entre 0 y 59" }
        check(_segundos in 0..59) { "Los segundos deben estar entre 0 y 59" }
    }*/

    var horas: Int
        get() = _horas
        set(value) {
            require(value in 0..23) { "Las horas deben estar entre 0 y 23" }
            _horas = value
        }

    var minutos: Int
        get() = _minutos
        set(value) {
            require(value in 0..59) { "Los minutos deben estar entre 0 y 59" }
            _minutos = value
        }

    var segundos: Int
        get() = _segundos
        set(value) {
            require(value in 0..59) { "Los segundos deben estar entre 0 y 59" }
            _segundos = value
        }

    companion object {
        fun create(horas: Int = 0, minutos: Int = 0, segundos: Int = 0): Reloj {
            require(horas in 0..23) { "Las horas deben estar entre 0 y 23" }
            require(minutos in 0..59) { "Los minutos deben estar entre 0 y 59" }
            require(segundos in 0..59) { "Los segundos deben estar entre 0 y 59" }
            return Reloj(horas, minutos, segundos)
        }

        fun now(): Reloj {
            val now = LocalTime.now()
            return Reloj(now.hour, now.minute, now.second)
        }
    }


    override

    fun toString(): String {
        val strHoras = if (horas < 10) "0$horas" else "$horas"
        val strMinutos = if (minutos < 10) "0$minutos" else "$minutos"
        val strSegundos = if (segundos < 10) "0$segundos" else "$segundos"
        return "$strHoras:$strMinutos:$strSegundos"
    }

    fun getTime(is24Hours: Boolean = true): String {
        var strHoras = if (horas < 10) "0$horas" else "$horas"
        val strMinutos = if (minutos < 10) "0$minutos" else "$minutos"
        val strSegundos = if (segundos < 10) "0$segundos" else "$segundos"
        return if (is24Hours)
            "$strHoras:$strMinutos:$strSegundos"
        else {
            strHoras = if (horas > 12) "${horas - 12}" else "$horas"
            val strMeridian = if (horas > 12) "PM" else "AM"
            "$strHoras:$strMinutos:$strSegundos $strMeridian"
        }
    }

    fun addHours(hours: Int) {
        val tmpHoras = horas + hours
        horas = if (tmpHoras > 23) {
            tmpHoras % 24
        } else {
            tmpHoras
        }
    }

    fun addMinutes(minutes: Int) {
        val tmpMinutos = minutos + minutes
        minutos = if (tmpMinutos > 59) {
            addHours(tmpMinutos / 60)
            tmpMinutos % 60
        } else {
            tmpMinutos
        }
    }

    fun addSeconds(seconds: Int) {
        val tmpSegundos = segundos + seconds
        segundos = if (tmpSegundos > 59) {
            addMinutes(tmpSegundos / 60)
            tmpSegundos % 60
        } else {
            tmpSegundos
        }
    }

    fun addTime(hours: Int = 0, minutes: Int = 0, seconds: Int = 0) {
        addHours(hours)
        addMinutes(minutes)
        addSeconds(seconds)
    }
}