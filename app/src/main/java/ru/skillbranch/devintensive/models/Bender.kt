package ru.skillbranch.devintensive.models

class Bender(
    var status:Status = Status.NORMAL,
    var question:Question = Question.NAME
) {
    enum class Status(val color: Triple<Int, Int, Int>) {
        NORMAL(Triple(255,255,255)),
        WARNING(Triple(255,120,0)),
        DANGER(Triple(255,60,60)),
        CRITICAL(Triple(255,255,0));

        fun nextStatus():Status {
            return if(this.ordinal < values().lastIndex) {
                values()[this.ordinal + 1]
            } else {
                values()[0]
            }
        }
    }

    enum class Question(val question: String, val answer: List<String>) {
        NAME("Как меня зовут?", listOf("бендер", "bender")) {
            override fun nextQuestion(): Question  = PROFESSION
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender")) {
            override fun nextQuestion(): Question = MATERIAL
        },
        MATERIAL("Из чего я сделан?", listOf("металл", "дерево", "metal", "iron", "wood")) {
            override fun nextQuestion(): Question = BDAY
        },
        BDAY("Когда меня создали?", listOf("2993")) {
            override fun nextQuestion(): Question = SERIAL
        },
        SERIAL("Мой серийный номер?", listOf("2716057")) {
            override fun nextQuestion(): Question = IDLE
        },
        IDLE("На этом все, вопросов больше нет", listOf()) {
            override fun nextQuestion(): Question = IDLE
        };

        abstract fun nextQuestion(): Question
    }

    fun askQuestion():String = when(question) {
        Question.NAME -> Question.NAME.question
        Question.PROFESSION -> Question.PROFESSION.question
        Question.MATERIAL ->  Question.MATERIAL.question
        Question.BDAY -> Question.BDAY.question
        Question.SERIAL -> Question.SERIAL.question
        Question.IDLE ->  Question.SERIAL.question
    }

    fun listenAnswer(answer:String): Pair<String, Triple<Int,Int,Int>> {
        val isValid:Pair<Boolean, String?> = isAnswerValid(answer)
        if(!isValid.first) {
            return "${isValid.second}\n${question.question}" to status.color
        }
        var answer = answer.toLowerCase()
        return if(question.answer.contains(answer)) {
            question = question.nextQuestion()
            "Отлично - это правильный ответ!\n${question.question}" to status.color
        } else {
            status = status.nextStatus()
            "Это не правильный ответ!\n${question.question}" to status.color
        }
    }

    private fun isAnswerValid(answer: String): Pair<Boolean,String?> {
        val positiveAnswer = true to null
        return when(question) {
            Question.NAME -> {
                if(answer.getOrNull(0)?.isUpperCase() == true) positiveAnswer
                else false to "Имя должно начинаться с заглавной буквы"
            }
            Question.PROFESSION -> {
                if(answer.getOrNull(0)?.isLowerCase() == true) positiveAnswer
                else false to "Професси11я должна начинаться со строчной буквы"
            }
            Question.MATERIAL -> {
                if(Regex("\\d+").containsMatchIn(answer)) false to "Материал не должен содержать цифр"
                else positiveAnswer
            }
            Question.BDAY -> {
                if(Regex("\\D").containsMatchIn(answer)) false to "Год моего рождения должен " +
                        "содержать только цифры"
                else positiveAnswer
            }
            Question.SERIAL -> {
                if(!(Regex("\\D").containsMatchIn(answer)) and (answer.length == 7)) positiveAnswer
                else false to "Серийный номер содержит только цифры, и их 7"
            }//"Серийный номер содержит только цифры, и их 7"
            Question.IDLE -> true to null
        }
    }
}