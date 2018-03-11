package com.ktda.core.users

import com.ktda.core.permissions.PermissionsListBuilder
import net.dv8tion.jda.core.Permission
import net.dv8tion.jda.core.entities.Channel
import net.dv8tion.jda.core.entities.Member
import net.dv8tion.jda.core.managers.PermOverrideManagerUpdatable

class PermissionOverrideBuilder {
    lateinit var channel: Channel
     lateinit var permBuilder: PermissionsListBuilder

    fun permissions(perms: PermissionsListBuilder.() -> Unit) {
        val builder = PermissionsListBuilder(perms)
        apply{ this.permBuilder = builder }
    }


    fun build(mem: Member) {
        val override = channel.getPermissionOverride(mem)
        if(override != null) PermOverrideManagerUpdatable(override).grant(permBuilder.allow).deny(permBuilder.deny).update().queue()
        else channel.createPermissionOverride(mem).setAllow(permBuilder.allow).setDeny(permBuilder.deny).queue()
    }

}

inline fun Member.overridePerms(builder: PermissionOverrideBuilder.() -> Unit) = PermissionOverrideBuilder().apply(builder).build(this)