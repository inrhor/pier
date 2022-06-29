package com.github.inrhor.pier.command

import com.github.inrhor.pier.server.Loader
import taboolib.common.platform.ProxyCommandSender
import taboolib.common.platform.command.subCommand
import taboolib.module.lang.sendLang

object ReloadCmd {

    val reload = subCommand {
        execute<ProxyCommandSender> { sender, _, _ ->
            Loader.doReload()
            sender.sendLang("LOADER_RELOAD")
        }
    }

}