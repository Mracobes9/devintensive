package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.models.User
import ru.skillbranch.devintensive.models.UserView
import ru.skillbranch.devintensive.utils.Utils
import java.util.*
import kotlin.math.abs

fun User.toUserViiew():UserView {
    val nickName = Utils.transliteration("$firstName $lastName")
    val initials = Utils.toInitials(firstName, lastName)
    val status = "${if(lastVisit == null) "Еще ни разу не был" else if(isOnline) "online" else "Последний раз был ${lastVisit.humanizeDiff()}"}"

    return UserView(id, fullName = "$firstName $lastName", nickName = nickName, initials = initials, avatar = avatar, status = status)
}

fun Date.humanizeDiff(date: Date = Date()): String {
    val diff = abs(this.time - date.time) / 1000

    var result = when(diff) {
        in 0..1 -> "только что"
        in 1..45 -> "несколько секунд"
        in 45..75 -> "минуту"
        in 75..2700 -> {
            val n = diff / 60
            val textPart = when((n % 10)) {
                1L -> "минута"
                in 2L..4L -> "минуты"
                else -> "минут"
            }
            "$n $textPart"
        }//"${diff / 60} минут назад"
        in 2700..4500 -> "час"
        in 4500..79200 -> {
            val n = diff / 3600
            val textPart = when((n % 10)) {
                1L -> "час"
                in 2L..4L -> "часа"
                else -> "часов"
            }
            "$n $textPart"
        } //"${diff / 3600} часов назад"
        in 79200..93600 -> "день"
        in 93600..31104000 -> {
            val n = diff / 86400
            val textPart = when((n % 10)) {
                1L -> "день"
                in 2L..4L -> "дня"
                else -> "дней"
            }
            "$n $textPart"
        }//"${diff / 86400} дней назад"
        else -> if((this.time - date.time) < 0) "более года назад" else "более чем через год"
    }

    if((diff >= 1) and (diff < 31104000)) {
        result = if((this.time - date.time) < 0) "$result назад" else "через $result"
    }

    return result
}