package com.ktda.core.roles

import com.ktda.core.permissions.PermissionsListBuilder
import net.dv8tion.jda.core.entities.Role
import java.awt.Color

class UpdateRoleBuilder {
    lateinit var permBuilder: PermissionsListBuilder
    var color: Int = -1
    lateinit var name: String

    fun permissions(perms: PermissionsListBuilder.() -> Unit) {
        val builder = PermissionsListBuilder(perms)
        apply{ this.permBuilder = builder }
    }

    fun build(role: Role) {
        println(permBuilder.deny)
        permBuilder.allow.forEach{ role.manager.givePermissions(it).queue() }
        permBuilder.deny.forEach{ role.manager.revokePermissions(it).queue() }
        if(::name.isInitialized) role.manager.setName(name).queue()
        if(color != -1) role.manager.setColor(Color(color)).queue()
    }
}

inline fun Role.update(builder: UpdateRoleBuilder.() -> Unit) = UpdateRoleBuilder().apply(builder).build(this)