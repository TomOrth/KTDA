package com.ktda.core.extensions.bot

import com.ktda.core.extensions.Preferences
import net.dv8tion.jda.core.JDA
import net.dv8tion.jda.core.JDABuilder
import net.dv8tion.jda.core.hooks.ListenerAdapter


infix fun JDABuilder.withToken(token: String) = this.setToken(token)

infix fun JDABuilder.withEvents(events: ListenerAdapter) = this.addEventListener(events)

infix fun JDABuilder.login(logging: Boolean) : JDA {
    Preferences.logging = logging
    return this.buildBlocking()
}