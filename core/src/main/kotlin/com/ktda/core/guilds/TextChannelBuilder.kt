package com.ktda.core.guilds


import net.dv8tion.jda.core.entities.Guild
import net.dv8tion.jda.core.managers.GuildController

class TextChannelBuilder {

    lateinit var name: String
    lateinit var topic: String
    var nsfw: Boolean = false

    fun build(guild: Guild) = GuildController(guild).createTextChannel(name).setTopic(topic).setNSFW(nsfw).queue()

}

inline fun Guild.createTextChannel(builder: TextChannelBuilder.() -> Unit) = TextChannelBuilder().apply(builder).build(this)
