package com.ktda.core
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async
import net.dv8tion.jda.core.AccountType
import net.dv8tion.jda.core.JDA
import net.dv8tion.jda.core.JDABuilder
import net.dv8tion.jda.core.hooks.EventListener
import java.awt.Event

class KTDA(accountType: AccountType = AccountType.BOT, token: String, events: List<EventListener>) {
    var jda: JDABuilder = JDABuilder(accountType)
            .setToken(token)

    init {
        events.forEach {
            jda.addEventListener(it)
        }
    }
    fun build() = jda.buildBlocking()
}

class KTDABuilder(var accountType: AccountType = AccountType.BOT) {
    lateinit var token: String
    var events: MutableList<EventListener> = mutableListOf()

    fun event(init: KTDABuilder.() -> EventListener) = apply { events.add(init()) }

    fun build() = KTDA(accountType, token, events)
}

fun bot(accountType: AccountType = AccountType.BOT, init: KTDABuilder.() -> Unit) : KTDA {
    val bot = KTDABuilder(accountType)
    bot.init()
    return bot.build()
}

fun botAsync(accountType: AccountType = AccountType.BOT, init: KTDABuilder.() -> Unit) : Deferred<JDA> {
    var bot = bot(accountType, init)
    return async {
        bot.build()
    }
}
