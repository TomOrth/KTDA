package com.ktda.core.permissions

import net.dv8tion.jda.core.Permission

class PermissionsListBuilder(init: PermissionsListBuilder.() -> Unit) {
    lateinit var allow: MutableList<Permission>
    lateinit var deny: MutableList<Permission>
    init {
        allow = mutableListOf<Permission>()
        deny = mutableListOf<Permission>()
        init()
    }

    operator fun Permission.unaryPlus() {
        println(allow)
        allow.add(this)
    }
    operator fun Permission.unaryMinus() = deny.add(this)
}

