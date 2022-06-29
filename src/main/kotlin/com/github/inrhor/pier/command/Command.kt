package com.github.inrhor.pier.command

import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.CommandHeader

@CommandHeader("pier")
object Command {

    @CommandBody(permission = "pier.admin.add")
    val add = AddCmd.add

    @CommandBody(permission = "pier.use.select")
    val select = SelectCmd.select

    @CommandBody(permission = "pier.admin.remove")
    val remove = RemoveCmd.remove

    @CommandBody(permission = "pier.admin.reload")
    val reload = ReloadCmd.reload

}