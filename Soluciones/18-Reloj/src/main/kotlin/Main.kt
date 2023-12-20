import models.Reloj

fun main(args: Array<String>) {
    // val reloj = Reloj(17)
    val reloj = Reloj.create(17, 59, 59)
    println(reloj)
    println(reloj.getTime())
    println(reloj.getTime(false))

    reloj.addMinutes(20)
    println(reloj)
    reloj.addMinutes(40)
    println(reloj)
    reloj.addSeconds(40)
    println(reloj)
    reloj.addSeconds(20)
    println(reloj)
    reloj.addHours(2)
    println(reloj)
    reloj.addHours(5)
    println(reloj)
    reloj.addTime(19, 49, 30)
    println(reloj)
    reloj.addTime(12, 0, 30)
    println(reloj)
    println(reloj.getTime(false))

    val relojNow = Reloj.now()

    repeat(60) {
        relojNow.addSeconds(1)
        println(relojNow)
        Thread.sleep(1000)
    }
}