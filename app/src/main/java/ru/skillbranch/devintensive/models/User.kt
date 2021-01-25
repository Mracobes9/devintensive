package ru.skillbranch.devintensive.models

import android.provider.ContactsContract
import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User(
        val id:String,
        var firstName:String?,
        var lastName:String?,
        var avatar:String?,
        var rating:Int = 0,
        var respect:Int = 0,
        val lastVisit: Date? = null,
        val isOnline:Boolean = false
) {
    var introBit:String

    constructor(id: String, firstName: String?, lastName: String?) : this(
            id=id,
            firstName = firstName,
            lastName = lastName,
            avatar = null
    )

    constructor(id: String) : this(id, "John", "Doe")

    init {
        introBit = getIntro()
        println("It's Alive!!! ${if(lastName === "Doe") "His name is $firstName $lastName" else "And his name is $firstName $lastName"}\n" +
                getIntro()
        )
    }

    private fun getIntro() = """
        tu tu ru tuuuuuu...
        tu tu ru tuuuuuuuuuuuuu.....
        
        
        tu tu ru tuuuuuu...
        tu tu ru tuuuuuuuuuuuuu.....
        ${"\n\n\n"}
        $firstName $lastName
        
        
    """.trimIndent()

    fun printMe() = println("""
            id: $id
            firstName: $firstName
            lastName: $lastName
            avatar: $avatar
            rating: $rating
            respect: $respect
            lastVisit: $lastVisit
            isOnline: $isOnline
        """.trimIndent())

    companion object Factory {
        private var lastId:Int = -1
        fun makeUser(fullName: String?):User {
            lastId++

            val (firstName, lastName) = Utils.parseFullName(fullName)

            return User(id="$lastId", firstName = firstName, lastName = lastName)
        }
    }

    class Builder {
        private val buildInfo = mutableMapOf<String, Any>()

        fun id(value: String):Builder {
            buildInfo.put("id", value)
            return this
        }
        fun firstName(value: String):Builder {
            buildInfo.put("firstName", value)
            return this
        }
        fun lastName(value: String):Builder {
            buildInfo.put("lastName", value)
            return this
        }
        fun avatar(value:String):Builder {
            buildInfo.put("avatar", value)
            return this
        }
        fun rating(value: Int):Builder {
            buildInfo.put("rating", value)
            return this
        }
        fun respect(value: Int):Builder {
            buildInfo.put("respect", value)
            return this
        }
        fun lastVisit(value: Date):Builder {
            buildInfo.put("lastVisit", value)
            return this
        }
        fun isOnline(value: Boolean):Builder {
            buildInfo.put("isOnline", value)
            return this
        }

        fun build():User {
            return User(
                    id = buildInfo["id"] as String,
                    firstName = buildInfo["firstName"] as String,
                    lastName = buildInfo["lastName"] as String,
                    avatar = buildInfo["avatar"] as String,
                    rating = buildInfo["rating"] as Int,
                    respect = buildInfo["respect"] as Int,
                    lastVisit = buildInfo["lastVisit"] as Date,
                    isOnline = buildInfo["isOnline"] as Boolean
            )
        }
    }
}