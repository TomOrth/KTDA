package com.ktda.example
import com.ktda.core.extensions.bot.*
fun main(args: Array<String>) {
    discordBot withToken "token" withEvents ExampleEvents() login true
}