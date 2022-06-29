package com.github.inrhor.pier.command

import com.github.inrhor.pier.api.manager.BoardManager
import com.github.inrhor.pier.api.manager.DataManager.addBoard
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import taboolib.common.platform.ProxyCommandSender
import taboolib.common.platform.ProxyPlayer
import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.subCommand

object AddCmd {

    @CommandBody
    val add = subCommand {
        dynamic {
            suggestion<ProxyCommandSender> { _, _ ->
                Bukkit.getOnlinePlayers().map { it.name }
            }
            dynamic {
                suggestion<ProxyCommandSender> { _, _ ->
                    BoardManager.boardMap.keys.map { it }
                }
                execute<ProxyPlayer> { sender, _, argument ->
                    val p = sender.cast<Player>()
                    p.addBoard(argument.split(" ")[0])
                }
            }
        }
    }

}