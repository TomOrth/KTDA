package com.ktda.example
import com.ktda.core.bot
import com.ktda.example.events.ExampleEvents
import kotlinx.coroutines.experimental.runBlocking

fun main(args: Array<String>) = runBlocking {
    bot {
        token = "NDA5ODgzODAwNDYyNTU3MTg3.DV5DIw.O77b9FW7ejIg6WMRLxLmYk7XMds"
        event { ExampleEvents() }
    }.build()
    println("Bot built") //TODO: replace with logging
}