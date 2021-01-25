package ru.skillbranch.devintensive.extensions

fun String.truncate(length: Int = 16):String {
    var result = this.trimEnd()

    try {
        result = result.substring(0, length).trimEnd() + "..."
    } catch (e: StringIndexOutOfBoundsException) {
        return result
    }

    return result
}

fun String.stripHtml(): String {
    return this
            .replace(Regex("<[\\w\\s=\"'\\/]*>"), "")
            .replace(Regex("\\s+"), " ")
}