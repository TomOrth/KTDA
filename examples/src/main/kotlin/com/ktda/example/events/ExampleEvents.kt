package com.ktda.example.events

import com.ktda.example.reaction.ReactionTest
import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent
import net.dv8tion.jda.core.events.message.react.MessageReactionRemoveEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter
import com.ktda.core.guilds.*
import com.ktda.core.messages.*
import com.ktda.core.users.*
import net.dv8tion.jda.core.Permission

class ExampleEvents : ListenerAdapter() {
    override fun onMessageReceived(event: MessageReceivedEvent?) {

        if(event?.message?.contentRaw.equals("ping") && !event?.message?.author!!.isBot()) {
            event?.message?.channel?.sendMessage {
                delim = " "
                +"Hello"
                +"Pong"
            }?.queue()
        }

        if(event?.message?.contentRaw.equals("react")) {
            event!!.message.channel.sendMessage {
                +"React"
            }.queue { ReactionTest.react(it, it.guild) }
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

        if(event?.message?.contentRaw!!.startsWith("mute")) {
            event?.message!!.mentionedMembers[0].overridePerms {
                channel = event?.message!!.textChannel
                permissions {
                    -Permission.MESSAGE_WRITE
                }
            }
        }

        if(event?.message?.contentRaw!!.startsWith("unmute")) {
            event?.message!!.mentionedMembers[0].overridePerms {
                channel = event?.message!!.textChannel
                permissions {
                    +Permission.MESSAGE_WRITE
                }
            }
        }
    }

    override fun onMessageReactionAdd(event: MessageReactionAddEvent?) = ReactionTest.onReactionAdd(event!!)

    override fun onMessageReactionRemove(event: MessageReactionRemoveEvent?) = ReactionTest.onReactionRemove(event!!)
}