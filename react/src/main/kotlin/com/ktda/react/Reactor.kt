package com.ktda.react

import kotlinx.coroutines.experimental.launch
import net.dv8tion.jda.core.entities.Guild
import net.dv8tion.jda.core.entities.Message
import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent
import net.dv8tion.jda.core.events.message.react.MessageReactionRemoveEvent

abstract class Reactor {

    val messages: MutableMap<in String, Message> = mutableMapOf()
    abstract val emotes: List<String>
    fun react(msg: Message, guild: Guild) {
        launch {
            emotes.forEach { msg.addReaction(it).queue() }
            messages[guild.id + msg.id] = msg
        }
    }

    abstract fun onReactionAdd(e: MessageReactionAddEvent)
    abstract fun onReactionRemove(e: MessageReactionRemoveEvent)
}