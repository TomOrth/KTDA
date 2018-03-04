package com.ktda.core.messages

import net.dv8tion.jda.core.entities.MessageChannel
import net.dv8tion.jda.core.MessageBuilder
import net.dv8tion.jda.core.requests.restaction.MessageAction
import java.io.File

class SendMessageBuilder(init: SendMessageBuilder.() -> Unit) {
    var delim: String = " "
    lateinit var file: File
    lateinit var onSend: () -> Unit
    val msgContent : MutableList<String> = mutableListOf()
    init {
        init()
    }

    operator fun String.unaryPlus() = msgContent.add(this)

    fun build(channel: MessageChannel) : MessageAction {
        val builder = MessageBuilder()
        builder.append(msgContent.joinToString(delim))
        val msg = builder.build()
        return if (::file.isInitialized) channel.sendFile(file, msg) else channel.sendMessage(msg)
    }
}

fun MessageChannel.sendMessage(builder: SendMessageBuilder.() -> Unit) = SendMessageBuilder(builder).build(this)
