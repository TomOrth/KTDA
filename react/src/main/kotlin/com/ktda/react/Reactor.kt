package com.ktda.react

import net.dv8tion.jda.core.entities.Message
import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent
import net.dv8tion.jda.core.events.message.react.MessageReactionRemoveEvent

abstract class Reactor {

    val messages: MutableMap<Long, Message> = mutableMapOf()
    abstract val emotes: MutableList<String>
    fun react(msg: Message) {
        emotes.forEach { msg.addReaction(it).queue() }
        messages.put(msg.idLong, msg)
    }

    abstract fun onReactionAdd(e: MessageReactionAddEvent)
    abstract fun onReactionRemove(e: MessageReactionRemoveEvent)
}