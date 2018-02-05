package com.ktda.core.extensions.bot

import net.dv8tion.jda.core.AccountType
import net.dv8tion.jda.core.JDABuilder

var discordBot = JDABuilder(AccountType.BOT)
var discordClient = JDABuilder(AccountType.CLIENT)