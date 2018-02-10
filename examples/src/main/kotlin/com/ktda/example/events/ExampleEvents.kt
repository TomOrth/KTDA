package com.ktda.example.events

import com.ktda.example.reaction.ReactionTest
import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent
import net.dv8tion.jda.core.events.message.react.MessageReactionRemoveEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter
import com.ktda.core.guild.*
import net.dv8tion.jda.core.Permission
import net.dv8tion.jda.core.managers.GuildController

class ExampleEvents : ListenerAdapter() {
    override fun onMessageReceived(event: MessageReceivedEvent?) {
        if(event?.message?.contentRaw.equals("ping") && !event?.message?.author!!.isBot()) {
            event?.message?.channel?.sendMessage("pong!")?.queue()
        }
        if(event?.message?.contentRaw.equals("react")) {
            event!!.message.channel.sendMessage("React").queue{ ReactionTest.react(it, it.guild) }
        }
        if(event?.message?.contentRaw.equals("role")) {
            event?.guild!!.createRole {
                name = "test2"
                permissions {
                    +Permission.MANAGE_CHANNEL
                }
            }
        }
        if(event?.message?.contentRaw.equals("chan")) {
            event?.guild!!.createTextChannel {
                name = "discord"
                topic = "testing stuff"
            }
        }
    }

    override fun onMessageReactionAdd(event: MessageReactionAddEvent?) = ReactionTest.onReactionAdd(event!!)

    override fun onMessageReactionRemove(event: MessageReactionRemoveEvent?) = ReactionTest.onReactionRemove(event!!)
}