package com.ktda.core.messages

import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.MessageBuilder
import net.dv8tion.jda.core.entities.MessageChannel
import net.dv8tion.jda.core.entities.MessageEmbed
import net.dv8tion.jda.core.requests.restaction.MessageAction
import java.awt.Color

class MessageEmbedBuilder {
    lateinit var author: String
    lateinit var description: String
    lateinit var url: String
    lateinit var image: String
    lateinit var title: String
    lateinit var titleUrl: String
    var color: Int = -1
    val fields: MutableMap<String, FieldEntry> = mutableMapOf()
    lateinit var footer: FooterBuilder

    fun field(builder: FieldsBuilder.() -> Unit) {
        val fieldsBuilder = FieldsBuilder().build(builder)
        fields[fieldsBuilder.name] = FieldEntry(fieldsBuilder.value, fieldsBuilder.inline)
    }

    fun footer(builder: FooterBuilder.() -> Unit) {
        footer = FooterBuilder().build(builder)
    }

    fun build(channel: MessageChannel) : MessageAction {
        val e = EmbedBuilder()

        if(color != -1) e.setColor(Color(color))

        if(::title.isInitialized && ::titleUrl.isInitialized) e.setTitle(title, titleUrl)
        else if(::title.isInitialized && !::titleUrl.isInitialized) e.setTitle(title)

        if(::description.isInitialized) e.setDescription(description)

        fields.forEach { e.addField(it.key, it.value.value, it.value.inline) }

        if(::url.isInitialized) e.setAuthor(author, url)
        else e.setAuthor(author)

        if(::image.isInitialized) e.setImage(image)
        e.setFooter(footer.text, footer.iconUrl)

        return MessageBuilder(e.build()).sendTo(channel)
    }

    class FieldsBuilder {
        lateinit var name: String
        lateinit var value: String
        var inline: Boolean = true

        fun build(builder: FieldsBuilder.() -> Unit)  = FieldsBuilder().apply(builder)
    }

    class FieldEntry(val value: String, val inline: Boolean)

    class FooterBuilder {
        var text: String = ""
        var iconUrl: String = ""

        fun build(builder: FooterBuilder.() -> Unit)  = FooterBuilder().apply(builder)
    }
}

inline fun MessageChannel.sendEmbed(builder: MessageEmbedBuilder.() -> Unit) = MessageEmbedBuilder().apply(builder).build(this)

