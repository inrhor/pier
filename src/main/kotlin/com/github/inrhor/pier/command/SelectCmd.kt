package com.github.inrhor.pier.command

import com.github.inrhor.pier.api.manager.DataManager.getData
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import taboolib.common.platform.ProxyCommandSender
import taboolib.common.platform.ProxyPlayer
import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.subCommand

object SelectCmd {

    @CommandBody
    val select = subCommand {
        dynamic {
            suggestion<ProxyCommandSender> { _, _ ->
                Bukkit.getOnlinePlayers().map { it.name }
            }
            dynamic {
                suggestion<ProxyCommandSender> { sender, _ ->
                    sender.cast<Player>().getData().boardMap.keys.map { it }
                }
                execute<ProxyPlayer> { sender, _, argument ->
                    val p = sender.cast<Player>()
                    p.getData().updateSelect(p, argument.split(" ")[0])
                }
            }
        }
    }

}