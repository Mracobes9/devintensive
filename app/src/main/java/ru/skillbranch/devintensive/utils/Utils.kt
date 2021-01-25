package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName:String?):Pair<String?, String?> {
        var parts:List<String>? = fullName?.split(" ")
        var firsName = parts?.getOrNull(0)
        var lastName = parts?.getOrNull(1)

        firsName = if(firsName.equals("")) null else firsName
        lastName = if(lastName.equals("")) null else lastName

        return firsName to lastName
    }

    fun transliteration(payload: String, divider: String = " "): String {
        var result = ""
        for(char in payload) {
            val r:String = when("$char".toLowerCase()) {
                "а" -> "a"
                "б" -> "b"
                "в" -> "v"
                "г" -> "g"
                "д" -> "d"
                "е" -> "e"
                "ё" -> "e"
                "ж" -> "zh"
                "з" -> "z"
                "и" -> "i"
                "й" -> "i"
                "к" -> "k"
                "л" -> "l"
                "м" -> "m"
                "н" -> "n"
                "о" -> "o"
                "п" -> "p"
                "р" -> "r"
                "с" -> "s"
                "т" -> "t"
                "у" -> "u"
                "ф" -> "f"
                "х" -> "h"
                "ц" -> "c"
                "ч" -> "ch"
                "ш" -> "sh"
                "щ" -> "sh'"
                "ъ" -> ""
                "ы" -> "i"
                "ь" -> ""
                "э" -> "e"
                "ю" -> "yu"
                "я" -> "ya"
                " " -> divider
                else-> "$char"
            }

            result += if(char.isUpperCase()) r.toUpperCase() else r
        }
        return result
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        var firstPart = firstName?.trim()?.getOrNull(0)?.toUpperCase()
        var secondPart = lastName?.trim()?.getOrNull(0)?.toUpperCase()

        var result: String? = null
        if((firstPart != null) or (secondPart != null)) {
            result = ""
        }

        if(firstPart != null) {
            result += firstPart
        }


        if(secondPart != null) {
            result += secondPart
        }

        return result
    }
}