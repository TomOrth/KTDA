package com.ktda.core.guilds


import net.dv8tion.jda.core.entities.Guild
import net.dv8tion.jda.core.managers.GuildController

class TextChannelBuilder(init: TextChannelBuilder.() -> Unit) {
    init {
        init()
    }
    lateinit var name: String
    lateinit var topic: String
    var nsfw: Boolean = false

    fun build(guild: Guild) = GuildController(guild).createTextChannel(name).setTopic(topic).setNSFW(nsfw).queue()

}

fun Guild.createTextChannel(builder: TextChannelBuilder.() -> Unit) = TextChannelBuilder(builder).build(this)
