package com.ktda.core.roles

import com.ktda.core.permissions.PermissionsListBuilder
import net.dv8tion.jda.core.Permission
import net.dv8tion.jda.core.entities.Guild
import net.dv8tion.jda.core.managers.GuildController
import java.awt.Color

class GuildRoleBuilder {
    lateinit var name: String
    var color: Int = -1
    lateinit var perms: MutableList<Permission>

    fun permissions(init: PermissionsListBuilder.() -> Unit) {
        var builder = PermissionsListBuilder(init)
        apply{ perms = builder.allow }
    }

    fun build(guild: Guild) {
        var role = GuildController(guild).createRole()
        if (color != -1) role.setColor(Color(color))
        role.setName(name).setPermissions(perms).queue()
    }
}

inline fun Guild.createRole(builder: GuildRoleBuilder.() -> Unit) = GuildRoleBuilder().apply(builder).build(this)
