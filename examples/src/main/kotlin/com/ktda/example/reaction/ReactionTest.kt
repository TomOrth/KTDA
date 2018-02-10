package com.ktda.example.reaction

import com.ktda.react.Reactor
import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent
import net.dv8tion.jda.core.events.message.react.MessageReactionRemoveEvent

object ReactionTest : Reactor() {
    override val emotes: MutableList<String> = mutableListOf("\uD83D\uDC4D","\uD83D\uDC4E")
    override fun onReactionAdd(e: MessageReactionAddEvent) {
        if(messages.containsKey(e.messageIdLong) && !e.user.isBot) {
            when(e.reactionEmote.name) {
                emotes[0] -> e.channel.sendMessage("up").queue()
                emotes[1] -> e.channel.sendMessage("down").queue()
            }
        }
    }

    override fun onReactionRemove(e: MessageReactionRemoveEvent) {
        if(messages.containsKey(e.messageIdLong) && !e.user.isBot) {
            when(e.reactionEmote.name) {
                emotes[0] -> e.channel.sendMessage("up").queue()
                emotes[1] -> e.channel.sendMessage("down").queue()
            }
        }
    }
}