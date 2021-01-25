package ru.skillbranch.devintensive.models

import java.lang.IllegalStateException
import java.util.*

abstract class BaseMessage(
        val id:String,
        val from:User?,
        val chat:Chat,
        val isIncoming:Boolean = false,
        val date:Date = Date()
) {
    abstract  fun formatMessage():String

    companion object AbstractFactory {
        var lastId = -1
        fun makeMessage(from: User?, chat: Chat, date: Date = Date(), type:String="text", payload: Any?):BaseMessage {
            lastId++

            return when(type) {
                "Image" -> ImageMessage("$lastId", from = from, chat = chat, date = date, image = payload as String)
                "Text" -> TextMessage("$lastId", from = from, chat = chat, date = date, text = payload as String)
                else -> throw IllegalStateException("Invalid message type")
            }
        }
    }
}