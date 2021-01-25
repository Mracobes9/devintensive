package ru.skillbranch.devintensive.extensions

import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern:String="HH:mm:ss dd.MM.yy"):String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))

    return dateFormat.format(this)
}

fun Date.add(value:Int, units:TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time

    time += when(units) {
        TimeUnits.SECOND-> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY;

    fun plural(value: Int): String {
        return "$value " + when(this) {
            TimeUnits.SECOND -> {
                when(abs(value) % 10) {
                    1 -> "секунда"
                    in 2..4 -> "секунды"
                    else -> "секунд"
                }
            }
            TimeUnits.MINUTE -> {
                when(abs(value) % 10) {
                    1 -> "минутаа"
                    in 2..4 -> "минуты"
                    else -> "минут"
                }
            }
            TimeUnits.HOUR -> {
                when(abs(value) % 10) {
                    1 -> "час"
                    in 2..4 -> "часа"
                    else -> "часов"
                }
            }
            TimeUnits.DAY -> {
                when(abs(value) % 10) {
                    1 -> "день"
                    in 2..4 -> "дня"
                    else -> "дней"
                }
            }
        }
    }

}