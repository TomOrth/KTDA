package com.ktda.example

import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter

class ExampleEvents : ListenerAdapter() {
    override fun onMessageReceived(event: MessageReceivedEvent?) {
        if(event?.message?.contentRaw.equals("ping") && !event?.message?.author!!.isBot()) {
            event?.message?.channel?.sendMessage("pong!")?.queue()
        }
    }
}